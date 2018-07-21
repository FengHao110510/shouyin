package com.hongsou.douguoshouyin.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/21 0021
 * 描述：  添加套餐中的商品显示
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


public class AddTaocanSinglefoodAdapter extends BaseQuickAdapter<SingleFoodsBean, BaseViewHolder> {

    public AddTaocanSinglefoodAdapter(int layoutResId, @Nullable List<SingleFoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SingleFoodsBean item) {
        helper.setText(R.id.tv_item_addtaocan_singlefoods_name, item.getSingleProductName() + "(" + item.getStandardName() + ")")
                .setText(R.id.tv_item_addtaocan_singlefoods_price, (Float.valueOf(item.getSingleProductPrice())*item.getSingleQuantity())+"")
                .setText(R.id.tv_item_addtaocan_singlefoods_count, item.getSingleQuantity() + "")
                .addOnClickListener(R.id.tv_item_addtaocan_singlefoods_del);
    }
}