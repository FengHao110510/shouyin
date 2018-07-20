package com.hongsou.douguoshouyin.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.db.SelectMealEntity;

import java.util.List;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/16
 * <p>
 * @desc 收款页面对应适配器
 */

public class CollectMoneyAdapter extends BaseQuickAdapter<SelectMealEntity, BaseViewHolder> {

    public CollectMoneyAdapter(@Nullable List<SelectMealEntity> data) {
        super(R.layout.module_recycle_item_collect_money, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectMealEntity item) {
        String foodType = item.getFoodProductsType();
        helper.setText(R.id.tv_food_price, item.getFoodPrice());
        helper.setText(R.id.tv_food_count, item.getFoodProductsCount() + "");
        if ("1".equals(foodType)) {
            // 单品
            helper.setText(R.id.tv_food_name, item.getFoodName() + "(" + item.getStandardName() + ")");
        } else {
            // 固定套餐
            helper.setText(R.id.tv_food_name, item.getFoodName());
        }
    }

}
