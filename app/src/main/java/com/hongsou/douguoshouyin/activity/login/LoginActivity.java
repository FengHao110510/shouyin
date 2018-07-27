package com.hongsou.douguoshouyin.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hongsou.douguoshouyin.activity.MainActivity;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.LoginBean;
import com.hongsou.douguoshouyin.javabean.PayCodeBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.tool.DeviceUtils;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import okhttp3.Call;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_login_user)
    EditText etLoginUser;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;
    @BindView(R.id.tv_login_forget)
    TextView tvLoginForget;
    @BindView(R.id.tv_login_regist)
    TextView tvLoginRegist;
    @BindView(R.id.tv_login_check)
    TextView tvLoginCheck;

    private boolean isChecked;//是否记住密码
    private String code;  //设备编号
    private String userName;//用户名

    private String passWord;//密码

    @Override
    protected void init() {
        initView();
        initData();
        initCode();
    }

    @Override
    public int initLayout() {
        return R.layout.module_activity_login;
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvLoginCheck});
        if (Global.getSpGlobalUtil().getCheckPassword()) {
            //将账号密码回显  check 选中
            isChecked = true;
            etLoginUser.setText(Global.getSpGlobalUtil().getUserName());
            etLoginPassword.setText(Global.getSpGlobalUtil().getPassword());
            tvLoginCheck.setTextColor(getResources().getColor(R.color.color_base_yellow));

        } else {
            isChecked = false;
            tvLoginCheck.setTextColor(getResources().getColor(R.color.gray));
        }
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bt_login_login, R.id.tv_login_forget, R.id.tv_login_regist, R.id.tv_login_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //登陆
            case R.id.bt_login_login:
                login();
                break;
            //忘记密码
            case R.id.tv_login_forget:
                Intent forgetIntent = new Intent(this, ForgetActivity.class);
                startActivity(forgetIntent);
                break;
            //注册
            case R.id.tv_login_regist:
                Intent registIntent = new Intent(this, RegistActivity.class);
                startActivity(registIntent);
                break;
            case R.id.tv_login_check:
                if (isChecked) {
                    tvLoginCheck.setTextColor(getResources().getColor(R.color.gray));
                    isChecked = false;

                } else {
                    tvLoginCheck.setTextColor(getResources().getColor(R.color.color_base_yellow));
                    isChecked = true;
                }
                break;
        }
    }


    //登陆
    private void login() {

        userName = etLoginUser.getText().toString();

        passWord = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            ToastUtil.showToast("请输入账号");
        } else if (TextUtils.isEmpty(passWord) || !isPasswordValid(passWord)) {
            ToastUtil.showToast("请输入正确密码 6-18位");
        } else if (TextUtils.isEmpty(code)) {
            ToastUtil.showToast("获取设备编号失败");
        } else {
            toLogin();
        }


    }

    //登录接口

    private void toLogin() {
        if (Global.getIPAddress(this) == null) {
            ToastUtil.showToast("您的网络已断开...");
            return;
        }
        showLoadingDialog("加载中...");
        HttpFactory.post().url(ApiConfig.LOGIN)
                .addParams("userName", userName)
                .addParams("passWord", passWord)
                .addParams("ip", Global.getIPAddress(this))
                .addParams("equpmentNumber", Global.getSpGlobalUtil().getCode())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                ToastUtil.showError();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                Log.e(TAG, "onResponse: " + response.toString());
                RootBean<LoginBean> loginBean = new Gson().fromJson(response, new TypeToken<RootBean<LoginBean>>() {
                }.getType());
                if (loginBean.isSuccess()) {
                    LoginBean dataBean = loginBean.getData();
                    payCode(dataBean);
//                    isLogined(dataBean,"","");

                } else {
                    ToastUtil.showToast(loginBean.getMsg());

                }

            }
        });
    }

    /**
     * @param dataBean
     * @author fenghao
     * @date 2018/7/24 0024 下午 16:14
     * @desc 获取支付标识
     */
    private void payCode(final LoginBean dataBean) {

        HttpFactory.get().url(ApiConfig.PAYCODE).addParams("paymentUser", dataBean.getPaymentUser())
                .build().execute(new ResponseCallback<PayCodeBean>(this) {
            @Override
            public void onResponse(PayCodeBean response, int id) {
                Log.e(TAG, "onResponse: " + response.toString());
                if (response.isSuccess()) {
                    isLogined(dataBean, response.getData().getAliCode(), response.getData().getWecharCode());
                } else {
                    ToastUtil.showToast(response.getMsg());
                }

            }
        });
    }

    /**
     * @param dataBean
     * @param aliCode
     * @param wecharCode @return
     * @author fenghao
     * @date 2018/7/9 0009 下午 17:43
     * @desc 将登录数据存储
     */

    private void isLogined(LoginBean dataBean, String aliCode, String wecharCode) {
        ToastUtil.showToast("登陆成功");
        Global.getSpGlobalUtil().setClerkName(dataBean.getClerkName());
        Global.getSpGlobalUtil().setClerkNumber(dataBean.getClerkNumber());
        Global.getSpGlobalUtil().setShopNumber(dataBean.getShopNumber());
        Global.getSpGlobalUtil().setPaymentUser(dataBean.getPaymentUser());
        Global.getSpGlobalUtil().setUserName(userName);
        Global.getSpGlobalUtil().setPassword(passWord);
        Global.getSpGlobalUtil().setAliCode(aliCode);
        Global.getSpGlobalUtil().setWecharCode(wecharCode);

        if (isChecked) {
            Global.getSpGlobalUtil().setCheckPassword(true);
        } else {
            Global.getSpGlobalUtil().setCheckPassword(false);
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
     * 推送别名设置能
     */
    private void initCode() {
        code = DeviceUtils.instace().getUniqueId(this);//获取设备编号
        Global.getSpGlobalUtil().setCode(code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
