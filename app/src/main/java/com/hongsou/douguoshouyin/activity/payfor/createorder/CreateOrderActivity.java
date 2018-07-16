package com.hongsou.douguoshouyin.activity.payfor.createorder;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.views.CommonTopBar;
import com.hongsou.douguoshouyin.views.CustomPopupWindow;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：lpc
 * <p>
 * 创建日期：2018/7/16
 * <p>
 * 描述：开单主页面
 * <p>
 * 修订历史：
 */
public class CreateOrderActivity extends BaseActivity {


    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;
    @BindView(R.id.rv_create_order_category)
    RecyclerView mRvCreateOrderCategory;
    @BindView(R.id.rv_create_order_food)
    RecyclerView mRvCreateOrderFood;
    @BindView(R.id.tv_order_money)
    TextView mTvOrderMoney;
    @BindView(R.id.tv_food_count)
    TextView mTvFoodCount;
    @BindView(R.id.tv_select_count)
    TextView mTvSelectCount;
    @BindView(R.id.ll_order_count)
    LinearLayout mLlOrderCount;
    @BindView(R.id.tv_make_money)
    TextView mTvMakeMoney;


    @Override
    public int initLayout() {
        return R.layout.module_activity_create_order;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void initData() {
//        HttpFactory.get()
//                .url(ApiConfig.GET_PAY_ORDER_LIST)
//                .build()
//                .execute(new ResponseCallback<RootBean<>>(this) {
//                    @Override
//                    public void onResponse(RootBean<> response, int id) {
//                       if (response.getCode() == 1000){
//                            rendView(response);
//                       }else {
//                           ToastUtil.showToast(response.getMsg());
//                       }
//                    }
//                });
    }

    /**
     * @param bean 数据源
     * @return
     * @desc 获取数据后，渲染页面
     * @anthor lpc
     * @date: 2018/7/16
     */
    private void rendView(RootBean bean) {

    }

    @OnClick({R.id.ll_order_count, R.id.tv_make_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_order_count:
                // 弹窗展示简单的餐品列表

                break;
            case R.id.tv_make_money:
                //跳转到订单详情页面
                Intent detailIntent = new Intent(this, KaidanDetialsActivity.class);
                startActivity(detailIntent);
                break;
            default:
                break;
        }
    }

    /**
     * @param
     * @desc 弹窗展示点餐列表
     * @anthor lpc
     * @date: 2018/7/16
     */
    private void showFoodListWindow() {
        CustomPopupWindow popupWindow = new CustomPopupWindow.Builder()
                .setContext(this)
                .setContentView(R.layout.module_pop_create_order_food_list)
                .setwidth(LinearLayout.LayoutParams.MATCH_PARENT)
                .setheight(LinearLayout.LayoutParams.WRAP_CONTENT)
                .setFouse(true)
                .setOutSideCancel(true)
                .builder();

        RecyclerView rvPopCreateOrderFood = (RecyclerView) popupWindow.getItemView(R.id.rv_pop_create_order_food);
        rvPopCreateOrderFood.setLayoutManager(new LinearLayoutManager(this));

        // 清空购物车按钮
        popupWindow.getItemView(R.id.tv_clear_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
