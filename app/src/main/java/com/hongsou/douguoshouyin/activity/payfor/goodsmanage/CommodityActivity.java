package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.AddTaocanCategoryAdapter;
import com.hongsou.douguoshouyin.adapter.AddTaocanFoodsAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.FoodBean;
import com.hongsou.douguoshouyin.javabean.FoodCategoryBean;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品管理主页面
 */
public class CommodityActivity extends BaseActivity {
//TODO 重新加载页面时刷新

    @BindView(R.id.tv_payfor_commodity_tag_guanlifenlei_icon)
    TextView tvPayforCommodityTagGuanlifenleiIcon;
    @BindView(R.id.ll_payfor_commodity_tag_guanlifenlei)
    LinearLayout llPayforCommodityTagGuanlifenlei;
    @BindView(R.id.tv_payfor_commodity_tag_tianjiashangpin_icon)
    TextView tvPayforCommodityTagTianjiashangpinIcon;
    @BindView(R.id.ll_payfor_commodity_tag_tianjiashangpin)
    LinearLayout llPayforCommodityTagTianjiashangpin;
    @BindView(R.id.tv_payfor_commodity_tag_xiugaipaixu_icon)
    TextView tvPayforCommodityTagXiugaipaixuIcon;

    @BindView(R.id.tv_payfor_commodity_tag_gengduocaozuo_icon)
    TextView tvPayforCommodityTagGengduocaozuoIcon;
    @BindView(R.id.ll_payfor_commodity_tag_gengduocaozuo)
    LinearLayout llPayforCommodityTagGengduocaozuo;
    @BindView(R.id.ll_payfor_commodity)
    LinearLayout llPayforCommodity;
    @BindView(R.id.tv_payfor_commodity_tag_tianjiataocan_icon)
    TextView tvPayforCommodityTagTianjiataocanIcon;
    @BindView(R.id.ll_payfor_commodity_tag_tianjiataocan)
    LinearLayout llPayforCommodityTagTianjiataocan;
    @BindView(R.id.ll_payfor_commodity_tag)
    LinearLayout llPayforCommodityTag;
    @BindView(R.id.ll_payfor_commodity_tag_tianjiazuhe)
    LinearLayout llPayforCommodityTagTianjiazuhe;
    @BindView(R.id.rv_payfor_commodity_category)
    RecyclerView rvPayforCommodityCategory;
    @BindView(R.id.rv_payfor_commodity_foods)
    RecyclerView rvPayforCommodityFoods;
    private PopupWindow mPopupWindow;


    //分类适配器
    AddTaocanCategoryAdapter addTaocanCategoryAdapter;
    //分类数据源
    List<FoodCategoryBean.DataBean> dataBeanList;
    //food数据源 修改后的
    List<SingleFoodsBean> singleFoodsBeanList = new ArrayList<>();
    //分类下的餐品 数据
    List<SingleFoodsBean> singleFoodsBeanList2 = new ArrayList<>();

    //适配器
    AddTaocanFoodsAdapter addTaocanFoodsAdapter;
    //分类编号
    private String categoryNumber;

