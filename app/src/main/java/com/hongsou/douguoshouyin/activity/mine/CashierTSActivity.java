package com.hongsou.douguoshouyin.activity.mine;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.tool.Global;

/**
 * 收银设置  语音推送设置
 */
public class CashierTSActivity extends BaseActivity {


    @BindView(R.id.tv_mine_cashier_ts_yuyin_icon)
    TextView tvMineCashierTsYuyinIcon;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_cashier_ts;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("推送设置");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvMineCashierTsYuyinIcon});
        if (Global.getSpGlobalUtil().getSpeechVoice()) {

            tvMineCashierTsYuyinIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));

        } else {
            tvMineCashierTsYuyinIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
        }
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.tv_mine_cashier_ts_yuyin_icon)
    public void onViewClicked() {
        if (Global.getSpGlobalUtil().getSpeechVoice()) {
            Global.getSpGlobalUtil().setSpeechVoice(false);
            tvMineCashierTsYuyinIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));

        } else {
            Global.getSpGlobalUtil().setSpeechVoice(true);
            tvMineCashierTsYuyinIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));

        }

    }

    //======================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
