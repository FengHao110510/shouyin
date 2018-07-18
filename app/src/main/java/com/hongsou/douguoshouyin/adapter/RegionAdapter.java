package com.hongsou.douguoshouyin.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.RegionListBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/16 0016
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


public class RegionAdapter extends BaseQuickAdapter<RegionListBean.DataBean, BaseViewHolder> {
    private Typeface typeface;

    public RegionAdapter(int layoutResId, @Nullable List<RegionListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RegionListBean.DataBean item) {
        helper.setText(R.id.tv_item_payfor_region_name, item.getRegionName())
                .addOnClickListener(R.id.iv_item_payfor_region_add_del_icon)
                .addOnClickListener(R.id.iv_item_payfor_region_add_icon)
                .addOnClickListener(R.id.tv_item_payfor_region_add_del);
    }

}
