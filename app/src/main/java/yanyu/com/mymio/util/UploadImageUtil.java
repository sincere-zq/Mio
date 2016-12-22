package yanyu.com.mymio.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by zengqiang on 16/4/8.
 * description:
 */
public class UploadImageUtil {

    /* 图片保存路径 */
    public static final String PHOTO_URI = "/YANYU/Image";

    /**
     * 用来标识请求照相功能的activity
     */
    public static final int CAMERA_WITH_DATA = 3021;
    /**
     * 用来标识请求相册的activity
     */
    public static final int PHOTO_PICKED_WITH_DATA = 3022;
    /**
     * 用跳转剪切
     */
    public static final int CROP_BIG_PICTURE = 3024;
    /**
     * 拍照的照片存储位置
     */
    public static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + PHOTO_URI);

    private static File mCurrentPhotoFile;// 照相机拍照得到的图片

    private static ByteArrayOutputStream baos;

    private OnCompleteListener onCompleteListener;

    /**
     * 从图库选择
     */
    public static void doPickPhotoFromGallery(Activity activity) {
        try {
            PHOTO_DIR.mkdirs();
            mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());
            final Intent intent = getPhotoPickIntent();
            activity.startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
        } catch (Exception e) {
            Log.e("Fans", "not photo find");
        }
    }

    /**
     * 拍照
     */
    public static void doTakePhoto(Activity activity) {
        if (!SDcardUtils.ExistSDCard()) {
            Log.e("Fans", "没有SDCard");
            return;
        }
        try {
            PHOTO_DIR.mkdirs();// 创建照片的存储目录
            mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
            final Intent intent = getTakePickIntent(mCurrentPhotoFile);
            activity.startActivityForResult(intent, CAMERA_WITH_DATA);
        } catch (Exception e) {
            Log.e("Fans", "获取相机失败");
        }
    }

    /**
     * 用当前时间给取得的图片命名
     */
    @SuppressLint("SimpleDateFormat")
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    public static Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        intent.putExtra("outputFormat",
                Bitmap.CompressFormat.JPEG.toString());
        return intent;
    }

    public static Intent getPhotoPickIntent() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        return intent;
    }

    private static String image_path;

    /**
     * 处理上传图片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static File dealWithUploadImageOnActivityResult(Activity activity, int requestCode, int resultCode, Intent data, OnCompleteListener onCompleteListener) {
        if (resultCode == Activity.RESULT_OK) {


            if (requestCode == PHOTO_PICKED_WITH_DATA) {// 从相册里面返回
                if (data != null) {
                    ContentResolver cr = activity.getContentResolver();
                    image_path = ImageUtil.getUriString(data.getData(), cr);
//                    cropImageUri(activity, data.getData(), 300, 300, CROP_BIG_PICTURE);

                    //注：如果不剪切图片的话就把cropImageUri这行代码注视直接调用  savaImage(image_path,onCompleteListener);  return new File(image_path);
                    savaImage(activity, image_path, onCompleteListener);
                    return new File(image_path);
                }
            } else if (requestCode == CAMERA_WITH_DATA) {// 从相机里面返回
                image_path = mCurrentPhotoFile.getPath();
                savaImage(activity, image_path, onCompleteListener);
                return new File(image_path);
//                cropImageUri(activity, Uri.fromFile(mCurrentPhotoFile), 300, 300, CROP_BIG_PICTURE);
            } else if (requestCode == CROP_BIG_PICTURE) {//从裁剪大图里面返回
                savaImage(activity, image_path, onCompleteListener);
                return new File(image_path);
//                cropImageUri(activity, data.getData(), 300, 300, CROP_BIG_PICTURE);
            }

        }
        return null;
    }

    /**
     * 裁剪照片
     *
     * @param uri
     * @param outputX
     * @param outputY
     * @param requestCode
     */
    private static void cropImageUri(final Activity activity, Uri uri, int outputX, int outputY, int requestCode) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ProgressDialogUtils.showProgress("头像上传中...", activity);
            }
        });
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, requestCode);
    }


    /**
     * 压缩并保存图片
     *
     * @param imgPath
     */
    private static void savaImage(final Activity activity, final String imgPath, final OnCompleteListener onCompleteListener) {
        if (StringUtil.isNotEmpty(imgPath)) {

            new Thread() {
                public void run() {
                    try {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ProgressDialogUtils.showProgress("头像上传中...", activity);
                            }
                        });
                        Bitmap resizeBitmap = ImageUtil.parseToProveBitmap(imgPath);
                        baos = new ByteArrayOutputStream();
                        ExifInterface sourceExif = new ExifInterface(imgPath);
                        int result = sourceExif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                        int rotate = 0;
                        switch (result) {
                            case ExifInterface.ORIENTATION_ROTATE_90:
                                rotate = 90;
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_180:
                                rotate = 180;
                                break;
                            case ExifInterface.ORIENTATION_ROTATE_270:
                                rotate = 270;
                                break;
                        }

                        if (resizeBitmap != null) {
                            if (rotate > 0) {
                                Matrix matrix = new Matrix();
                                matrix.setRotate(rotate); // resizeBitmap.getWidth()
                                // resizeBitmap.getHeight()
                                Bitmap rotateBitmap = Bitmap.createBitmap(resizeBitmap, 0, 0, resizeBitmap.getWidth(), resizeBitmap.getHeight(), matrix, true);
                                if (rotateBitmap != null) {
                                    resizeBitmap.recycle();
                                    resizeBitmap = rotateBitmap;
                                }
                            }
                            resizeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            int options = 100;
                            while (baos.toByteArray().length > 100 * 1024) { //循环判断如果压缩后图片是否大于100kB,大于继续压缩
                                baos.reset();//重置baos即清空baos
                                resizeBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
                                options -= 10;//每次都减少5
                            }
                        }

                        if (onCompleteListener != null) {
                            onCompleteListener.onComplete(imgPath, resizeBitmap);
                        }

                        LogUtils.e("压缩后图片大小为： " + baos.toByteArray().length);
//                        File dirFile = new File(imgPath);
                        //检测图片是否存在
//                        if (dirFile.exists()) {
//                            dirFile.delete();  //删除原图片
//                        }
//                        File myCaptureFile = new File(imgPath);
//                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
//                        bufferedOutputStream.write(baos.toByteArray());
//                        bufferedOutputStream.flush();
//                        bufferedOutputStream.close();
                    } catch (OutOfMemoryError e) {
                        System.gc();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }.start();

        } else {
            Log.e("Fans", "图片路径为空");
        }

    }

    public interface OnCompleteListener {
        void onComplete(String imgPath, Bitmap bitmap);
    }

}
