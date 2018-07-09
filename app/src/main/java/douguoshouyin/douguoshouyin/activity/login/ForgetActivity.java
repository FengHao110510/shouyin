package douguoshouyin.douguoshouyin.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;
import douguoshouyin.douguoshouyin.http.Apiconfig;
import douguoshouyin.douguoshouyin.http.HttpFactory;
import douguoshouyin.douguoshouyin.tool.TimeCount;
import douguoshouyin.douguoshouyin.tool.ToastUtil;
import okhttp3.Call;

/**
 * Created by Administrator on 2018/6/21 0021.
 */

public class ForgetActivity extends BaseActivity {
    @BindView(R.id.et_forget_user)
    EditText etForgetUser;
    @BindView(R.id.et_forget_send_msg)
    EditText etForgetSendMsg;
    @BindView(R.id.bt_forget_send_msg)
    Button btForgetSendMsg;
    @BindView(R.id.et_forget_set_password)
    EditText etForgetSetPassword;
    @BindView(R.id.et_forget_set_password2)
    EditText etForgetSetPassword2;
    @BindView(R.id.bt_forget_yes)
    Button btForgetYes;
    @BindView(R.id.tv_forget_login)
    TextView tvForgetLogin;

    TimeCount timeCount;

    @Override
    public int intiLayout() {
        return R.layout.module_activity_forget;
    }

    @Override
    protected void init() {
        initView();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bt_forget_send_msg, R.id.bt_forget_yes, R.id.tv_forget_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //发送验证码
            case R.id.bt_forget_send_msg:
                timeCount = new TimeCount(60000, 1000, btForgetSendMsg);
                timeCount.start();
                sendMsg();
                break;
            //确认忘记密码
            case R.id.bt_forget_yes:
                goForget();
                break;
            //跳转登录页面登录
            case R.id.tv_forget_login:
                finishActivity();
                break;
        }
    }

    /**
     * 发送验证码
     */
    private void sendMsg() {
        showLoadingDialog("加载中...");
        HttpFactory.post().url(Apiconfig.sendMsg).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                //TODO 验证码存储
            }
        });
    }

    /**
     * 确认忘记密码
     */
    private void goForget() {
        String user = etForgetUser.getText().toString();
        String sendMsg = etForgetSendMsg.getText().toString();
        String password1 = etForgetSetPassword.getText().toString();
        String password2 = etForgetSetPassword2.getText().toString();

        if (user.equals("")) {
            ToastUtil.showToast("请输入账号");
        } else if (sendMsg.equals("")) {
            ToastUtil.showToast("请输入验证码");

        } else if (password1.equals("")) {
            ToastUtil.showToast("请输入密码");

        } else if (password2.equals("")) {
            ToastUtil.showToast("请确认密码");

        } else {
            yesForget();
        }
    }


    /**
     * 忘记密码接口
     */
    private void yesForget() {
        showLoadingDialog("加载中...");
        HttpFactory.post().url(Apiconfig.forgetPassword).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                //TODO 成功后登录跳转
            }
        });
    }
}
