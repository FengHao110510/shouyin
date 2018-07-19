package com.hongsou.douguoshouyin.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.javabean.RegionListBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/16 0016
 * 描述：餐品分类列表适配器
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


public class CategoryListAdapter extends BaseQuickAdapter<FoodCategoryBean.DataBean, BaseViewHolder> {
    private Typeface typeface;

    public CategoryListAdapter(int layoutResId, @Nullable List<FoodCategoryBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FoodCategoryBean.DataBean item) {
        String type = "";
        if (item.getCategoryType().equals("0")) {
            type = "单品分类";
        } else if (item.getCategoryType().equals("1")) {
            type = "固定套餐";
        } else if (item.getCategoryType().equals("2")) {
            type = "分组套餐";
        }
        helper.setText(R.id.tv_item_payfor_region_name, item.getCategoryName())
                .setText(R.id.tv_item_payfor_region_type, type)
                .setVisible(R.id.tv_item_payfor_region_type, true)
                .addOnClickListener(R.id.iv_item_payfor_region_add_del_icon)
                .addOnClickListener(R.id.iv_item_payfor_region_add_icon)
                .addOnClickListener(R.id.tv_item_payfor_region_add_del);

    }

}
