package com.hongsou.douguoshouyin.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.StatisticsRankingsBean;

import java.util.List;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/26
 * <p>
 * @desc
 */

public class StatisticsAdapter extends BaseQuickAdapter<StatisticsRankingsBean, BaseViewHolder> {

    public StatisticsAdapter(List<StatisticsRankingsBean> data) {
        super(R.layout.module_recycle_item_statistics_ranking, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StatisticsRankingsBean item) {
        helper.setText(R.id.tv_name, item.getFoodProductsName() + "(" + item.getStandardName() + ")")
                .setText(R.id.tv_count, item.getFoodProductsCount());
    }
}
