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
