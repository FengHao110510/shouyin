package douguoshouyin.douguoshouyin.activity.mine;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;
import douguoshouyin.douguoshouyin.tool.Global;

public class CashierTSActivity extends BaseActivity {


    @BindView(R.id.tv_mine_cashier_ts_yuyin_icon)
    TextView tvMineCashierTsYuyinIcon;

    @Override
    public int intiLayout() {
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
