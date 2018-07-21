package com.hongsou.douguoshouyin.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/20 0020
 * 描述： 选择单品的页面中的food
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


public class AddTaocanFoodsAdapter extends BaseQuickAdapter<SingleFoodsBean, BaseViewHolder> {
    private Typeface mTypeface;

    public AddTaocanFoodsAdapter(int layoutResId, @Nullable List<SingleFoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SingleFoodsBean item) {
        mTypeface = Typeface.createFromAsset(mContext.getAssets(), "iconfont.ttf");
        ((TextView) helper.getView(R.id.tv_subtract)).setTypeface(mTypeface);
        ((TextView) helper.getView(R.id.tv_add)).setTypeface(mTypeface);

        helper.setText(R.id.tv_food_name, item.getSingleProductName() + "(" + item.getStandardName() + ")")
                .setText(R.id.tv_food_price, item.getSingleProductPrice())
                .setText(R.id.tv_food_count, item.getSingleQuantity()+"")
                .addOnClickListener(R.id.tv_subtract)
                .addOnClickListener(R.id.tv_add);
    }
}