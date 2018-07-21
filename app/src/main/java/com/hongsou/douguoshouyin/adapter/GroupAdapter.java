package com.hongsou.douguoshouyin.adapter;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.GroupMultiItemEntity;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/21 0021
 * 描述：分组适配器
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


public class GroupAdapter extends BaseMultiItemQuickAdapter<GroupMultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GroupAdapter(List data) {
        super(data);
        //必须绑定type和layout的关系
        addItemType(GroupMultiItemEntity.TITLE, R.layout.module_item_taocanfenzu_showlist_title);
        addItemType(GroupMultiItemEntity.CONTENT, R.layout.module_item_taocanfenzu_showlist_content);

    }


    @Override
    protected void convert(BaseViewHolder helper, GroupMultiItemEntity item) {

        switch (helper.getItemViewType()) {
            case GroupMultiItemEntity.TITLE:
                helper.setText(R.id.tv_item_taocanfenzu_shoulist_title_name, item.getGroupTitleBean().getGroupName())
                        .setText(R.id.tv_item_taocanfenzu_shoulist_title_choose, item.getGroupTitleBean().getGroupCount())
                        .addOnClickListener(R.id.ll_item_taocanfenzu_shoulist_title_del);
                break;
            case GroupMultiItemEntity.CONTENT:
                helper.setText(R.id.tv_item_taocanfenzu_shoulist_content_name, item.getGroupContentBean().getFoodFullName() + "(" + item.getGroupContentBean().getStandard() + ")")
                        .setText(R.id.tv_item_taocanfenzu_shoulist_content_count, item.getGroupContentBean().getFoodProductsCount() + "")
                        .setText(R.id.tv_item_taocanfenzu_shoulist_content_num, item.getGroupContentBean().getMinGroup());
                break;
            default:
                break;
        }


    }


}
