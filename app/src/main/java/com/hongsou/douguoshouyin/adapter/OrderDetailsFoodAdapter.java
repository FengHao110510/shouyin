package com.hongsou.douguoshouyin.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.OrderDetailBean;
import com.hongsou.douguoshouyin.javabean.OrderFoodBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/14 0014
 * 描述：
 * 修订历史：
 * ┌─┐       ┌─┐
 * ┌──┘ ┴───────┘ ┴──┐
 * │                 │
 * │       ───       │
 * │  ─┬┘       └┬─  │
 * │                 │
 * │       ─┴─       │
 * │                 │
 * └───┐         ┌───┘
 * │         │
 * │         │
 * │         │
 * │         └──────────────┐
 * │                        │
 * │                        ├─┐
 * │                        ┌─┘
 * │                        │
 * └─┐  ┐  ┌───────┬──┐  ┌──┘
 * │ ─┤ ─┤       │ ─┤ ─┤
 * └──┴──┘       └──┴──┘
 * 神兽保佑
 * 代码无BUG!
 */


public class OrderDetailsFoodAdapter extends BaseQuickAdapter<OrderFoodBean, BaseViewHolder> {


    public OrderDetailsFoodAdapter(int layoutResId, @Nullable List<OrderFoodBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, OrderFoodBean item) {
        helper.setText(R.id.tv_item_orderdetail_food_name,item.getFoodName())
                .setText(R.id.tv_item_orderdetail_food_count,item.getFoodCount())
                .setText(R.id.tv_item_orderdetail_food_price,item.getFoodPrice());

    }
}
