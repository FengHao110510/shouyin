package com.hongsou.douguoshouyin.activity.payfor.createorder;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.db.SelectMealEntity;
import com.hongsou.douguoshouyin.javabean.FoodBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @copyright: 鸿搜网络公司 版权所有
 * <p>
 * @author: lpc
 * <p>
 * @date：2018/7/16 <p>
 * @desc：开单流程，选择组合套餐页面
 */
public class CreateOrderSelectGroupActivity extends BaseActivity {

    /**
     * 餐品数据
     */
    private List<FoodBean.DataBean> mFoodBeanList;
    /**
     * 全部餐品数据
     */
    private List<FoodBean.DataBean> mFinalFoodBeanList;
    /*
     * 购物车中的餐品集合
     */
    private List<SelectMealEntity> mSelectMealEntities;
    private LayoutInflater mInflater;


    @Override
    public int initLayout() {
        return R.layout.module_activity_create_order;
    }

    @Override
    protected void init() {
        mInflater = LayoutInflater.from(CreateOrderSelectGroupActivity.this);
        if (mSelectMealEntities == null){
            mSelectMealEntities = new ArrayList<>();
        }
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


}
