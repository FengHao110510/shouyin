package com.hongsou.douguoshouyin.http;

import android.util.Log;

import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by Administrator on 2018/7/2.
 */

public class HttpFactory {

    private static final String TAG = "HttpFactory";

    protected static final String GET = "GET";
    protected static final String POST = "POST";
    private String type;
    private String url;
    private Object tag;
    private Map<String, String> params;


    public HttpFactory(String type, String url, Object tag, Map<String, String> params) {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.type = type;
    }

    public static void postString(String url,String content,ResponseCallback responseCallback){
        if (NetWorkStateUtils.isNetConnected()) {
            Log.e(TAG, "【参数】 :: " + content);
            try {
               OkHttpUtils.postString().content(content)
                       .mediaType(MediaType.parse("application/json"))
                       .addHeader("token", Global.getSpGlobalUtil().getCode())
                       .addHeader("phone", Global.getSpGlobalUtil().getUserName())
                       .url(url).build().execute(responseCallback);
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtil.showToast("参数有误");
                BaseActivity.dismissLoadingDialog();
                BaseFragment.dismissLoadingDialog();
            }
        } else {
            BaseActivity.dismissLoadingDialog();
            BaseFragment.dismissLoadingDialog();
            ToastUtil.showToast("网络未连接，请检查网络");
        }
    }
    public static PostHttpBuilder post() {
        return new PostHttpBuilder();
    }

//    public static PostHttpStringBuilder postString(){
//        return new PostHttpStringBuilder();
//    }

    public static GetHttpBuilder get() {
        return new GetHttpBuilder();
    }

    public void execute(final Callback callback) {
        if (NetWorkStateUtils.isNetConnected()) {
            if (params == null){
                params = new HashMap<>();
            }
            Log.e(TAG, "【参数】 :: " + params.toString());
            try {
                RequestCall build;
                if (type.equals(POST)) {
                    build = OkHttpUtils.post().url(url).params(params)
                            .addHeader("token", Global.getSpGlobalUtil().getCode())
                            .addHeader("phone", Global.getSpGlobalUtil().getLoginPhone())
                            .build();
                } else {
                    build = OkHttpUtils.get().url(url).params(params)
                            .addHeader("token", Global.getSpGlobalUtil().getCode())
                            .addHeader("phone", Global.getSpGlobalUtil().getUserName())
                            .build();
                }
                build.execute(callback);
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtil.showToast("参数有误");
                BaseActivity.dismissLoadingDialog();
                BaseFragment.dismissLoadingDialog();
            }
        } else {
            BaseActivity.dismissLoadingDialog();
            BaseFragment.dismissLoadingDialog();
            ToastUtil.showToast("网络未连接，请检查网络");
        }
    }
}