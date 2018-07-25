package com.hongsou.douguoshouyin.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.TurnoverBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/16 0016
 * 描述：流水列表适配器
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


public class TurnoverAdapter extends BaseMultiItemQuickAdapter<TurnoverMultipleItem, BaseViewHolder> {

    public TurnoverAdapter(List<TurnoverMultipleItem> data) {
        super(data);
        //必须绑定type和layout的关系
        addItemType(TurnoverMultipleItem.TYPE_DATE, R.layout.module_item_turnover_turnover_first);
        addItemType(TurnoverMultipleItem.TYPE_INFO, R.layout.module_item_turnover_turnover_second);
    }

    @Override
    protected void convert(BaseViewHolder helper, TurnoverMultipleItem item) {
        TurnoverBean data = item.getData();
        switch (item.getItemType()) {
            case TurnoverMultipleItem.TYPE_DATE:
                helper.setText(R.id.tv_time, data.getTime())
                        .setText(R.id.tv_money, data.getMoney());
                break;
            case TurnoverMultipleItem.TYPE_INFO:
                if (data.getOrderType().equals(""));
                helper.setText(R.id.tv_payment_type, data.getPaymentType())
                        .setText(R.id.tv_payment_state, data.getOrderState())
                        .setText(R.id.tv_time, data.getTradingTime())
                        .setText(R.id.tv_money,data.getPayAmount());
                break;
            default:
                break;
        }
    }
}
