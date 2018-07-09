package com.hongsou.douguoshouyin.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;

/**
 * 收银设置页面
 */
public class CashierActivity extends BaseActivity {


    @BindView(R.id.rl_mine_cashier_xiaopiao)
    RelativeLayout rlMineCashierXiaopiao;
    @BindView(R.id.rl_mine_cashier_tuisong)
    RelativeLayout rlMineCashierTuisong;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_cashier;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("收银设置");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.rl_mine_cashier_xiaopiao, R.id.rl_mine_cashier_tuisong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_cashier_xiaopiao:
                //自动打印小票设置
                Intent cashierXPIntent = new Intent(this, CashierXPActivity.class);
                startActivity(cashierXPIntent);
                break;
            case R.id.rl_mine_cashier_tuisong:
                //推送设置
                Intent cashierTSIntent = new Intent(this, CashierTSActivity.class);
                startActivity(cashierTSIntent);
                break;
        }
    }

    //==========================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
