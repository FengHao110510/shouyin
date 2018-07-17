package com.hongsou.douguoshouyin.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.TableListContentBean;
import com.hongsou.douguoshouyin.javabean.TableRegionTitleBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/17 0017
 * 描述：table列表适配器
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


public class TableAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_TABLE_TITLE = 0;
    public static final int TYPE_TABLE_CONTENT = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TableAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_TABLE_TITLE, R.layout.module_item_table_title);
        addItemType(TYPE_TABLE_CONTENT, R.layout.module_item_table_content);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_TABLE_TITLE:
                final TableRegionTitleBean tableRegionTitleBean = (TableRegionTitleBean) item;
                helper.setText(R.id.tv_item_table_title_region,tableRegionTitleBean.getRegionName())
                .setText(R.id.tv_item_table_title_guige,tableRegionTitleBean.getPedestal()+"");
                break;
            case TYPE_TABLE_CONTENT:
                TableListContentBean tableListContentBean = (TableListContentBean) item;
                helper.setText(R.id.tv_item_table_content_pedestal,tableListContentBean.getNumber()+"");
                break;
            default:
                break;
        }
    }
}
