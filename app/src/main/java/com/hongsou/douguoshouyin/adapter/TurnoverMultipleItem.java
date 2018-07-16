package com.hongsou.douguoshouyin.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongsou.douguoshouyin.javabean.TurnoverBean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/16 0016
 * 描述：流水列表多布局 数据不直接传给适配器，而是通过MultiItemEntity来传递
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


public class TurnoverMultipleItem implements MultiItemEntity {
    //用于区分不同的布局
    public static final int FIRST_TYPE = 1;
    public static final int SECOND_TYPE = 2;

    private int itemType;
    //数据源


    public TurnoverBean.DataBean.ResultBean getData() {
        return data;
    }

    public TurnoverMultipleItem(int itemType, TurnoverBean.DataBean.ResultBean data) {
        this.itemType = itemType;
        this.data = data;
    }

    private TurnoverBean.DataBean.ResultBean data;

    @Override
    public int getItemType() {
        return itemType;
    }
}
