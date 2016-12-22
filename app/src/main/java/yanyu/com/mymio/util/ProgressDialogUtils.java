package yanyu.com.mymio.util;

import android.content.Context;

import yanyu.com.mymio.R;
import yanyu.com.mymio.view.LoadingDialog;


/**
 * Created on 2016/10/27.
 * Author：qdq
 * Description:加载对话框
 */
public class ProgressDialogUtils {
	
	private static LoadingDialog mProgressDialog;
	
	public static void showProgress(String message, Context context) {
		mProgressDialog = new LoadingDialog(context, R.style.loadDialog);
		mProgressDialog.setContent(message);
		mProgressDialog.setCancelable(true);
		if (!mProgressDialog.isShowing()) {
			mProgressDialog.show();
		}

	}
	public static void hideProgress() {
		try{
			if (mProgressDialog != null) {
				if (mProgressDialog.isShowing()) {
					mProgressDialog.dismiss();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
