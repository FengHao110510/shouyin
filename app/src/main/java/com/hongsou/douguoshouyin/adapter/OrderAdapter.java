package com.hongsou.douguoshouyin.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.OrderBean;
import com.hongsou.douguoshouyin.javabean.RootBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：冯大鱼
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：2018/7/11 0011
 * <p>
 * 描述：订单列表adapter
 * <p>
 * 修订历史：
 */


public class OrderAdapter extends BaseQuickAdapter<RootBean<OrderBean>,BaseViewHolder> {
    public Typeface typeface;


    public OrderAdapter(int layoutResId, @Nullable List<RootBean<OrderBean>> data) {
        super(layoutResId, data);
        initIconfont();
    }

    @Override
    protected void convert(BaseViewHolder helper, RootBean<OrderBean> item) {
//        helper.setImageResource(R.id.iv_item_turnover_order_img,).setText(R.id.tv_item_turnover_order_ordernum,)
//                .setText(R.id.tv_item_turnover_order_time,).setTypeface(typeface,R.id.tv_item_turnover_order_you);
    }
    /**
     * 设置图片
     */
    private void initIconfont() {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(mContext.getAssets(), "iconfont.ttf");
        }

    }

    public void setIconFont(TextView[] tv) {
        for (int i = 0; i < tv.length; i++) {
            tv[i].setTypeface(typeface);
        }
    }
}
