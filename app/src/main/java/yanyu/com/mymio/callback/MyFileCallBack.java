package yanyu.com.mymio.callback;

import com.zhy.http.okhttp.callback.FileCallBack;

import yanyu.com.mymio.constant.Constant;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public abstract class MyFileCallBack extends FileCallBack {
    public MyFileCallBack(String destFileName) {
        super(Constant.DOWNLOAD_FILE, destFileName);
    }
}
