package com.hongsou.douguoshouyin.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.db.SelectMealEntity;
import com.hongsou.douguoshouyin.http.ApiConfig;

import java.math.BigDecimal;
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
        BigDecimal bigDecimal = new BigDecimal(item.getFoodPrice()).multiply(new BigDecimal(item.getFoodProductsCount()));
        helper.setText(R.id.tv_food_price, bigDecimal.setScale(2).toString());
        helper.setText(R.id.tv_food_count, item.getFoodProductsCount() + "");
        String imgUrl = item.getFoodProductPicture();
        if ("1".equals(foodType)) {
            // 单品
            helper.setText(R.id.tv_food_name, item.getFoodName() + "(" + item.getStandardName() + ")");
        } else {
            // 固定套餐
            helper.setText(R.id.tv_food_name, item.getFoodName());
        }

        if (!TextUtils.isEmpty(imgUrl)){
            if (imgUrl.contains("--")) {
                String[] split = imgUrl.split("--");
                imgUrl = split[0];
            }
            if (imgUrl.contains(Constant.IMG_REPLACE1) || imgUrl.contains(Constant.IMG_REPLACE2)) {
                imgUrl = imgUrl.substring(imgUrl.indexOf("shopPic/"), imgUrl.length());
            }
        }
        Glide.with(mContext).load(ApiConfig.IMG_URL + imgUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.dg_logo)
                .into((ImageView) helper.getView(R.id.iv_food_img));
    }

}
