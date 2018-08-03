package com.hongsou.douguoshouyin.activity.mine;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;

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

    }

    @OnClick(R.id.tv_mine_authorize_submit)
    public void onViewClicked() {

    }

    //================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}