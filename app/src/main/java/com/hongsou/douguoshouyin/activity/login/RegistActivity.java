package com.hongsou.douguoshouyin.activity.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.LoginBean;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.Apiconfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import okhttp3.Call;

/**
 * 注册页面 没用。。。。
 */
public class RegistActivity extends BaseActivity {

    @BindView(R.id.et_regist_user)
    EditText etRegistUser;
    @BindView(R.id.et_regist_send_msg)
    EditText etRegistSendMsg;
    @BindView(R.id.bt_regist_send_msg)
    Button btRegistSendMsg;
    @BindView(R.id.et_regist_set_password)
    EditText etRegistSetPassword;

    @BindView(R.id.tv_regist_user_agree)
    TextView tvRegistUserAgree;
    @BindView(R.id.bt_regist_regist)
    Button btRegistRegist;
    @BindView(R.id.tv_regist_forget)
    TextView tvRegistForget;
    @BindView(R.id.tv_regist_login)
    TextView tvRegistLogin;
    @BindView(R.id.tv_regist_agree)
    TextView tvRegistAgree;

    private boolean ischecked = false;//判断是否点击 同意按钮

    @Override
    public int initLayout() {
        return R.layout.module_activity_regist;
    }

    @Override
    protected void init() {
        initView();
        initData();
    }


    @Override
    public void initView() {
        tvRegistAgree.setTypeface(typeface);
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bt_regist_send_msg, R.id.tv_regist_agree, R.id.tv_regist_user_agree, R.id.bt_regist_regist, R.id.tv_regist_forget, R.id.tv_regist_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_regist_send_msg:
                //发送验证码接口
                sendMsg();
                break;
            case R.id.tv_regist_user_agree:
                //显示用户协议
                showUserAgreeDialog();
                break;
            case R.id.bt_regist_regist:
                //走注册接口
                regist();
                break;
            //跳转忘记密码页面
            case R.id.tv_regist_forget:
                Intent forgetIntent = new Intent(this, ForgetActivity.class);
                startActivity(forgetIntent);
                finishActivity();
                break;
            //跳转登录页面
            case R.id.tv_regist_login:
                finishActivity();
                break;
            case R.id.tv_regist_agree:
                //判断是否点击同意按钮 点击时变色
                if (!ischecked) {
                    ischecked = true;
                    tvRegistAgree.setText(R.string.icon_anniu_checked);
                } else {
                    ischecked = false;
                    tvRegistAgree.setText(R.string.icon_anniu_nomal);
                }
                break;
        }
    }

    //展示用户协议
    private void showUserAgreeDialog() {
        final View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_useragree, null);
        Display display = this.getWindowManager().getDefaultDisplay();

        int weith = display.getWidth();
        int height = display.getHeight();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(weith * 4 / 5, height * 3 / 4);

        final Dialog agreeDialog = new Dialog(this, R.style.CommonDialog);
        agreeDialog.addContentView(view, layoutParams);
        agreeDialog.setCancelable(true);
        agreeDialog.show();

        TextView tv_regist_dialog_content = view.findViewById(R.id.tv_regist_dialog_content);
        Button bt_regist_dialog_yes = view.findViewById(R.id.bt_regist_dialog_yes);

        bt_regist_dialog_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ischecked = true;
                tvRegistAgree.setText(R.string.icon_anniu_checked);
                agreeDialog.cancel();
            }
        });

    }

    /**
     * 发送验证码接口
     */
    private void sendMsg() {
        showLoadingDialog("加载中...");
        HttpFactory.post().url(Apiconfig.sendMsg).addParams("", "").build().execute(new ResponseCallback<LoginBean>(this,LoginBean.class) {
            @Override
            public void onResponse(LoginBean response, int id) {

            }


        });
    }

    /**
     * 注册
     */
    private void regist() {
        String user = etRegistUser.getText().toString();
        String send = etRegistSendMsg.getText().toString();
        String password = etRegistSetPassword.getText().toString();

        if (user.equals("")) {
            ToastUtil.showToast("请输入账号");
        } else if (password.equals("")) {
            ToastUtil.showToast("请输入密码");
        } else if (send.equals("")) {
            ToastUtil.showToast("请输入验证码");
        } else if (!ischecked) {
            ToastUtil.showToast("请同意用户协议");
        } else {
            goRegist();
        }
    }

    /**
     * 注册接口
     */
    private void goRegist() {
        showLoadingDialog("加载中...");
        HttpFactory.post().url(Apiconfig.regist).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
