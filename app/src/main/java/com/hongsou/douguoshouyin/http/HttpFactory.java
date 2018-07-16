package com.hongsou.douguoshouyin.http;

import android.util.Log;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.http.NetWorkStateUtils;
import com.hongsou.douguoshouyin.http.PostHttpBuilder;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.LogUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.Map;


import okhttp3.Call;

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
            Log.e(TAG, "【参数】 :: " + params.toString());
            try {
                RequestCall build;
                if (type.equals(POST)) {
                    build = OkHttpUtils.post().url(url).params(params).build();
                } else {
                    build = OkHttpUtils.get().url(url).params(params).build();
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