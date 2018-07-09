package com.hongsou.douguoshouyin.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;

/**
 * 添加不参与扫码的餐品   添加分组套餐中餐品（这个还没写）   添加单人套餐中的商品
 */
public class AddNosaomaActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.tv_mine_addnosaoma_wancheng)
    TextView tvMineAddnosaomaWancheng;

    @Override
    public int initLayout() {
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
        if (intent.getIntExtra("flag", 0) == 1) {
            initTitle("添加套餐商品");

        } else if (intent.getIntExtra("flag", 0) == 2) {
            initTitle("不参与扫码点餐的商品");
        }
        tvTitlebarRight.setVisibility(View.VISIBLE);
        tvTitlebarRight.setText("全选");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_titlebar_right, R.id.tv_mine_addnosaoma_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_right:
                //全选
                break;
            case R.id.tv_mine_addnosaoma_wancheng:
                //将选择的菜品返回到上个界面显示   走接口？
                finishActivity();
                break;
        }
    }

    //=============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}