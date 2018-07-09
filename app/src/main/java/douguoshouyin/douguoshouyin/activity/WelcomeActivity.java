package douguoshouyin.douguoshouyin.activity;


import android.content.Intent;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.activity.login.LoginActivity;
import douguoshouyin.douguoshouyin.base.BaseActivity;

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
    public int intiLayout() {
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
