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
     * shopNumber : 340000198009023896
     * logGid : c6FDf7D8-e31B-Eba1-305A-76FBCdfCF449
     * tabletableNumber : F6C5C3AE-2d1c-BA6e-69F5-BbdeEedcc4d4
     * pedestal : 4
     * number : 5
     * regionNumber : f32ece98-fe8E-A
     */

    private String shopNumber;
    private String logGid;
    private String tabletableNumber;
    private int pedestal;
    private int number;
    private String regionNumber;
    private boolean selectFlag;
    private String qrCodeLink;


    public TableListContentBean(String shopNumber, String logGid, String tabletableNumber
            , int pedestal, int number, String regionNumber, boolean selectFlag
    ,String qrCodeLink) {
        this.shopNumber = shopNumber;
        this.logGid = logGid;
        this.tabletableNumber = tabletableNumber;
        this.pedestal = pedestal;
        this.number = number;
        this.regionNumber = regionNumber;
        this.selectFlag = selectFlag;
        this.qrCodeLink = qrCodeLink;
    }


    public String getQrCodeLink() {
        return qrCodeLink;
    }

    public void setQrCodeLink(String qrCodeLink) {
        this.qrCodeLink = qrCodeLink;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getLogGid() {
        return logGid;
    }

    public void setLogGid(String logGid) {
        this.logGid = logGid;
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

    public String getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(String regionNumber) {
        this.regionNumber = regionNumber;
    }

    public boolean isSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(boolean selectFlag) {
        this.selectFlag = selectFlag;
    }

    @Override
    public int getItemType() {
        return TableAdapter.TYPE_TABLE_CONTENT;
    }
}
