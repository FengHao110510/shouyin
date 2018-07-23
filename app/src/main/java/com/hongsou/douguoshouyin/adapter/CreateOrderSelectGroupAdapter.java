package com.hongsou.douguoshouyin.adapter;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.db.FoodZuheTaocanXQ;

import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */

public class CreateOrderSelectGroupAdapter extends BaseQuickAdapter<List<FoodZuheTaocanXQ>, BaseViewHolder> {

    private Typeface mTypeface;
    private int pos = -1;

    public CreateOrderSelectGroupAdapter(@Nullable List<List<FoodZuheTaocanXQ>> data) {
        super(R.layout.module_recycle_item_group_food_1, data);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, List<FoodZuheTaocanXQ> item) {
        mTypeface = Typeface.createFromAsset(mContext.getAssets(), "iconfont.ttf");
        ((TextView) helper.getView(R.id.tv_selected)).setTypeface(mTypeface);
        StringBuffer name = new StringBuffer();
        for (int i = 0; i < item.size(); i++) {
            name.append(item.get(i).getSingleProductName() + "(" + item.get(i).getStandardName() + ")*" + item.get(i).getFoodProductsCount());
            if (i < (item.size() - 1)) {
                name.append("+");
            }
        }
        helper.setText(R.id.tv_food_name, name.toString());

        if (pos == helper.getLayoutPosition()) {
            ((TextView) helper.getView(R.id.tv_selected)).setTextColor(mContext.getResources().getColor(R.color.red));
            helper.setText(R.id.tv_selected, R.string.icon_check_selected);
        } else {
            ((TextView) helper.getView(R.id.tv_selected)).setTextColor(mContext.getResources().getColor(R.color.tip_text_black));
            helper.setText(R.id.tv_selected, R.string.icon_check_normal);
        }

        helper.addOnClickListener(R.id.tv_selected);
    }


    public void setSelect(int position) {
        pos = position;
    }
}
