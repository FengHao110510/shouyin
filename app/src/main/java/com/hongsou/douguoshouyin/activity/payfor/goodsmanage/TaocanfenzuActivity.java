package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.AddFenzuSinglefoodAdapter;
import com.hongsou.douguoshouyin.adapter.AddTaocanSinglefoodAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;

import java.util.List;

//判断是哪里来的 套餐-选择套餐分组   或者 套餐-套餐分组
public class TaocanfenzuActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.rv_payfor_taocanfenzu_zuhe)
    RecyclerView rvPayfortaocanfenzuZuhe;
    @BindView(R.id.tv_payfor_taocanfenzu_add)
    TextView tvPayfortaocanfenzuAdd;



    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_taocanfenzu;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("套餐分组");

    }

    @Override
    public void initView() {
        //判断是 选择页面 还是 添加页面  选择页面将右上角的学则按钮显示 还有recyclerView中的样式改变
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_titlebar_right, R.id.tv_payfor_taocanfenzu_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_right:
                break;
            case R.id.tv_payfor_taocanfenzu_add:
                //跳转到添加分组页面
                Intent addIntent = new Intent(this, AddFenzuActivity.class);
                startActivityForResult(addIntent, 1);
                break;
            default:
                break;
        }
    }

    //=================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
