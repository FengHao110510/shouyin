package com.hongsou.douguoshouyin.activity.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.javabean.ReplacePasswordBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 修改密码页面
 */
public class ReplacePasswordActivity extends BaseActivity {


    @BindView(R.id.et_mine_replace_oldpassword)
    EditText etMineReplaceOldpassword;
    @BindView(R.id.et_mine_replace_newpassword)
    EditText etMineReplaceNewpassword;
    @BindView(R.id.et_mine_replace_aginpassword)
    EditText etMineReplaceAginpassword;
    @BindView(R.id.bt_mine_replace_over)
    Button btMineReplaceOver;

    private String oldPassWord;//旧密码
    private String newPassWord;//新密码
    private String aginPassWord;//再次密码

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_replace_password;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("修改密码");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_mine_replace_over)
    public void onViewClicked() {
        //修改密码
        over();
    }

    /**
     * 判断密码是否填写
     */
    private void over() {
        oldPassWord = etMineReplaceOldpassword.getText().toString();
        newPassWord = etMineReplaceNewpassword.getText().toString();
        aginPassWord = etMineReplaceAginpassword.getText().toString();

        if (TextUtils.isEmpty(oldPassWord)) {
            ToastUtil.showToast("请输入原密码");
        } else if (!oldPassWord.equals(Global.getSpGlobalUtil().getPassword())) {
            ToastUtil.showToast("与原密码不一致，请重新输入");
        } else if (TextUtils.isEmpty(newPassWord)) {
            ToastUtil.showToast("请输入新密码");
        } else if (TextUtils.isEmpty(aginPassWord) || !aginPassWord.equals(newPassWord)) {
            ToastUtil.showToast("两次新密码不一致，请重新输入密码");
        } else if (newPassWord.length() < 6 || newPassWord.length() > 18) {
            ToastUtil.showToast("新密码应该在6-18位之间");
        } else {
            toOver();
        }
    }

    /**
     * 修改完成接口
     */
    private void toOver() {
        showLoadingDialog();
        HttpFactory.post().url(ApiConfig.REPLACE_PASSWORD)
                .addParams("userName", Global.getSpGlobalUtil().getUserName())
                .addParams("passWord", Global.getSpGlobalUtil().getPassword())
                .addParams("updatePassWord", aginPassWord)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                Log.e(TAG, "onResponse: " + response);

                ReplacePasswordBean replacePasswordBean = new Gson().fromJson(response, ReplacePasswordBean.class);
                if (replacePasswordBean.getCode() == 1000) {
                    ToastUtil.showToast(replacePasswordBean.getMsg());
                    Global.getSpGlobalUtil().setPassword(aginPassWord);
                } else {
                    ToastUtil.showToast(replacePasswordBean.getMsg());
                }

            }
        });
    }


    //==========================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
