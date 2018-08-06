package com.hongsou.douguoshouyin.activity;


import android.content.Intent;
import android.text.TextUtils;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.login.LoginActivity;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.tool.Global;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity {


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

    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(4);

    /**
     * 设置延时时间
     */
    private void initTimer() {
        mScheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                intentMainAct();
            }
        }, 3, TimeUnit.SECONDS);
    }

    //跳转方向
    private void intentMainAct() {
        Intent mainIntent = null;

        if (TextUtils.isEmpty(Global.getSpGlobalUtil().getClerkNumber())) {
            mainIntent = new Intent(this, LoginActivity.class);

        } else {
            mainIntent = new Intent(this, MainActivity.class);
        }
        startActivity(mainIntent);
        finish();
    }

    @Override
    public void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
