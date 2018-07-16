package com.hongsou.douguoshouyin.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;

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
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TurnoverAdapter(List<TurnoverMultipleItem> data) {
        super(data);
        //必须绑定type和layout的关系
        addItemType(TurnoverMultipleItem.FIRST_TYPE, R.layout.module_item_turnover_turnover_first);
        addItemType(TurnoverMultipleItem.SECOND_TYPE, R.layout.module_item_turnover_turnover_second);
    }

    @Override
    protected void convert(BaseViewHolder helper, TurnoverMultipleItem item) {
        switch (item.getItemType()) {
            case TurnoverMultipleItem.FIRST_TYPE:

                break;
            case TurnoverMultipleItem.SECOND_TYPE:
                break;

        }
    }
}
