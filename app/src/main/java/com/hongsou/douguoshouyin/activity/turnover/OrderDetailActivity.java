package com.hongsou.douguoshouyin.activity.turnover;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.OrderDetailsFoodAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.OrderDetailBean;
import com.hongsou.douguoshouyin.javabean.OrderFoodBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 订单详情页面
 */
public class OrderDetailActivity extends BaseActivity {


    @BindView(R.id.tv_turnover_orderdetail_xianshijine)
    TextView tvTurnoverOrderdetailXianshijine;
    @BindView(R.id.tv_turnover_orderdetail_dingdanhao)
    TextView tvTurnoverOrderdetailDingdanhao;
    @BindView(R.id.tv_turnover_orderdetail_leixing)
    TextView tvTurnoverOrderdetailLeixing;
    @BindView(R.id.tv_turnover_orderdetail_dingdanzhuangtai)
    TextView tvTurnoverOrderdetailDingdanzhuangtai;
    @BindView(R.id.tv_turnover_orderdetail_caozuoren)
    TextView tvTurnoverOrderdetailCaozuoren;
    @BindView(R.id.tv_turnover_orderdetail_xiadanshijian)
    TextView tvTurnoverOrderdetailXiadanshijian;
    @BindView(R.id.tv_turnover_orderdetail_yingshou)
    TextView tvTurnoverOrderdetailYingshou;
    @BindView(R.id.tv_turnover_orderdetail_zhaoling)
    TextView tvTurnoverOrderdetailZhaoling;
    @BindView(R.id.tv_turnover_orderdetail_shishoujine)
    TextView tvTurnoverOrderdetailShishoujine;
    @BindView(R.id.tv_turnover_orderdetail_tuikuan)
    TextView tvTurnoverOrderdetailTuikuan;
    @BindView(R.id.tv_turnover_orderdetail_dayinxiaopiao)
    TextView tvTurnoverOrderdetailDayinxiaopiao;
    @BindView(R.id.tv_turnover_orderdetail_dingdanjine)
    TextView tvTurnoverOrderdetailDingdanjine;
    @BindView(R.id.tv_turnover_orderdetail_youhuijine)
    TextView tvTurnoverOrderdetailYouhuijine;
    @BindView(R.id.tv_turnover_orderdetail_shoukuanjine)
    TextView tvTurnoverOrderdetailShoukuanjine;


    @BindView(R.id.v_turnover_orderdetail_verticle)
    View vTurnoverOrderdetailVerticle;
    @BindView(R.id.tv_turnover_orderdetail_shangpinzongjia)
    TextView tvTurnoverOrderdetailShangpinzongjia;
    @BindView(R.id.tv_turnover_orderdetail_shangpinfenshu)
    TextView tvTurnoverOrderdetailShangpinfenshu;
    @BindView(R.id.ll_turnover_orderdetail_xianshijine)
    LinearLayout llTurnoverOrderdetailXianshijine;
    @BindView(R.id.rv_turnover_orderdetail_foodlist)
    RecyclerView rvTurnoverOrderdetailFoodlist;

    private String batch;//订单号
    Dialog dialog;

    //套餐
    List<OrderDetailBean.DataBean.PackageBean> packageBeanList;

    //组合套餐
    List<OrderDetailBean.DataBean.GroupBean> groupBeanList;
    //单品
    List<OrderDetailBean.DataBean.FoodBean> foodBeanList;

    //总集合
    List<OrderFoodBean> orderFoodBeanList = new ArrayList<>();

    OrderDetailsFoodAdapter orderDetailsFoodAdapter;
    //支付方式
    String type;

    @Override
    public int initLayout() {
        return R.layout.module_activity_turnover_order_detail;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("订单详情页");
    }

    @Override
    public void initView() {
        batch = getIntent().getStringExtra("batch");
    }

    @Override
    public void initData() {
        detailes();
    }

