package com.hongsou.douguoshouyin.javabean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/21 0021
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


public class GroupMultiItemEntity implements MultiItemEntity {
    public static final int TITLE = 1;
    public static final int CONTENT = 2;
    private int itemType;
    private GroupTitleBean groupTitleBean;

    public GroupTitleBean getGroupTitleBean() {
        return groupTitleBean;
    }

    public void setGroupTitleBean(GroupTitleBean groupTitleBean) {
        this.groupTitleBean = groupTitleBean;
    }

    public GroupContentBean getGroupContentBean() {
        return groupContentBean;
    }

    public void setGroupContentBean(GroupContentBean groupContentBean) {
        this.groupContentBean = groupContentBean;
    }

    private GroupContentBean groupContentBean;

    public GroupMultiItemEntity(int itemType,GroupTitleBean groupTitleBean) {
        this.itemType = itemType;
        this.groupTitleBean = groupTitleBean;
    }
    public GroupMultiItemEntity(int itemType,GroupContentBean groupContentBean) {
        this.itemType = itemType;
        this.groupContentBean = groupContentBean;

    }
    @Override
    public int getItemType() {
        return itemType;
    }
}

