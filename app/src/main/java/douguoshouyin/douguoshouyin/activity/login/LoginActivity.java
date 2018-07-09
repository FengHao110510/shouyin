package douguoshouyin.douguoshouyin.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.activity.MainActivity;
import douguoshouyin.douguoshouyin.base.BaseActivity;
import douguoshouyin.douguoshouyin.base.BaseApplication;
import douguoshouyin.douguoshouyin.http.Apiconfig;
import douguoshouyin.douguoshouyin.http.HttpFactory;
import douguoshouyin.douguoshouyin.tool.ToastUtil;
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

    @Override
    protected void init() {
        initView();
        initData();
    }

    @Override
    public int intiLayout() {
        return R.layout.module_activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.bt_login_login, R.id.tv_login_forget, R.id.tv_login_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //登陆
            case R.id.bt_login_login:
//                login();
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
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
        }
    }


    //登陆
    private void login() {

        String user = etLoginUser.getText().toString();

        String password = etLoginPassword.getText().toString();

        if (user.equals("")) {
            ToastUtil.showToast("请输入账号");
        } else if (password.equals("")) {
            ToastUtil.showToast("请输入密码");
        } else {
            toLogin();
        }


    }

    //登录接口
    private void toLogin() {
        showLoadingDialog("加载中...");
        HttpFactory.post().url(Apiconfig.login).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
//                finishActivity();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getApplication().removeAll();
    }
}
