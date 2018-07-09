package douguoshouyin.douguoshouyin.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;

public class NosaomaFoodsActivity extends BaseActivity {


    @BindView(R.id.rv_mine_nosaoma_foods)
    RecyclerView rvMineNosaomaFoods;
    @BindView(R.id.tv_mine_nosaoma_addnosaoma)
    TextView tvMineNosaomaAddnosaoma;

    @Override
    public int intiLayout() {
        return R.layout.module_activity_mine_nosaoma_foods;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("不参与扫码的商品");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.tv_mine_nosaoma_addnosaoma)
    public void onViewClicked() {
        //跳转到添加不参与扫码商品的页面
        Intent addnoIntent = new Intent(this,AddNosaomaActivity.class);
        addnoIntent.putExtra("falg",2);//2  表示从本页面跳转添加商品页面的 1表示从添加单品套餐商品页面跳转的AddTaocanActivity
        startActivity(addnoIntent);
        
    }

    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
