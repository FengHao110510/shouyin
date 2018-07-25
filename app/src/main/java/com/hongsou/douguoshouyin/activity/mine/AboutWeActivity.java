package com.hongsou.douguoshouyin.activity.mine;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.SystemSetupBean;
import com.hongsou.douguoshouyin.tool.DateUtils;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.tool.Global;

import java.io.File;

import okhttp3.Call;

/**
 * 关于我们
 */
public class AboutWeActivity extends BaseActivity {


    @BindView(R.id.tv_mine_aboutwe_version)
    TextView tvMineAboutweVersion;
    @BindView(R.id.tv_mine_aboutwe_check)
    Button tvMineAboutweCheck;


    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_about_we;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("关于我们");
    }

    @Override
    public void initView() {
        tvMineAboutweVersion.setText("版本号V " + Global.getVersionName());
    }

    @Override
    public void initData() {
    }




    @OnClick(R.id.tv_mine_aboutwe_check)
    public void onViewClicked() {
        //检查更新  走接口 TODO
        HttpFactory.get().url(ApiConfig.GET_SYSTEM_SETUP)
                .addParams("diffVersion", "qingshouyin")
                .build().execute(new ResponseCallback<SystemSetupBean>(this) {
            @Override
            public void onResponse(SystemSetupBean response, int id) {
                if (response.isSuccess()){
                    if (!"123".equals(response.getData().getNewVersionNumber())){
                        downLoadApk(response.getData().getDownloadAddress());
                    }else {
                        ToastUtil.showToast("最新版本无需更新");
                    }
                }else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });


    }

    private void downLoadApk(String downloadAddress) {
        String path = Environment.getExternalStorageDirectory() + "/douguo/apk/";
        OkHttpUtils.get().url(downloadAddress).build().execute(
                new FileCallBack(path,"qingshouyin"+ DateUtils.getNowDateLong()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.showToast("网络连接失败");

            }

            @Override
            public void onResponse(File response, int id) {
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
            }
        });
    }


    //=================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
