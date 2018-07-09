package douguoshouyin.douguoshouyin.tool;


import android.os.CountDownTimer;
import android.widget.Button;

import douguoshouyin.douguoshouyin.R;

/**
 * Created by Administrator on 2018/6/21 0021.  发送验证码的
 */

public class TimeCount extends CountDownTimer {
    Button btn ;
    public TimeCount(long millisInFuture, long countDownInterval, Button btn) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btn.setClickable(false);
        btn.setText(""+millisUntilFinished / 1000 +"s后重新获取");
        btn.setBackgroundResource(R.drawable.btn_checked);
    }

    @Override
    public void onFinish() {
        btn.setText("重新获取验证码");
        btn.setClickable(true);
        btn.setBackgroundResource(R.drawable.btn_nomal);


    }
}