    //强制刷新状态 0强制刷新 1不强制刷新
    private String state;
    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_commodity;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("商品管理");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvPayforCommodityTagGengduocaozuoIcon, tvPayforCommodityTagGuanlifenleiIcon
                , tvPayforCommodityTagTianjiashangpinIcon, tvPayforCommodityTagXiugaipaixuIcon, tvPayforCommodityTagTianjiataocanIcon});

    }

    @Override
    public void initData() {
        state="1";
        getCategoryList();
        getFoodsList();
    }

    /**
     * @author fenghao
     * @date 2018/7/20 0020 下午 15:05
     * @desc 获取分类列表
     */
    private void getCategoryList() {
        HttpFactory.get().url(ApiConfig.GET_CATEGORY)
                .addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<FoodCategoryBean>(this) {
            @Override
            public void onResponse(FoodCategoryBean response, int id) {
                if (response.isSuccess()) {
                    dataBeanList = response.getData();
                    if (dataBeanList.size() > 0) {
                        showCategoryList(dataBeanList);
                    } else {
                        ToastUtil.showToast("暂无分类，请先添加分类");
                    }
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @param dataBeanList 分类数据源
     * @author fenghao
     * @date 2018/7/20 0020 下午 15:07
     * @desc 展示分类列表
     */
    private void showCategoryList(final List<FoodCategoryBean.DataBean> dataBeanList) {

        if (dataBeanList.size() < 1) {
            ToastUtil.showToast("暂无商品，请先添加商品");
            finishActivity();
        }
        addTaocanCategoryAdapter = new AddTaocanCategoryAdapter(R.layout.module_item_addfoods_category, dataBeanList);
        rvPayforCommodityCategory.setAdapter(addTaocanCategoryAdapter);
        rvPayforCommodityCategory.setLayoutManager(new LinearLayoutManager(this));

        addTaocanCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView tvItemAddfoodsCategory;

                //获取其他子控件 变色
                for (int i = 0; i < dataBeanList.size(); i++) {
                    if (i == position) {
                        tvItemAddfoodsCategory = (TextView) adapter.getViewByPosition(rvPayforCommodityCategory, position
                                , R.id.tv_item_addfoods_category);
                        tvItemAddfoodsCategory.setBackgroundColor(getResources().getColor(R.color.white));

                    } else {
                        tvItemAddfoodsCategory = (TextView) adapter.getViewByPosition(rvPayforCommodityCategory, i
                                , R.id.tv_item_addfoods_category);
                        tvItemAddfoodsCategory.setBackgroundColor(getResources().getColor(R.color.bg_gray));
                    }
                }
                singleFoodsBeanList2.clear();
                //取出同类型的商品放入集合2 显示
                for (int n = 0; n < singleFoodsBeanList.size(); n++) {
                    if (singleFoodsBeanList.get(n).getCategoryNumber().equals(dataBeanList.get(position).getCategoryNumber())) {
                        singleFoodsBeanList2.add(singleFoodsBeanList.get(n));
                    }
                }

                addTaocanFoodsAdapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * @author fenghao
     * @date 2018/7/20 0020 下午 15:06
     * @desc 获取food列表
     */
    private void getFoodsList() {
        HttpFactory.get().url(ApiConfig.GET_FOOD)
                .addParams("shopNumber", getShopNumber())
                .addParams("state",state)
                .build().execute(new ResponseCallback<FoodBean>(this) {
            @Override
            public void onResponse(FoodBean response, int id) {
                if (response.isSuccess()) {
                    state="1";
                    Global.getSpGlobalUtil().setForceState("1");
                    List<FoodBean.DataBean> data = response.getData();
                    setSingleFoodsBean(data);
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @param data 原始数据
     * @author fenghao
     * @date 2018/7/20 0020 下午 16:36
     * @desc 设置数据
     */
    private void setSingleFoodsBean(List<FoodBean.DataBean> data) {
        FoodBean.DataBean foodDataBean;

        for (int i = 0; i < data.size(); i++) {
            //循环商品规格
            foodDataBean = data.get(i);
            if ("1".equals(data.get(i).getFoodType())) {
                for (int k = 0; k < data.get(i).getShopStandarList().size(); k++) {
                    SingleFoodsBean singleFoodsBean = new SingleFoodsBean(foodDataBean.getSingleProductNumber()
                            , foodDataBean.getShopStandarList().get(k).getStandardNumber()
                            , foodDataBean.getSingleProductName()
                            , foodDataBean.getShopStandarList().get(k).getStandardName()
                            , foodDataBean.getShopStandarList().get(k).getSell()
                            , foodDataBean.getFoodProductsPicture()
                            , foodDataBean.getSingleProductType()
                            , 0
                    );
                    singleFoodsBeanList.add(singleFoodsBean);
                }
            } else if ("0".equals(data.get(i).getFoodType())) {
                //固定套餐
                singleFoodsBeanList.add(new SingleFoodsBean(foodDataBean.getPackageNumber()
                        , "", foodDataBean.getPackageName()
                        , "", foodDataBean.getPackagePrice()
                        , foodDataBean.getPackagePicture()
                        , foodDataBean.getPackageType()
                        , 0));
            } else if ("2".equals(data.get(i).getFoodType())) {
                //组合套餐
                singleFoodsBeanList.add(new SingleFoodsBean(foodDataBean.getGroupPackageNumber()
                        , "", foodDataBean.getGroupPackageName()
                        , "", foodDataBean.getGroupPackagePrice()
                        , foodDataBean.getPackagePicture()
                        , foodDataBean.getCateGoryType()
                        , 0));
            }

        }
        //取出同类型的商品放入集合2 显示  默认显示第一个分类的数据
        for (int n = 0; n < singleFoodsBeanList.size(); n++) {
            if (singleFoodsBeanList.get(n).getCategoryNumber().equals(dataBeanList.get(0).getCategoryNumber())) {
                singleFoodsBeanList2.add(singleFoodsBeanList.get(n));
            }
        }
        showFoodsList();
    }

    /**
     * @author fenghao
     * @date 2018/7/20 0020 下午 16:55
     * @desc 展示商品列表
     */
    private void showFoodsList() {
        addTaocanFoodsAdapter = new AddTaocanFoodsAdapter(R.layout.module_recycle_item_create_order_food, singleFoodsBeanList2,1);
        rvPayforCommodityFoods.setAdapter(addTaocanFoodsAdapter);
        rvPayforCommodityFoods.setLayoutManager(new LinearLayoutManager(this));

    }


    @OnClick({R.id.ll_payfor_commodity_tag_tianjiataocan, R.id.ll_payfor_commodity_tag_guanlifenlei, R.id.ll_payfor_commodity_tag_tianjiashangpin, R.id.ll_payfor_commodity_tag_tianjiazuhe, R.id.ll_payfor_commodity_tag_gengduocaozuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_payfor_commodity_tag_guanlifenlei:
                //跳转分类管理页面
                Intent manageIntent = new Intent(this, ManageClassActivity.class);
                startActivity(manageIntent);
                break;
            case R.id.ll_payfor_commodity_tag_tianjiashangpin:
                //跳转添加商品页面
                Intent addgoodsIntent = new Intent(this, AddGoodsActivity.class);
                startActivity(addgoodsIntent);
                break;
            case R.id.ll_payfor_commodity_tag_tianjiazuhe:
                //添加分组页面
                Intent addzuheIntent = new Intent(this, TaocanfenzuActivity.class);
                addzuheIntent.putExtra("choose", "");
                startActivity(addzuheIntent);
                break;
            case R.id.ll_payfor_commodity_tag_gengduocaozuo:
                //弹出popwindow
                showPopWindow();
                break;
            case R.id.ll_payfor_commodity_tag_tianjiataocan:
                //跳转添加套餐页面
                Intent addtaocanIntent = new Intent(this, AddTaocanActivity.class);
                startActivity(addtaocanIntent);
                break;
            default:
                break;
        }
    }

    //popwindow弹窗
    private void showPopWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_payfor_commodity, null);

        TextView tv_payfor_commodity_pop_qishou = view.findViewById(R.id.tv_payfor_commodity_pop_qishou);
        TextView tv_payfor_commodity_pop_tingshou = view.findViewById(R.id.tv_payfor_commodity_pop_tingshou);
        TextView tv_payfor_commodity_pop_shanchu = view.findViewById(R.id.tv_payfor_commodity_pop_shanchu);

        //批量起售

        tv_payfor_commodity_pop_qishou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //批量停售

        tv_payfor_commodity_pop_tingshou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //批量删除

        tv_payfor_commodity_pop_shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Display display = this.getWindowManager().getDefaultDisplay();
        int h = display.getHeight();
        int w = display.getWidth();
        mPopupWindow = new PopupWindow(view, w * 2 / 5, h * 3 / 10);
        // 设置SelectPicPopupWindow弹出窗体动画效果，设置动画，一会会讲解
        mPopupWindow.setAnimationStyle(R.style.et_style);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(llPayforCommodity, Gravity.BOTTOM, w * 4 / 9, h / 11);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if ("0".equals(Global.getSpGlobalUtil().getForceState())){
            state="0";
            if (dataBeanList!=null){
                dataBeanList.clear();
            }
            if (singleFoodsBeanList!=null){
                singleFoodsBeanList.clear();
            }
            if (singleFoodsBeanList2!=null){
                singleFoodsBeanList2.clear();
            }
            getCategoryList();
            getFoodsList();


        }
    }

    //---------------======================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}



