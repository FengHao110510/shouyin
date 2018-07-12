package com.hongsou.douguoshouyin.activity.login;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.javabean.SendMsgBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.Apiconfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import okhttp3.Call;

/**
 * 忘记密码
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


    private String yanzhengma;

    @Override
    public int initLayout() {
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
//                timeCount = new TimeCount(60000, 1000, btForgetSendMsg);
//                timeCount.start();
//                timeCount.
                String user = etForgetUser.getText().toString();
                if (TextUtils.isEmpty(user)) {
                    ToastUtil.showToast("请输入账号");
                }else {
                    sendMsg(user);
                }
                break;
            //确认提交
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
     * @param user 用户手机号
     */
    private void sendMsg(String user) {
        showLoadingDialog();
        HttpFactory.post().url(Apiconfig.sendMsg).addParams("phone", user).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                //TODO 验证码存储
                RootBean<SendMsgBean> sendMsgBean = new Gson().fromJson(response, new TypeToken<RootBean<SendMsgBean>>() {
                }.getType());
                if (sendMsgBean.getCode() == 1000) {
                    //成功 后存储二维码
                    yanzhengma = sendMsgBean.getData().getVerificationCode();
                    new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long l) {
                            btForgetSendMsg.setText(l / 1000 + "s后重新获取");
                            btForgetSendMsg.setBackground(getResources().getDrawable(R.drawable.btn_checked));
                            btForgetSendMsg.setClickable(false);
                        }

                        @Override
                        public void onFinish() {
                            btForgetSendMsg.setText("获取验证码");
                            btForgetSendMsg.setBackground(getResources().getDrawable(R.drawable.btn_nomal));
                            btForgetSendMsg.setClickable(true);
                        }
                    }.start();
                }else {
                    ToastUtil.showToast(sendMsgBean.getMsg());
                }

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

        if (TextUtils.isEmpty(user)) {
            ToastUtil.showToast("请输入账号");
        } else if (TextUtils.isEmpty(sendMsg) || !sendMsg.equals(yanzhengma)) {
            ToastUtil.showToast("验证码错误");
        } else if (TextUtils.isEmpty(password1) || !isPasswordValid(password1)) {
            ToastUtil.showToast("请输入正确密码 6-18位");

        } else if (TextUtils.isEmpty(password2) || !isPasswordValid(password2)) {
            ToastUtil.showToast("请确认正确密码 6-18位");

        } else {
            yesForget();
        }
    }

    /**
     * 检测密码位数
     *
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 5 && password.length() < 17;
    }

    /**
     * 忘记密码接口
     */
    private void yesForget() {
        showLoadingDialog("加载中...");
        HttpFactory.post().url(Apiconfig.forgetPassword)
                .addParams("userName", etForgetUser.getText().toString())
                .addParams("passWord", etForgetSetPassword2.getText().toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                //TODO 成功后登录跳转
                BaseBean baseBean = new Gson().fromJson(response, BaseBean.class);
                if (baseBean.getCode() == 1000) {
                    Global.getSpGlobalUtil().setPassword(etForgetSetPassword2.getText().toString());
                    finishActivity();
                }else {
                    ToastUtil.showToast(baseBean.getMsg());
                }
            }
        });
    }
}
