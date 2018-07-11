package com.hongsou.douguoshouyin.http;

import android.util.Log;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.http.NetWorkStateUtils;
import com.hongsou.douguoshouyin.http.PostHttpBuilder;
import com.hongsou.douguoshouyin.tool.LogUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;


import okhttp3.Call;

/**
 * Created by Administrator on 2018/7/2.
 */

public class HttpFactory {

    private static final String TAG = "HttpFactory";

    private  String url;
    private  Object tag;
    private  Map<String, String> params;


    public HttpFactory(String url, Object tag, Map<String, String> params) {
        this.url = url;
        this.tag = tag;
        this.params = params;
    }

    public static PostHttpBuilder post(){
        return new PostHttpBuilder();
    }

//    public static PostHttpStringBuilder postString(){
//        return new PostHttpStringBuilder();
//    }

    public static PostHttpBuilder get(){
        return new PostHttpBuilder();
    }

    public void execute(final StringCallback stringCallBack) {
        if (NetWorkStateUtils.isNetConnected()){
            Log.e(TAG, "参数 :: " + params.toString());
            try {
                OkHttpUtils.post()
                        .url(url)
                        .params(params)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showToast(R.string.http_error);
                        stringCallBack.onError(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e(TAG, "==================== 返回结果 ==================");
                        LogUtil.e(TAG, response );
                        LogUtil.e(TAG, "====================== END ====================");
                        stringCallBack.onResponse(response, id);
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
                ToastUtil.showToast("参数有误");
                BaseActivity.dismissLoadingDialog();
                BaseFragment.dismissLoadingDialog();
            }
        }else {
            BaseActivity.dismissLoadingDialog();
            BaseFragment.dismissLoadingDialog();
            ToastUtil.showToast("网络未连接，请检查网络");
        }
    }
}