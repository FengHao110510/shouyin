package com.hongsou.douguoshouyin.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.javabean.FoodBean;

import java.util.List;

/**
 * @author lpc
 * <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/16
 * <p>
 * @desc
 */

public class CreateOrderAdapter extends BaseQuickAdapter<FoodBean, BaseViewHolder> {

    public CreateOrderAdapter(int layoutResId, @Nullable List<FoodBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FoodBean item) {

    }
}
