package com.hongsou.douguoshouyin.activity.mine;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.AuthorizeBean;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthorizeActivity extends BaseActivity {

    @BindView(R.id.et_mine_authorize_num)
    EditText etMineAuthorizeNum;
    @BindView(R.id.tv_mine_authorize_icon)
    TextView tvMineAuthorizeIcon;
    @BindView(R.id.tv_mine_authorize_submit)
    TextView tvMineAuthorizeSubmit;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_authorize;
    }

    @Override
    protected void init() {
        initData();
        initBack();
        initTitle("豆果收银授权");
        setIconFont(new TextView[]{tvMineAuthorizeIcon});
    }

    @Override
    public void initData() {
        //查询商户编号
        HttpFactory.get().url(ApiConfig.GET_SHOP_PAYMENT_USER).addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<AuthorizeBean>(this) {
            @Override
            public void onResponse(AuthorizeBean response, int id) {
                if (response.isSuccess()) {
                    etMineAuthorizeNum.setText(response.getData().getPaymentUser());
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    @OnClick(R.id.tv_mine_authorize_submit)
    public void onViewClicked() {
        String regex = "^[A-Za-z0-9]+$";
        if (!etMineAuthorizeNum.getText().toString().matches(regex)) {
            ToastUtil.showToast("只能包含数字或字母");
            return;
        } else {
            submit(etMineAuthorizeNum.getText().toString());
        }
    }


    /**
     * @param s 修改的商户编号‘
     * @author fenghao
     * @date 2018/8/3 0003 下午 19:22
     * @desc 修改商户编号
     */
    private void submit(String s) {
        HttpFactory.post().url(ApiConfig.APP_SHOP_AUTHORIZATION)
                .addParams("shopNumber", getShopNumber())
                .addParams("paymentUser", s)
                .build().execute(new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("修改成功");

                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    //================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}