package douguoshouyin.douguoshouyin.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;

public class AddNosaomaActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.tv_mine_addnosaoma_wancheng)
    TextView tvMineAddnosaomaWancheng;

    @Override
    public int intiLayout() {
        return R.layout.module_activity_mine_add_nosaoma;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent.getIntExtra("flag",0)==1){
            initTitle("添加套餐商品");

        }else if (intent.getIntExtra("flag",0)==2){
            initTitle("不参与扫码点餐的商品");
        }
        tvTitlebarRight.setVisibility(View.VISIBLE);
        tvTitlebarRight.setText("全选");
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_titlebar_right, R.id.tv_mine_addnosaoma_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_right:
                break;
            case R.id.tv_mine_addnosaoma_wancheng:
                finishActivity();
                break;
        }
    }
}