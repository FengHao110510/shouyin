package douguoshouyin.douguoshouyin.activity.payfor.payfor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;

public class BackPayActivity extends BaseActivity {


    @BindView(R.id.tv_payfor_backpay_tuikuanjine)
    TextView tvPayforBackpayTuikuanjine;
    @BindView(R.id.tv_payfor_backpay_dingdanhao)
    TextView tvPayforBackpayDingdanhao;
    @BindView(R.id.tv_payfor_backpay_jiaoyishijian)
    TextView tvPayforBackpayJiaoyishijian;
    @BindView(R.id.tv_payfor_backpay_jiaoyijine)
    TextView tvPayforBackpayJiaoyijine;
    @BindView(R.id.tv_payfor_backpay_zhifufangshi)
    TextView tvPayforBackpayZhifufangshi;
    @BindView(R.id.tv_payfor_backpay_zhifuzhuangtai)
    TextView tvPayforBackpayZhifuzhuangtai;
    @BindView(R.id.bt_payfor_backpay_tuikuan)
    Button btPayforBackpayTuikuan;

    @Override
    public int intiLayout() {
        return R.layout.module_activity_payfor_back_pay;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("退款成功");
    }

    @Override
    public void initView() {
    //TODO 初始化数据之前退款接口传的？
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.bt_payfor_backpay_tuikuan)
    public void onViewClicked() {
        finishActivity();
    }

    //---=================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
