package com.hongsou.douguoshouyin.activity.payfor.createorder;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.payfor.createorder.model.CreateOrderModel;
import com.hongsou.douguoshouyin.activity.payfor.createorder.presenter.CreateOrderPresenter;
import com.hongsou.douguoshouyin.activity.payfor.createorder.view.ICreateOrderView;
import com.hongsou.douguoshouyin.adapter.CreateOrderAdapter;
import com.hongsou.douguoshouyin.adapter.CreateOrderFoodListAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.db.SelectMealEntity;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.tool.ScreenUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;
import com.hongsou.douguoshouyin.views.CustomPopupWindow;
import com.hongsou.douguoshouyin.views.tablayout.VerticalTabLayout;
import com.hongsou.greendao.gen.SelectMealEntityDao;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;

/**
 * @copyright: 鸿搜网络公司 版权所有
 * <p>
 * @author: lpc
 * <p>
 * @date：2018/7/16 <p>
 * @desc：开单主页面
 */
public class CreateOrderActivity extends BaseActivity implements ICreateOrderView, BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;
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
    @BindView(R.id.tab_create_category)
    VerticalTabLayout mTabCreateCategory;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;

    private CreateOrderAdapter mCreateOrderAdapter;
    private CreateOrderFoodListAdapter mCreateOrderFoodListAdapter;
    /**
     * 餐品数据
     */
    private List<FoodBean.DataBean> mFoodBeanList;
    /**
     * 全部餐品数据
     */
    private List<FoodBean.DataBean> mFinalFoodBeanList;
    /*
     * 购物车中的餐品集合
     */
    private List<SelectMealEntity> mSelectMealEntities;
    /**
     * 右上角角标
     */
    private List<Badge> mBadgeList;
    private CreateOrderPresenter mPresenter;
    private LayoutInflater mInflater;
    /**
     * 数据库缓存购物车列表
     */
    private SelectMealEntityDao mSelectMealEntityDao;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            for (int i = 0; i < mSelectMealEntities.size(); i++) {
                Log.e(TAG, "handleMessage: " + i);
                mSelectMealEntityDao.insertOrReplace(mSelectMealEntities.get(i));
            }
        }
    };

    @Override
    public int initLayout() {
        return R.layout.module_activity_create_order;
    }

    @Override
    protected void init() {
        mSelectMealEntityDao = BaseApplication.getApplication().getDaoSession().getSelectMealEntityDao();
        mRvCreateOrderFood.setLayoutManager(new LinearLayoutManager(this));
        mPresenter = new CreateOrderPresenter(this, new CreateOrderModel(), this);
        mInflater = LayoutInflater.from(CreateOrderActivity.this);
        // TODO 测试用
//        Global.getSpGlobalUtil().setShopNumber("111");

        mBadgeList = new ArrayList<>();
        // 获取接口数据
        mPresenter.getFoodList();
        mPresenter.getCategory();

        if (mSelectMealEntities == null){
            mSelectMealEntities = new ArrayList<>();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSelectMealEntityDao.deleteAll();
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void onSuccess(Object response) {
        if (response instanceof FoodBean) {
            // 展示餐品列表
            rendView(((FoodBean) response).getData());
        } else if (response instanceof FoodCategoryBean) {
            final FoodCategoryBean foodCategoryBean = (FoodCategoryBean) response;
            mTabCreateCategory.removeAllTabs();
            mTabCreateCategory.addTab(mTabCreateCategory.newTab().setText("全部"));
            // 展示餐品分类
            final List<FoodCategoryBean.DataBean> foodCategoryBeanData = foodCategoryBean.getData();
            for (int i = 0; i < foodCategoryBeanData.size(); i++) {
                mTabCreateCategory.addTab(mTabCreateCategory.newTab().setText(foodCategoryBeanData.get(i).getCategoryName()));
//                mBadgeList.add(new QBadgeView(CreateOrderActivity.this).bindTarget(mTabCreateCategory.getTabAt(i)));
            }
            // 默认选择第一条全部
            mTabCreateCategory.setSelectedTab(0);

            // 分类选择监听
            mTabCreateCategory.setOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(VerticalTabLayout.Tab tab, int position) {
                    String type = "-1";
                    if (position > 0) {
                        type = foodCategoryBeanData.get(position - 1).getCategoryNumber();
                    }
                    mPresenter.getFoodByCategory(mFoodBeanList, mFinalFoodBeanList, type);
                    mCreateOrderAdapter.notifyDataSetChanged();
                }

                @Override
                public void onTabReleased(VerticalTabLayout.Tab tab, int position) {
                }
            });
        }
    }

    /**
     * @param list       操作后的菜单数据
     * @param totalMoney 总价格
     * @param totalCount 总金额
     * @desc 选择删除菜品时的回调
     * @anthor lpc
     * @date: 2018/7/18
     */
    @Override
    public void changeSelectFoodCallBack(List list, String totalMoney, int totalCount) {
        mSelectMealEntities.clear();
        mSelectMealEntities.addAll(list);
        mCreateOrderAdapter.notifyDataSetChanged();
        mTvOrderMoney.setText(totalMoney);
        mTvFoodCount.setText(totalCount + "");
        mTvSelectCount.setText(totalCount + "");
        if (totalCount > 0) {
            mTvFoodCount.setVisibility(View.VISIBLE);
        } else {
            mTvFoodCount.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void initData() {
    }

    /**
     * @param foodBeans 菜品数据源
     * @desc 获取数据后，渲染页面
     * @anthor lpc
     * @date 2018/7/16
     */
    private void rendView(List<FoodBean.DataBean> foodBeans) {
        if (mFoodBeanList == null) {
            mFoodBeanList = new ArrayList<>();
            mFinalFoodBeanList = new ArrayList<>();
            mCreateOrderAdapter = new CreateOrderAdapter(mFoodBeanList);
            mRvCreateOrderFood.setAdapter(mCreateOrderAdapter);
            mCreateOrderAdapter.setOnItemChildClickListener(this);
        }
        mFoodBeanList.clear();
        mFinalFoodBeanList.clear();
        mFoodBeanList.addAll(foodBeans);
        mFinalFoodBeanList.addAll(foodBeans);
        mCreateOrderAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ll_order_count, R.id.tv_make_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_order_count:
                // 弹窗展示简单的餐品列表
                showFoodListWindow();
                break;
            case R.id.tv_make_money:
                //跳转到订单详情页面
                Intent intent = new Intent(this, CollectMoneyActivity.class);
                intent.putExtra("money", mTvOrderMoney.getText());
                intent.putExtra("count", mTvSelectCount.getText());
                intent.putExtra("foodCount", mTvFoodCount.getText());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * @param
     * @desc 餐品列表的Item点击事件
     * @anthor lpc
     * @date: 2018/7/17
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        FoodBean.DataBean dataBean;
        SelectMealEntity selectMealEntity;
        switch (view.getId()) {
            case R.id.tv_add:
                // 按钮 加
                dataBean = mFoodBeanList.get(position);
                dataBean.setFoodProductsCount(dataBean.getFoodProductsCount() + 1);
                mPresenter.addFood(mFoodBeanList, dataBean, -1);
                break;
            case R.id.tv_subtract:
                // 按钮 减
                dataBean = mFoodBeanList.get(position);
                dataBean.setFoodProductsCount(dataBean.getFoodProductsCount() - 1);
                mPresenter.subtractFood(mFoodBeanList, dataBean, -1);
                break;
            case R.id.rl_standard:
                // 按钮 选择规格
                dataBean = mFoodBeanList.get(position);
                showStandardWindow(dataBean, dataBean.getShopStandarList());
                break;
            case R.id.tv_add_select:
                // 按钮 加
                selectMealEntity = mSelectMealEntities.get(position);
                mCreateOrderFoodListAdapter.notifyDataSetChanged();
                mPresenter.addFood(mFoodBeanList, selectMealEntity, -1);
                break;
            case R.id.tv_subtract_select:
                // 按钮 减
                selectMealEntity = mSelectMealEntities.get(position);
                mCreateOrderFoodListAdapter.notifyDataSetChanged();
                mPresenter.subtractFood(mFoodBeanList, selectMealEntity, -1);
                break;
            default:
                break;
        }
    }

    /**
     * @param dataBean
     * @param shopStandarList 规格集合
     * @desc 选择规格弹窗
     * @anthor lpc
     * @date: 2018/7/17
     */
    private void showStandardWindow(final FoodBean.DataBean dataBean, final List<FoodBean.DataBean.ShopStandarListBean> shopStandarList) {
        final String foodName = dataBean.getSingleProductName();
        View view = LayoutInflater.from(this).inflate(R.layout.module_pop_create_order_standard, null);
        final Dialog dialog = new Dialog(this, R.style.CommonDialog);
        //设置dialog的宽高
        Display display = getWindowManager().getDefaultDisplay();
        int width = (int) (display.getWidth() * 0.8);
        int height = (int) (display.getHeight() * 0.3);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        dialog.setContentView(view, params);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

        final TagFlowLayout tflStandard = view.findViewById(R.id.tfl_standard);
        final TextView tvFoodName = view.findViewById(R.id.tv_food_name);
        final TextView tvStandardName = view.findViewById(R.id.tv_standard_name);
        final TextView tvStandardPrice = view.findViewById(R.id.tv_standard_price);
        final LinearLayout llAddShopping = view.findViewById(R.id.ll_add_shopping);

        final int[] standardPosition = {-1};
        tvFoodName.setText(foodName);
        tflStandard.setAdapter(new TagAdapter<FoodBean.DataBean.ShopStandarListBean>(shopStandarList) {
            @Override
            public View getView(FlowLayout parent, int position, FoodBean.DataBean.ShopStandarListBean shopStandarListBeans) {
                TextView textView = (TextView) mInflater.inflate(R.layout.module_tag_flow_text_view, tflStandard, false);
                textView.setText(foodName + "(" + shopStandarListBeans.getStandardName() + ")");
                return textView;
            }
        });
        // 选择规格
        tflStandard.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                tvStandardName.setText(foodName + "(" + shopStandarList.get(position).getStandardName() + ")");
                tvStandardPrice.setText("￥" + shopStandarList.get(position).getSell());
                standardPosition[0] = position;
                return false;
            }
        });

        // 加入购物车
        llAddShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (standardPosition[0] < 0) {
                    ToastUtil.showToast("请先选择规格");
                } else {
                    mPresenter.addFood(mFoodBeanList, dataBean, standardPosition[0]);
                    dataBean.setFoodProductsCount(dataBean.getFoodProductsCount() + 1);
                    dialog.dismiss();
                }
            }
        });
    }

    /**
     * @desc 弹窗展示点餐列表
     * @anthor lpc
     * @date: 2018/7/16
     */
    private void showFoodListWindow() {
        int height = getWindowManager().getDefaultDisplay().getHeight();
        CustomPopupWindow popupWindow = new CustomPopupWindow.Builder()
                .setContext(this)
                .setContentView(R.layout.module_pop_create_order_food_list)
                .setwidth(LinearLayout.LayoutParams.MATCH_PARENT)
                .setheight(height / 3)
                .setFouse(true)
                .setOutSideCancel(true)
                .builder();
        //0.0-1.0
        ScreenUtil.backgroundAlpha(this, 0.5f);
        popupWindow.showUp(mLlBottom);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ScreenUtil.backgroundAlpha(CreateOrderActivity.this, 1f);
            }
        });
        RecyclerView rvPopCreateOrderFood = (RecyclerView) popupWindow.getItemView(R.id.rv_pop_create_order_food);
        rvPopCreateOrderFood.setLayoutManager(new LinearLayoutManager(this));

        mCreateOrderFoodListAdapter = new CreateOrderFoodListAdapter(mSelectMealEntities);
        rvPopCreateOrderFood.setAdapter(mCreateOrderFoodListAdapter);
        mCreateOrderFoodListAdapter.setOnItemChildClickListener(this);
        // 清空购物车按钮
        popupWindow.getItemView(R.id.tv_clear_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.clearFoods();
                mSelectMealEntities.clear();
                mCreateOrderFoodListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
