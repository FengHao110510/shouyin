package com.hongsou.douguoshouyin.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/20 0020
 * 描述： 选择单品的页面中的food
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


public class AddTaocanFoodsAdapter extends BaseQuickAdapter<SingleFoodsBean, BaseViewHolder> {
    private Typeface mTypeface;
    //show=0 显示 =1不显示
    private int show;
    public AddTaocanFoodsAdapter(int layoutResId, @Nullable List<SingleFoodsBean> data,int show) {
        super(layoutResId, data);
        this.show = show;

    }

    @Override
    protected void convert(BaseViewHolder helper, SingleFoodsBean item) {
        mTypeface = Typeface.createFromAsset(mContext.getAssets(), "iconfont.ttf");
        ((TextView) helper.getView(R.id.tv_subtract)).setTypeface(mTypeface);
        ((TextView) helper.getView(R.id.tv_add)).setTypeface(mTypeface);

        if (!TextUtils.isEmpty(item.getStandardName())) {
            helper.setText(R.id.tv_food_name, item.getSingleProductName() + "(" + item.getStandardName() + ")");
        } else {
            helper.setText(R.id.tv_food_name, item.getSingleProductName());

        }
        if (show==1){
            helper.setVisible(R.id.ll_count,false);
        }
        helper.setText(R.id.tv_food_price, item.getSingleProductPrice())
                .setText(R.id.tv_food_count, item.getSingleQuantity() + "")
                .addOnClickListener(R.id.tv_subtract)
                .addOnClickListener(R.id.tv_add);
        // 图片路径
        String img_url = "";

        if (!TextUtils.isEmpty(item.getFoodProductsPicture())) {
            img_url = item.getFoodProductsPicture();
            // 是否有分隔符’-’
            if (item.getFoodProductsPicture().contains("--")) {
                String[] split = item.getFoodProductsPicture().split("--");
                img_url = split[0];
            }
            // 是否有旧数据中无用的字符链接
            if (img_url.contains(Constant.IMG_REPLACE1) || img_url.contains(Constant.IMG_REPLACE2)) {
                img_url = img_url.substring(img_url.indexOf("shopPic/"), img_url.length());
            }
        }
        Glide.with(mContext).load(ApiConfig.IMG_URL + img_url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.dg_logo)
                .into((ImageView) helper.getView(R.id.iv_food_img));
    }
}