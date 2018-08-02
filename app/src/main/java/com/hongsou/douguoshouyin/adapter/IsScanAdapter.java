package com.hongsou.douguoshouyin.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.javabean.NoScanBean;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/20 0020
 * 描述： 不参与扫码点餐的餐品
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


public class IsScanAdapter extends BaseQuickAdapter<NoScanBean.DataBean.ResultBean, BaseViewHolder> {
    public IsScanAdapter(int layoutResId, @Nullable List<NoScanBean.DataBean.ResultBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, NoScanBean.DataBean.ResultBean item) {

        helper.setText(R.id.tv_item_choose_isscan_name, item.getSingleProductName())
                .setText(R.id.tv_item_choose_isscan_price, item.getPrice())
                .setVisible(R.id.iv_item_choose_isscan_choose, false)
                .setVisible(R.id.tv_item_choose_isscan_del, true)
                .addOnClickListener(R.id.tv_item_choose_isscan_del);


        // 图片路径
        String img_url = "";

        if (!TextUtils.isEmpty(item.getPicture())) {
            img_url = item.getPicture();
            // 是否有分隔符’-’
            if (item.getPicture().contains("--")) {
                String[] split = item.getPicture().split("--");
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
                .into((ImageView) helper.getView(R.id.iv_item_choose_isscan_img));
    }

}