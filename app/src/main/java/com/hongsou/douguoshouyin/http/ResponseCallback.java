package com.hongsou.douguoshouyin.http;


import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.LogUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.LoadingDialog;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/13.
 */

public abstract class ResponseCallback<T> extends Callback<T> {

    private static final String TAG = "ResponseCallback";

    private Context mContext;
    private boolean mIsShowDialog;
    private String message;
    private Request mRequest;


    public static Gson mGson;
    private LoadingDialog loadingDialog;


    public ResponseCallback(Context context) {
        this(context, "加载中...", true);
    }

    public ResponseCallback(Context context, String msg) {
        this(context, msg, true);
    }

    public ResponseCallback(Context context, String msg, boolean isShowDialog) {
        message = msg;
        mContext = context;
        mIsShowDialog = isShowDialog;
        if (mGson == null){
            mGson = new Gson();
        }
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        T t = null;
        try {
            String s = response.body().string();
            LogUtil.e(TAG, "==================== 返回结果 ==================");
            LogUtil.e(TAG, s);
            LogUtil.e(TAG, "====================== END ====================");
            //解析json,返回bean对象
            Type genericSuperclass = getClass().getGenericSuperclass();
            Type genericityType;
            if (genericSuperclass instanceof ParameterizedType) {
                genericityType = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            } else {
                genericityType = Object.class;
            }
            t = mGson.fromJson(s, genericityType);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        //在这里做异常统一处理
        System.out.println("onError");
        Log.e(TAG, "onError: " + e.toString());
        dismissLoadingDialog();
        ToastUtil.showError();
        cusError(call, e, id);
    }

    public void cusError(Call call, Exception e, int id) {
    }

    @Override
    public void onBefore(Request request, int id) {
        //这里开启动画
        mRequest = request;
        if (mIsShowDialog && mContext != null) {
            if (loadingDialog == null) {
                loadingDialog = new LoadingDialog(mContext);
                loadingDialog.setCancelable(true);
            }
            if (mContext instanceof Activity && !((Activity) mContext).isFinishing()) {
                loadingDialog.setMessage(message);
                loadingDialog.show();
            }
        }
        super.onBefore(request, id);
    }

    @Override
    public void onAfter(int id) {
        //这里结束动画
        try {
            dismissLoadingDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onAfter(id);
    }

    /**
     * 进度条
     */
    public void showLoadingDialog(Context context, String msg) {
        dismissLoadingDialog();
        loadingDialog = new LoadingDialog(context);
        loadingDialog.setMessage(msg);
        loadingDialog.show();
    }

    public void showLoadingDialog(Context context) {
        dismissLoadingDialog();
        loadingDialog = new LoadingDialog(context);
        loadingDialog.setMessage("加载中...");
        loadingDialog.show();
    }

    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }

    }
}
