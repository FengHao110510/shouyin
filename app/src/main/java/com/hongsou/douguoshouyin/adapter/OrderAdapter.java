package com.hongsou.douguoshouyin.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.OrderBean;
import com.hongsou.douguoshouyin.javabean.RootBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：冯大鱼
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：2018/7/11 0011
 * <p>
 * 描述：订单列表adapter
 * <p>
 * 修订历史：
 */


public class OrderAdapter extends BaseQuickAdapter<OrderBean.DataBean.ResultBean,BaseViewHolder> {


    public OrderAdapter(int layoutResId, @Nullable List<OrderBean.DataBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean.DataBean.ResultBean item) {
        helper.setText(R.id.tv_item_turnover_order_ordernum,item.getBatch())
                .setText(R.id.tv_item_turnover_order_orderSource,item.getOrderSource())
                .setText(R.id.tv_item_turnover_order_time,item.getEndTime())
                .setText(R.id.tv_item_turnover_order_paystate,item.getOrderType())
                .setText(R.id.tv_item_turnover_order_jine,"¥"+item.getOrderAmount());
    }
}
