package com.hongsou.douguoshouyin.javabean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongsou.douguoshouyin.adapter.TableAdapter;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/17 0017
 * 描述：Table头bean
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


public class TableRegionTitleBean extends AbstractExpandableItem<TableListContentBean> implements MultiItemEntity {
    //区域
    public String regionName;
    //桌台规格
    public int pedestal;
    //区域编号
    public String regionNumber;


    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getPedestal() {
        return pedestal;
    }

    public void setPedestal(int pedestal) {
        this.pedestal = pedestal;
    }

    public String getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(String regionNumber) {
        this.regionNumber = regionNumber;
    }


    public TableRegionTitleBean(String regionName, int pedestal, String regionNumber) {
        this.regionName = regionName;
        this.pedestal = pedestal;
        this.regionNumber = regionNumber;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getItemType() {
        return TableAdapter.TYPE_TABLE_TITLE;
    }
}
