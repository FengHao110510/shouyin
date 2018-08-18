package com.hongsou.douguoshouyin.adapter;

import android.content.Context;
import android.nfc.FormatException;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.db.FoodZuheTaocanXQ;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CircleImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/17 0017
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


public class CreateOrderSelectGroupAdapter2 extends BaseQuickAdapter<FoodBean.DataBean.ListTwoBean, BaseViewHolder> {
    Context context;
    //已选中的集合最终结合
    final List<FoodZuheTaocanXQ> foodZuheTaocanXQList3 = new ArrayList<>();
    //传入的数据
    List<FoodBean.DataBean.ListTwoBean> listTwoBeanList = new ArrayList<>();
    public CreateOrderSelectGroupAdapter2(int layoutResId, @Nullable List<FoodBean.DataBean.ListTwoBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
        this.listTwoBeanList = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, FoodBean.DataBean.ListTwoBean item) {
        //将餐品抽出放入foodZuheTaocanXQList中备用
        final List<FoodZuheTaocanXQ> foodZuheTaocanXQList = new ArrayList<>();
        //已选中的集合
        final List<FoodZuheTaocanXQ> foodZuheTaocanXQList2 = new ArrayList<>();
        for (int i = 0; i < item.getListThree().size(); i++) {
            for (int n = 0; n < item.getListThree().get(i).size(); n++) {
                foodZuheTaocanXQList.add(item.getListThree().get(i).get(n));
            }
        }
        helper.setText(R.id.tv_item_create_order_select_group_name, foodZuheTaocanXQList.get(0).getGroupName())
                .setText(R.id.tv_item_create_order_select_group_mustselect, foodZuheTaocanXQList.size()
                        + "选" + foodZuheTaocanXQList.get(0).getGroupCount());
        int alreadyCount = 0;
        List<View> viewList = new ArrayList<>();
        ViewPager vpItemCreateOrderSelectGroup = helper.getView(R.id.vp_item_create_order_select_group);
        LinearLayout llItemCreateOrderSelectGroupDian = helper.getView(R.id.ll_item_create_order_select_group_dian);
        //小圆点
        final List<ImageView> dotViewLists = new ArrayList<>();

        for (int i = 0; i < foodZuheTaocanXQList.size(); i++) {
            if (foodZuheTaocanXQList.get(i).getAlreadyCount()) {
                alreadyCount++;
            }
            View view = View.inflate(context, R.layout.module_item_select_group_viewpager, null);
            CircleImageView icvSelectGroupViewpagerImg = view.findViewById(R.id.icv_select_group_viewpager_img);
            TextView tvSelectGroupViewpagerName = view.findViewById(R.id.tv_select_group_viewpager_name);
            String img_url = "";

            // 图片路径
            if (!TextUtils.isEmpty(foodZuheTaocanXQList.get(i).getFoodProductsPicture())) {
                img_url = foodZuheTaocanXQList.get(i).getFoodProductsPicture();
                // 是否有分隔符’-’
                if (foodZuheTaocanXQList.get(i).getFoodProductsPicture().contains("--")) {
                    String[] split = foodZuheTaocanXQList.get(i).getFoodProductsPicture().split("--");
                    img_url = split[0];
                }
                // 是否有旧数据中无用的字符链接
                if (img_url.contains(Constant.IMG_REPLACE1) || img_url.contains(Constant.IMG_REPLACE2)) {
                    img_url = img_url.substring(img_url.indexOf("shopPic/"), img_url.length());
                }
            }

            Glide.with(mContext).load(ApiConfig.IMG_URL + img_url)
                    .placeholder(R.drawable.dg_logo)
                    .skipMemoryCache(false)
                    .dontAnimate()
                    .error(R.drawable.dg_logo)
                    .into(icvSelectGroupViewpagerImg);

            tvSelectGroupViewpagerName.setText(foodZuheTaocanXQList.get(i).getSingleProductName()
                    +"("+foodZuheTaocanXQList.get(i).getStandardName()+")");
            viewList.add(view);

            //添加小圆点指示器
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            //为小圆点左右添加间距
            params.leftMargin = 10;
            params.rightMargin = 10;
            //手动给小圆点一个大小
            params.height = 20;
            params.width = 20;
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.img2);
            } else {
                imageView.setBackgroundResource(R.drawable.img1);
            }
            //为LinearLayout添加ImageView
            llItemCreateOrderSelectGroupDian.addView(imageView, params);
            dotViewLists.add(imageView);
        }
        helper.setText(R.id.tv_item_create_order_select_group_already, alreadyCount + "");

        Log.e(TAG, "convert: " + viewList.size());


        //viewpager adapter
        MyPageAdapter adapter = new MyPageAdapter(viewList);
        vpItemCreateOrderSelectGroup.setAdapter(adapter);
        //小圆点
        vpItemCreateOrderSelectGroup.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotViewLists.size(); i++) {
                    if (i == position) {
                        dotViewLists.get(i).setImageResource(R.drawable.img2);
                    } else {
                        dotViewLists.get(i).setImageResource(R.drawable.img1);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //左下角
        final TagFlowLayout tlItemCreateOrderSelectGroupText = helper.getView(R.id.tl_item_create_order_select_group_text);
        final LayoutInflater mInflater = LayoutInflater.from(context);
        final TagAdapter tagAdapter = new TagAdapter<FoodZuheTaocanXQ>(foodZuheTaocanXQList2) {
            @Override
            public View getView(FlowLayout parent, int position, FoodZuheTaocanXQ foodZuheTaocanXQ) {
                View v = (View) mInflater.inflate(R.layout.module_tag_flow_view,
                        tlItemCreateOrderSelectGroupText, false);
                TextView tvTagFlow = v.findViewById(R.id.tv_tag_flow);
                tvTagFlow.setText(foodZuheTaocanXQ.getSingleProductName()
                +"("+foodZuheTaocanXQ.getStandardName()+")");
                return v;
            }
        };
        tlItemCreateOrderSelectGroupText.setAdapter(tagAdapter);

        tlItemCreateOrderSelectGroupText.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                for (int i = 0; i < foodZuheTaocanXQList.size(); i++) {
                    if (foodZuheTaocanXQList2.get(position).getStandard().equals(foodZuheTaocanXQList.get(i).getStandard())) {
                        foodZuheTaocanXQList.get(i).setAlreadyCount(false);
                    }
                }
                for (int i = 0; i < foodZuheTaocanXQList3.size(); i++) {
                    if (foodZuheTaocanXQList2.get(position).getStandard().equals(foodZuheTaocanXQList3.get(i).getStandard())) {
                        foodZuheTaocanXQList3.remove(i);
                    }
                }
                foodZuheTaocanXQList2.remove(position);
                tagAdapter.notifyDataChanged();

                //先判断选中项是否超过了必选
                int count = 0;
                for (int i = 0; i < foodZuheTaocanXQList.size(); i++) {
                    if (foodZuheTaocanXQList.get(i).getAlreadyCount()) {
                        count++;
                    }
                }
                helper.setText(R.id.tv_item_create_order_select_group_already, count + "");

                return false;
            }
        });
        //viewpager点击事件
        adapter.setnCheckListener(new MyPageAdapter.OnCheckListener() {
            @Override
            public void onCheck(int pos) {
                //先判断选中项是否超过了必选
                int count = 0;
                for (int i = 0; i < foodZuheTaocanXQList.size(); i++) {
                    if (foodZuheTaocanXQList.get(i).getAlreadyCount()) {
                        count++;
                    }
                }
                int groupCount = Integer.valueOf(foodZuheTaocanXQList.get(0).getGroupCount());
                if (count == groupCount) {
                    ToastUtil.showToast("已超出可选数量");
                } else {
                    //执行下面的逻辑  已选择 左下角名称
                    foodZuheTaocanXQList2.clear();
                    foodZuheTaocanXQList.get(pos).setAlreadyCount(true);
                    for (int n = 0; n < foodZuheTaocanXQList.size(); n++) {
                        if (foodZuheTaocanXQList.get(n).getAlreadyCount()) {
                            foodZuheTaocanXQList2.add(foodZuheTaocanXQList.get(n));

                        }
                    }
                    boolean isHas = false;
                    //判断是否有重复餐品
                    for (int h=0;h<foodZuheTaocanXQList3.size();h++){
                        if (foodZuheTaocanXQList.get(pos).getStandard().equals(foodZuheTaocanXQList3.get(h).getStandard())){
                            isHas = true;
                            ToastUtil.showToast("已存在此菜品，请重新选择");
                        }
                    }
                    if (!isHas){
                        foodZuheTaocanXQList3.add(foodZuheTaocanXQList.get(pos));
                        helper.setText(R.id.tv_item_create_order_select_group_already, (count + 1) + "");
                    }
                    tagAdapter.notifyDataChanged();
                }
            }
        });


    }

    public List<FoodZuheTaocanXQ> getList() {
        boolean flag=true;
        for (int i=0;i<listTwoBeanList.size();i++){
            int count =0;
            for (int n =0;n<foodZuheTaocanXQList3.size();n++){
                if (listTwoBeanList.get(i).getGroupNumber().equals(foodZuheTaocanXQList3.get(n).getGroupNumber())){
                    count++;
                }
                Log.e(TAG, "getList: "+foodZuheTaocanXQList3.get(n).getSingleProductName()+foodZuheTaocanXQList3.size() );
            }
            int integer = Integer.valueOf(listTwoBeanList.get(i).getListThree().get(0).get(0).getGroupCount());
            if (count<integer){
                ToastUtil.showToast(listTwoBeanList.get(i).getListThree().get(0).get(0).getGroupName()
                        +"中有未选项，请选择");
                flag=false;
                break;
            }
        }

        if (flag){
            return foodZuheTaocanXQList3;
        }
        return null;
    }
}
