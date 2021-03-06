package com.hongsou.douguoshouyin.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.javabean.FoodBean;

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

public class CreateOrderAdapter extends BaseQuickAdapter<FoodBean.DataBean, BaseViewHolder> {

    private Typeface mTypeface;

    public CreateOrderAdapter(@Nullable List<FoodBean.DataBean> data) {
        super(R.layout.module_recycle_item_create_order_food, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, FoodBean.DataBean item) {
        mTypeface = Typeface.createFromAsset(mContext.getAssets(), "iconfont.ttf");
        ((TextView) helper.getView(R.id.tv_add)).setTypeface(mTypeface);
        ((TextView) helper.getView(R.id.tv_subtract)).setTypeface(mTypeface);

        helper.addOnClickListener(R.id.tv_add);
        helper.addOnClickListener(R.id.tv_subtract);
        helper.addOnClickListener(R.id.rl_standard);

        // 根据选择的餐品数量是否大于0，决定数量角标和减号按钮是否显示
        helper.setText(R.id.tv_food_count, item.getFoodProductsCount() + "")
                .setText(R.id.tv_tip_count, item.getFoodProductsCount() + "");
        if (item.getFoodProductsCount() <= 0) {
            helper.setVisible(R.id.tv_food_count, false)
                    .setVisible(R.id.tv_subtract, false)
                    .setVisible(R.id.tv_tip_count, false);
        } else {
            helper.setVisible(R.id.tv_food_count, true)
                    .setVisible(R.id.tv_subtract, true)
                    .setVisible(R.id.tv_tip_count, true);
        }
//        String foodType = item.getFoodType();
//        String img_url = "";
//        if ("1".equals(foodType)) {
//            // 单品
//            List<FoodBean.DataBean.ShopStandarListBean> shopStandarList = item.getShopStandarList();
//            if (shopStandarList != null) {
//                helper.setText(R.id.tv_food_price, shopStandarList.get(0).getSell());
//                if (shopStandarList.size() == 1) {
//                    helper.setText(R.id.tv_food_name, item.getSingleProductName() + "(" + shopStandarList.get(0).getStandardName() + ")");
//                    helper.setVisible(R.id.ll_count, true);
//                    helper.setVisible(R.id.rl_standard, false);
//                } else {
//                    helper.setText(R.id.tv_food_name, item.getSingleProductName());
//                    helper.setVisible(R.id.ll_count, false);
//                    helper.setVisible(R.id.rl_standard, true).setText(R.id.tv_select_standard, "选规格");
//                }
//            }
//            if (!TextUtils.isEmpty(item.getFoodProductsPicture())) {
//                img_url = item.getFoodProductsPicture();
//                if (item.getFoodProductsPicture().contains("--")) {
//                    String[] split = item.getFoodProductsPicture().split("--");
//                    img_url = split[0];
//                }
//                if (img_url.contains(Constant.IMG_REPLACE1) || img_url.contains(Constant.IMG_REPLACE2)) {
//                    img_url = img_url.substring(img_url.indexOf("shopPic/"), img_url.length());
//                }
//            }
//        } else if ("0".equals(foodType)) {
//            // 固定套餐
//            helper.setText(R.id.tv_food_name, item.getPackageName());
//            helper.setText(R.id.tv_food_price, item.getPackagePrice());
//            helper.setVisible(R.id.ll_count, true);
//            helper.setVisible(R.id.rl_standard, false);
//            if (!TextUtils.isEmpty(item.getPackagePicture())) {
//                img_url = item.getPackagePicture();
//                if (item.getPackagePicture().contains("--")) {
//                    String[] split = item.getPackagePicture().split("--");
//                    img_url = split[0];
//                }
//                if (img_url.contains(Constant.IMG_REPLACE1) || img_url.contains(Constant.IMG_REPLACE2)) {
//                    img_url = img_url.substring(img_url.indexOf("shopPic/"), img_url.length());
//                }
//            }
//        } else if ("2".equals(foodType)) {
//            // 组合套餐
//            helper.setText(R.id.tv_food_name, item.getGroupPackageName());
//            helper.setText(R.id.tv_food_price, item.getGroupPackagePrice());
//            helper.setVisible(R.id.ll_count, false);
//            helper.setVisible(R.id.rl_standard, true).setText(R.id.tv_select_standard, "选餐品");
//            // 图片路径
//            if (!TextUtils.isEmpty(item.getPackagePicture())) {
//                img_url = item.getPackagePicture();
//                // 是否有分隔符’-’
//                if (item.getPackagePicture().contains("--")) {
//                    String[] split = item.getPackagePicture().split("--");
//                    img_url = split[0];
//                }
//                // 是否有旧数据中无用的字符链接
//                if (img_url.contains(Constant.IMG_REPLACE1) || img_url.contains(Constant.IMG_REPLACE2)) {
//                    img_url = img_url.substring(img_url.indexOf("shopPic/"), img_url.length());
//                }
//            }
//        }
        helper.setText(R.id.tv_food_name, item.getFoodName())
                .setText(R.id.tv_food_price, item.getFoodPrice())
                .setVisible(R.id.rl_standard, item.isVisible())
                .setVisible(R.id.ll_count, !item.isVisible())
                .setText(R.id.tv_select_standard, item.getBtnStr());
        Glide.with(mContext).load(ApiConfig.IMG_URL + item.getFoodImg())
                .placeholder(R.drawable.dg_logo)
                .skipMemoryCache(false)
                .dontAnimate()
                .error(R.drawable.dg_logo)
                .into((ImageView) helper.getView(R.id.iv_food_img));
    }

}