    /**
     * 查询订单详情接口
     */
    private void detailes() {
        showLoadingDialog();
        HttpFactory.get().url(ApiConfig.GET_ORDER_DETAILS)
                .addParams("shopNumber", Global.getSpGlobalUtil().getShopNumber())
                .addParams("batch", batch)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                ToastUtil.showError();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                Log.e(TAG, "onResponse: " + response.toString());
                OrderDetailBean orderDetailBean = new Gson().fromJson(response, OrderDetailBean.class);

                if (orderDetailBean.getCode() == 1000) {
                    OrderDetailBean.DataBean.OrderBean order = orderDetailBean.getData().getOrder();

                    addOrderDetails(order);
                    packageBeanList = orderDetailBean.getData().getPackageX();
                    groupBeanList = orderDetailBean.getData().getGroup();
                    foodBeanList = orderDetailBean.getData().getFood();

                    //总份数
                    tvTurnoverOrderdetailShangpinfenshu.setText((packageBeanList.size() + groupBeanList.size() + foodBeanList.size()) + "");

                    if (packageBeanList.size() > 0) {
                        addPackage(packageBeanList);
                    }
                    if (groupBeanList.size() > 0) {
                        addGroup(groupBeanList);
                    }
                    if (foodBeanList.size() > 0) {
                        addFood(foodBeanList);
                    }

                    //展示
                    showFoodList();

                } else {
                    ToastUtil.showToast(orderDetailBean.getMsg());
                }


            }
        });
    }


    /**
     * @param
     * @param order
     * @return
     * @author fenghao
     * @date 2018/7/14 0014 下午 15:25
     * @desc 显示订单详情的数据
     */
    private void addOrderDetails(OrderDetailBean.DataBean.OrderBean order) {
        //TODO 判断是不是已退款的 是的话隐藏退款按钮
        if (order.getOrderType().contains("已退款")) {
            vTurnoverOrderdetailVerticle.setVisibility(View.GONE);
            tvTurnoverOrderdetailTuikuan.setVisibility(View.GONE);
        }
        tvTurnoverOrderdetailDingdanzhuangtai.setText(order.getOrderType());

        tvTurnoverOrderdetailXianshijine.setText(order.getOrderAmount());
        tvTurnoverOrderdetailDingdanhao.setText(order.getBatch());
        tvTurnoverOrderdetailLeixing.setText(order.getOrderSourcePayment());
        tvTurnoverOrderdetailCaozuoren.setText(order.getClerkName());
        tvTurnoverOrderdetailXiadanshijian.setText(order.getInsertTime());
        tvTurnoverOrderdetailYingshou.setText(order.getAmountReceivable());
        tvTurnoverOrderdetailZhaoling.setText(order.getCashAmount());
        tvTurnoverOrderdetailShishoujine.setText(order.getAmountCollected());
        tvTurnoverOrderdetailDingdanjine.setText(order.getOrderAmount());
        tvTurnoverOrderdetailYouhuijine.setText(order.getOrderDiscount());
        tvTurnoverOrderdetailShoukuanjine.setText(order.getAmountCollected());
        if (order.getPaymentType().contains("微信")){
            type = "wechar";
        }else if (order.getPaymentType().contains("支付宝")){
            type = "ali";
        }
        //总价
        tvTurnoverOrderdetailShangpinzongjia.setText(order.getOrderAmount());
    }

    /**
     * @param packageBeanList
     * @return
     * @author fenghao
     * @date 2018/7/14 0014 下午 15:06
     * @desc 将套餐添加进集合
     */
    private void addPackage(List<OrderDetailBean.DataBean.PackageBean> packageBeanList) {
        //将标题添加进去  套餐名 个数 价钱
        for (int i = 0; i < packageBeanList.size(); i++) {
            OrderFoodBean orderFoodBeanA = new OrderFoodBean();
            orderFoodBeanA.setFoodName(packageBeanList.get(i).getPackageName());
            orderFoodBeanA.setFoodCount("X" + packageBeanList.get(i).getFoodProductsCount());
            orderFoodBeanA.setFoodPrice("¥" + packageBeanList.get(i).getPackagePrice());

            orderFoodBeanList.add(orderFoodBeanA);
            //将单个餐品添加进去
            for (int j = 0; j < packageBeanList.get(i).getPackageList().size(); j++) {
                OrderFoodBean orderFoodBeanB = new OrderFoodBean();
                orderFoodBeanB.setFoodName("");
                orderFoodBeanB.setFoodCount("--" + packageBeanList.get(i).getPackageList().get(j).getSingleProductName()
                        + "(" + packageBeanList.get(i).getPackageList().get(j).getStandardName() + ")");
                orderFoodBeanB.setFoodPrice("*" + packageBeanList.get(i).getPackageList().get(j).getFoodProductsCount());
                orderFoodBeanList.add(orderFoodBeanB);
            }
        }
    }

    /**
     * @param groupBeanList
     * @return
     * @author fenghao
     * @date 2018/7/14 0014 下午 15:21
     * @desc 将组合套餐添加进集合
     */
    private void addGroup(List<OrderDetailBean.DataBean.GroupBean> groupBeanList) {
        for (int i = 0; i < groupBeanList.size(); i++) {
            //把头添加进去
            OrderFoodBean orderFoodBeanA = new OrderFoodBean();
            orderFoodBeanA.setFoodName(groupBeanList.get(i).getGroupPackageName());
            orderFoodBeanA.setFoodCount("X" + groupBeanList.get(i).getFoodProductsCount());
            orderFoodBeanA.setFoodPrice("¥" + groupBeanList.get(i).getGroupPackagePrice());

            orderFoodBeanList.add(orderFoodBeanA);

            //将 菜品加进去
            for (int j = 0; j < groupBeanList.get(i).getGroupFood().size(); j++) {
                OrderFoodBean orderFoodBeanB = new OrderFoodBean();
                orderFoodBeanB.setFoodName("");
                orderFoodBeanB.setFoodCount("--" + groupBeanList.get(i).getGroupFood().get(j).getSingleProductName()
                        + "(" + groupBeanList.get(i).getGroupFood().get(j).getStandardName() + ")");
                orderFoodBeanB.setFoodPrice("*" + groupBeanList.get(i).getGroupFood().get(j).getFoodProductsCount());
                orderFoodBeanList.add(orderFoodBeanB);
            }
        }
    }

    /**
     * @param foodBeanList
     * @return
     * @author fenghao
     * @date 2018/7/14 0014 下午 15:21
     * @desc 将单品添加进集合
     */
    private void addFood(List<OrderDetailBean.DataBean.FoodBean> foodBeanList) {
        for (int i = 0; i < foodBeanList.size(); i++) {
            OrderFoodBean orderFoodBean = new OrderFoodBean();
            orderFoodBean.setFoodName(foodBeanList.get(i).getSingleProductName()
                    + "(" + foodBeanList.get(i).getStandardName() + ")");
            orderFoodBean.setFoodCount("X" + foodBeanList.get(i).getFoodProductsCount());
            orderFoodBean.setFoodPrice("¥" + foodBeanList.get(i).getSell());
            orderFoodBeanList.add(orderFoodBean);
        }
    }

    /**
     * @param
     * @return
     * @author fenghao
     * @date 2018/7/14 0014 下午 16:03
     * @desc 展示菜品列表
     */
    private void showFoodList() {
        //创建适配器
        orderDetailsFoodAdapter = new OrderDetailsFoodAdapter(R.layout.module_item_orderdetail_foods, orderFoodBeanList);
        rvTurnoverOrderdetailFoodlist.setAdapter(orderDetailsFoodAdapter);
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTurnoverOrderdetailFoodlist.setLayoutManager(linearLayoutManager);
    }

    //弹框输入密码
    private void showTuikuanDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_tuikuan, null);
        TextView tv_dialog_tuikuan_title = view.findViewById(R.id.tv_dialog_tuikuan_title);
        EditText et_dialog_tuikuan_content = view.findViewById(R.id.et_dialog_tuikuan_content);
        TextView tv_dialog_tuikuan_yes = view.findViewById(R.id.tv_dialog_tuikuan_yes);
        TextView tv_dialog_tuikuan_cancle = view.findViewById(R.id.tv_dialog_tuikuan_cancle);
        tv_dialog_tuikuan_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断密码是否正确 走退款接口   TODO
                tuikuan();
                dialog.dismiss();
            }
        });
        tv_dialog_tuikuan_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);
        dialog = new Dialog(this, R.style.CommonDialog);
        dialog.addContentView(view, params);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();


    }

    //退款接口
    private void tuikuan() {
        showLoadingDialog();
        HttpFactory.post().url(ApiConfig.REFUND)
                .addParams("outTradeNo", tvTurnoverOrderdetailDingdanhao.getText().toString())
                .addParams("type", type)
                .addParams("appAuthToken", Global.getSpGlobalUtil().getAliCode())
                .addParams("subMchId", Global.getSpGlobalUtil().getWecharCode())
                .addParams("refundAmount", tvTurnoverOrderdetailShishoujine.getText().toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                tuidan();
            }
        });
    }

    /**
     *  @author  fenghao
     *  @date    2018/7/26 0026 下午 16:20
     *  @desc   退单接口
     */
    private void tuidan() {
        String paymentType ="";
        if (type.contains("wechar")){
            paymentType="微信";
        }else  if (type.contains("ali")){
            paymentType="支付宝";
        }
        HttpFactory.post()
                .addParams("shopNumber",getShopNumber())
                .addParams("batch",tvTurnoverOrderdetailDingdanhao.getText().toString())
                .addParams("orderAmount",tvTurnoverOrderdetailShishoujine.getText().toString())
                .addParams("reason","")
                .addParams("amount",tvTurnoverOrderdetailShishoujine.getText().toString())
                .addParams("paymentType",paymentType)
                .url(ApiConfig.REFOUND_ORDER).build().execute(new ResponseCallback<BaseBean>(this) {

            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("退款成功");
                } else {
                    ToastUtil.showToast("退单失败");
                }
            }
        });
    }
    @OnClick({R.id.tv_turnover_orderdetail_tuikuan, R.id.tv_turnover_orderdetail_dayinxiaopiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_turnover_orderdetail_tuikuan:
                showTuikuanDialog();
                break;
            case R.id.tv_turnover_orderdetail_dayinxiaopiao:
                //打印小票
                break;
            default:
                break;
        }
    }


    //======================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
