package douguoshouyin.douguoshouyin.activity.payfor.kaidan;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;

public class KaidanActivity extends BaseActivity {


    @Override
    public int intiLayout() {
        return R.layout.module_activity_payfor_kaidan;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("开单");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.tv_payfor_kaidan_qushoukuan_bt)
    public void onViewClicked() {
        //跳转到订单详情页面
        Intent detailIntent = new Intent(this,KaidanDetialsActivity.class);
        startActivity(detailIntent);

    }

    //===================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
