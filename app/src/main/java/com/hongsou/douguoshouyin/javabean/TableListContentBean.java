package com.hongsou.douguoshouyin.javabean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongsou.douguoshouyin.adapter.TableAdapter;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/17 0017
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


public class TableListContentBean implements MultiItemEntity {


    /**
     * shopNumber : 53000019990814726X
     * tabletableNumber : quis cillum ut
     * pedestal : 2
     * number : 50
     */

    private String shopNumber;
    private String tabletableNumber;
    private int pedestal;
    private int number;

    public TableListContentBean(String shopNumber, String tabletableNumber, int pedestal, int number) {
        this.shopNumber = shopNumber;
        this.tabletableNumber = tabletableNumber;
        this.pedestal = pedestal;
        this.number = number;
    }


    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getTabletableNumber() {
        return tabletableNumber;
    }

    public void setTabletableNumber(String tabletableNumber) {
        this.tabletableNumber = tabletableNumber;
    }

    public int getPedestal() {
        return pedestal;
    }

    public void setPedestal(int pedestal) {
        this.pedestal = pedestal;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getItemType() {
        return TableAdapter.TYPE_TABLE_CONTENT;
    }
}
