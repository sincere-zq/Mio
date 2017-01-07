package yanyu.com.mymio.callback;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import yanyu.com.mymio.base.BaseBean;
import yanyu.com.mymio.util.LogUtils;
import yanyu.com.mymio.util.MD5Utils;
import yanyu.com.mymio.util.StringUtil;

/**
 * Created by zengqiang on 2016/8/16 0016.
 * Description: http回调
 */
public abstract class HttpCallBack<T> extends StringCallback {

    private Class<T> tClass;

    public HttpCallBack() {
        tClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        onFail("网络异常");
    }

    @Override
    public void onResponse(String response, int id) {

        if (StringUtil.isNotEmpty(response)) {
            LogUtils.e("response" + response);
            try {
                String entireResponse = MD5Utils.desEncrypt(response);
                BaseBean baseBean = JSON.parseObject(entireResponse, BaseBean.class);
                LogUtils.e("result" + entireResponse);
                if (baseBean.result) {
                    onSuccess(JSON.parseObject(entireResponse, tClass));
                } else {
                    onFail(baseBean.message.toString());
                }
            } catch (JSONException e) {
                onFail("解析异常");
            }
        } else
            onFail("服务器返回内容为空");
    }

    public abstract void onSuccess(T result);

    public abstract void onFail(String errMsg);

}
