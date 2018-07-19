package com.hongsou.douguoshouyin.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.javabean.ShopStandarList;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/19 0019
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


public class ShopStandarAdapter extends BaseQuickAdapter<ShopStandarList, BaseViewHolder> {
    List<ShopStandarList> data;
    EditText etItemStandarName;
    EditText etItemStandarSell;

    public ShopStandarAdapter(int layoutResId, @Nullable List<ShopStandarList> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, ShopStandarList item) {
        helper.setText(R.id.et_item_standar_name, item.getStandardName())
                .setText(R.id.et_item_standar_sell, item.getSell())
                .setText(R.id.tv_item_standar_num, (helper.getLayoutPosition() + 1) + "")
                .addOnClickListener(R.id.tv_item_standar_num)
                .addOnClickListener(R.id.tv_item_standar_del);

        etItemStandarName = helper.getView(R.id.et_item_standar_name);
        etItemStandarSell = helper.getView(R.id.et_item_standar_sell);

        etItemStandarName.addTextChangedListener(new TextWatcher() {
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
        etItemStandarSell.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //通过接口回调将数据传递到Activity中
                mTextListener.onTextChanged(helper.getLayoutPosition(),"" ,editable.toString());
            }
        });
    }

    private onTextChangeListener mTextListener;

    public interface onTextChangeListener {
        void onTextChanged(int pos, String standardName,String sell);
    }

    //设置自定义接口成员变量
    public void setOnTextChangeListener(onTextChangeListener onTextChangeListener) {
        this.mTextListener = onTextChangeListener;
    }


}
