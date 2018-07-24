package com.hongsou.douguoshouyin.activity;


import android.Manifest;
import android.content.Intent;
import android.util.Log;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.login.LoginActivity;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.functions.Consumer;

/**
 * 幻影页面
 */
public class WelcomeActivity extends BaseActivity {


    private Timer timer;

    @Override
    protected void init() {
        initView();
        initData();
    }

    @Override
    public int initLayout() {
        return R.layout.module_activity_welcome;
    }

    @Override
    public void initView() {
        initTimer();
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {

                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            ToastUtil.showToast("请打开相关权限，否则将影响使用");
                        }
                    }
                });
    }

    /**
     * 设置延时时间
     */
    private void initTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                intentMainAct();
                Log.e("timer", "---->" + Thread.currentThread());
            }
        };
        timer.schedule(timerTask, 3000);
    }

    //跳转方向
    private void intentMainAct() {
        Intent mainIntent = null;
        mainIntent = new Intent(this, LoginActivity.class);
//        mainIntent = new Intent(this, MainActivity.class);

        startActivity(mainIntent);
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        finish();
    }

    @Override
    public void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
