package yanyu.com.mymio.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import yanyu.com.mymio.util.LogUtils;
import yanyu.com.mymio.util.MD5Utils;

/**
 * Created by zq on 2016/12/1 0001.
 */

public class HttpHelper {
    public static void HttpPostUtil(String url, HashMap<String, Object> params, HttpCallBack httpCallBack) {
        params.put("nonce_str", MD5Utils.getMessageDigest(String.valueOf(System.currentTimeMillis()).getBytes()));
        if (params.size() > 0) {
            JSONObject jsonObject = new JSONObject();
            Set<String> keySet = params.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                LogUtils.e("http->param", key + "=" + params.get(key));
                if (key.equals("password")) {
                    jsonObject.put(key, MD5Utils.getSHA1(String.valueOf(params.get(key))));
                } else {
                    if (params.get(key) instanceof Boolean) {
                        jsonObject.put(key, params.get(key));
                    } else if (params.get(key) instanceof Number) {
                        jsonObject.put(key, params.get(key));
                    } else if (params.get(key) instanceof Character) {
                        jsonObject.put(key, params.get(key));
                    } else if (params.get(key) instanceof String) {
                        jsonObject.put(key, params.get(key));
                    } else if (params.get(key) instanceof JSONArray) {
                        jsonObject.put(key, params.get(key));
                    } else if (params.get(key) instanceof JSONObject) {
                        jsonObject.put(key, params.get(key));
                    }
                }
            }
            OkHttpUtils.post().url(url).addParams("data", MD5Utils.encrypt(jsonObject.toString())).build().execute(httpCallBack);
            LogUtils.e(jsonObject.toString());
        }

    }

    public static void HttpPostArrayUtil(String url, HashMap<String, Object> params, HttpArrayCallBack httpCallBack) {
        params.put("nonce_str", MD5Utils.getMessageDigest(String.valueOf(System.currentTimeMillis()).getBytes()));
        if (params.size() > 0) {
            JSONObject jsonObject = new JSONObject();
            Set<String> keySet = params.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                LogUtils.e("http->param", key + "=" + params.get(key));
                if (key.equals("password")) {
                    jsonObject.put(key, MD5Utils.getSHA1(String.valueOf(params.get(key))));
                } else {
                    if (params.get(key) instanceof Boolean) {
                        jsonObject.put(key, params.get(key));
                    } else if (params.get(key) instanceof Number) {
                        jsonObject.put(key, params.get(key));
                    } else if (params.get(key) instanceof Character) {
                        jsonObject.put(key, params.get(key));
                    } else if (params.get(key) instanceof String) {
                        jsonObject.put(key, params.get(key));
                    } else if (params.get(key) instanceof JSONArray) {
                        jsonObject.put(key, params.get(key));
                    } else if (params.get(key) instanceof JSONObject) {
                        jsonObject.put(key, params.get(key));
                    }
                }
            }
            OkHttpUtils.post().url(url).addParams("data", MD5Utils.encrypt(jsonObject.toString())).build().execute(httpCallBack);
            LogUtils.e(jsonObject.toString());
        }
    }
}
