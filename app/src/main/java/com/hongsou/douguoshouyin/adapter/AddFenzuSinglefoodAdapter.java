package com.hongsou.douguoshouyin.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/21 0021
 * 描述：  添加分组中的商品显示
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


public class AddFenzuSinglefoodAdapter extends BaseQuickAdapter<SingleFoodsBean, BaseViewHolder> {
    EditText tvItemAddfenzuSinglefoodsNum;

    public AddFenzuSinglefoodAdapter(int layoutResId, @Nullable List<SingleFoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, SingleFoodsBean item) {
        helper.setText(R.id.tv_item_addfenzu_singlefoods_name, item.getSingleProductName() + "(" + item.getStandardName() + ")")
                .setText(R.id.tv_item_addfenzu_singlefoods_count, item.getSingleQuantity() + "")
                .addOnClickListener(R.id.tv_item_addfenzu_singlefoods_del);
        tvItemAddfenzuSinglefoodsNum = helper.getView(R.id.tv_item_addfenzu_singlefoods_num);
        tvItemAddfenzuSinglefoodsNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //通过接口回调将数据传递到Activity中
                mTextListener.onTextChanged(helper.getLayoutPosition(), s.toString(),"");
            }
        });
    }

    private AddFenzuSinglefoodAdapter.onTextChangeListener mTextListener;

    public interface onTextChangeListener {
        void onTextChanged(int pos, String minGroup,String sell);
    }

    //设置自定义接口成员变量
    public void setOnTextChangeListener(AddFenzuSinglefoodAdapter.onTextChangeListener onTextChangeListener) {
        this.mTextListener = onTextChangeListener;
    }
}