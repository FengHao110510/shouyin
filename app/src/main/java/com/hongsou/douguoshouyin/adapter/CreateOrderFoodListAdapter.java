package com.hongsou.douguoshouyin.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.db.SelectMealEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/16
 * <p>
 * @desc
 */

public class CreateOrderFoodListAdapter extends BaseQuickAdapter<SelectMealEntity, BaseViewHolder> {

    private Typeface mTypeface;

    public CreateOrderFoodListAdapter(@Nullable List<SelectMealEntity> data) {
        super(R.layout.module_recycle_item_create_order_select_food, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, SelectMealEntity item) {
        mTypeface = Typeface.createFromAsset(mContext.getAssets(), "iconfont.ttf");
        ((TextView) helper.getView(R.id.tv_add_select)).setTypeface(mTypeface);
        ((TextView) helper.getView(R.id.tv_subtract_select)).setTypeface(mTypeface);

        helper.addOnClickListener(R.id.tv_add_select);
        helper.addOnClickListener(R.id.tv_subtract_select);

        // 根据选择的餐品数量是否大于0，决定数量角标和减号按钮是否显示
        helper.setText(R.id.tv_food_count, item.getFoodProductsCount() + "");
        BigDecimal bigDecimal = new BigDecimal(item.getFoodPrice()).multiply(new BigDecimal(item.getFoodProductsCount()));
        helper.setText(R.id.tv_food_price, bigDecimal.setScale(1).toString());

        String foodType = item.getFoodProductsType();
        if ("1".equals(foodType)) {
            // 单品
            helper.setText(R.id.tv_food_name, item.getFoodName() + "(" + item.getStandardName() + ")");
        } else {
            // 套餐
            helper.setText(R.id.tv_food_name, item.getFoodName());
        }
    }

}
