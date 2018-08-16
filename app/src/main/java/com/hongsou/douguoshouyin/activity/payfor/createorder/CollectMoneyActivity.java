package com.hongsou.douguoshouyin.activity.payfor.createorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.payfor.payfor.PaymentDetailActivity;
import com.hongsou.douguoshouyin.activity.payfor.payfor.QRCodeActivity;
import com.hongsou.douguoshouyin.activity.payfor.payfor.ScanQRCodeActivity;
import com.hongsou.douguoshouyin.activity.turnover.OrderDetailActivity;
import com.hongsou.douguoshouyin.adapter.CollectMoneyAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.broadcastreceiver.PayOnLineSuccessBean;
import com.hongsou.douguoshouyin.db.SelectMealEntity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.SaomahaoBean;
import com.hongsou.douguoshouyin.javabean.SubmitOrderBean;
import com.hongsou.douguoshouyin.tool.DateUtils;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ScreenUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;
import com.hongsou.douguoshouyin.views.CustomPopupWindow;
import com.hongsou.greendao.gen.SelectMealEntityDao;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/19
 * <p>
 * @desc 收款页面
 */
public class CollectMoneyActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;
    @BindView(R.id.rv_collect_money)
    RecyclerView mRvCollectMoney;
    @BindView(R.id.et_money)
    EditText mEtMoney;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.ll_edit_content)
    LinearLayout mLlEditContent;
    @BindView(R.id.tv_total_money)
    TextView mTvTotalMoney;
    @BindView(R.id.tv_food_count)
    TextView mTvFoodCount;
    @BindView(R.id.tv_select_count)
    TextView mTvSelectCount;
    @BindView(R.id.tv_collect_money_collect)
    TextView mTvCollectMoneyCollect;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    /**
     * 数据库缓存购物车列表
     */
    private SelectMealEntityDao mSelectMealEntityDao;
    private CollectMoneyAdapter mCollectMoneyAdapter;
    private List<SelectMealEntity> mSelectMealEntities;
    private String mTotalMoney;
    private CustomPopupWindow mPopupWindow;
    private SubmitOrderBean mSubmitOrderBean;
    private String mBatch;


    @Override
    public int initLayout() {
        return R.layout.module_activity_collect_money;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        if (getIntent().hasExtra("money")) {
            mTotalMoney = getIntent().getStringExtra("money");
            mTvTotalMoney.setText(mTotalMoney);
            mTvFoodCount.setText(getIntent().getStringExtra("foodCount"));
            mTvSelectCount.setText(getIntent().getStringExtra("count"));
            String data = getIntent().getStringExtra("data");
            mSelectMealEntities = (List<SelectMealEntity>) new Gson().fromJson(data, new TypeToken<List<SelectMealEntity>>() {
            }.getType());
        }
//        mSelectMealEntityDao = BaseApplication.getApplication().getDaoSession().getSelectMealEntityDao();
//        mSelectMealEntities = mSelectMealEntityDao.loadAll();
        if (mSelectMealEntities == null) {
            mSelectMealEntities = new ArrayList<>();
        }
        mRvCollectMoney.setLayoutManager(new LinearLayoutManager(this));
        Log.e(TAG, "init: " + mSelectMealEntities.size());
        mCollectMoneyAdapter = new CollectMoneyAdapter(mSelectMealEntities);
        mRvCollectMoney.setAdapter(mCollectMoneyAdapter);
    }

    @Override
    public void initData() {
    }

    @OnClick(R.id.tv_collect_money_collect)
    public void onViewClicked() {
        //弹出收款框
        showPopWindow();
    }

    /**
     * @desc 弹框扫码或一码付支付
     * @anthor lpc
     * @date: 2018/7/19
     */
    private void showPopWindow() {
        //h popwindow的高度
        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        mPopupWindow = new CustomPopupWindow.Builder()
                .setContext(this)
                .setContentView(R.layout.module_pop_pay)
                .setOutSideCancel(true)
                .setFouse(true)
                .setheight(h / 5)
                .setwidth(LinearLayout.LayoutParams.MATCH_PARENT)
                .setAnimationStyle(R.style.et_style)
                .builder();
        // 将屏幕背景变暗
        ScreenUtil.backgroundAlpha(this, 0.5f);
        mPopupWindow.showUp(mLlBottom);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 恢复白色背景
                ScreenUtil.backgroundAlpha(CollectMoneyActivity.this, 1f);
            }
        });
        TextView tvPayforPopSaoIcon = (TextView) mPopupWindow.getItemView(R.id.tv_payfor_pop_sao_icon);
        TextView tvPayforPopErweiIcon = (TextView) mPopupWindow.getItemView(R.id.tv_payfor_pop_erwei_icon);
        TextView tvPayforPopXianjinIcon = (TextView) mPopupWindow.getItemView(R.id.tv_payfor_pop_xianjin_icon);
        LinearLayout llPayForPopScan = (LinearLayout) mPopupWindow.getItemView(R.id.ll_payfor_pop_sao);
        LinearLayout llPayForPopCode = (LinearLayout) mPopupWindow.getItemView(R.id.ll_payfor_pop_erwei);
        LinearLayout llPayForPopCash = (LinearLayout) mPopupWindow.getItemView(R.id.ll_payfor_pop_xianjin);

        setIconFont(new TextView[]{tvPayforPopErweiIcon, tvPayforPopSaoIcon, tvPayforPopXianjinIcon});
        // 扫一扫
        llPayForPopScan.setOnClickListener(this);
        // 二维码
        llPayForPopCode.setOnClickListener(this);
        // 现金
        llPayForPopCash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_payfor_pop_sao:
                mPopupWindow.dismiss();
                createOrderInfo("在线支付");
                Global.getSpGlobalUtil().setReceivableMoney(mTotalMoney);
                submitOrder(mSubmitOrderBean, "scan");
                break;
            case R.id.ll_payfor_pop_erwei:
                mPopupWindow.dismiss();
                // 提交订单
                createOrderInfo("在线支付");
                submitOrder(mSubmitOrderBean, "qrcode");
                Global.getSpGlobalUtil().setReceivableMoney(mTotalMoney);
                break;
            case R.id.ll_payfor_pop_xianjin:
                mPopupWindow.dismiss();
                createOrderInfo("现金");
                startActivity(new Intent(this, CollectMoneyForCashActivity.class)
                        .putExtra("bean", new Gson().toJson(mSubmitOrderBean)));
                break;
            default:
                break;
        }
    }

    /**
     * @return 要提交的数据
     * @desc 生成订单数据
     * @anthor lpc
     * @date: 2018/7/19
     */
    private void createOrderInfo(String paymentType) {
        mSubmitOrderBean = new SubmitOrderBean();
        mSubmitOrderBean.setOrderAmount(mTotalMoney);
        // 订单优惠
        String orderDiscount = "0";
        if (!TextUtils.isEmpty(mEtMoney.getText().toString())) {
            // 订单金额 - 用户输入的金额 = 优惠金额
            String s = new BigDecimal(mTotalMoney).subtract(new BigDecimal(mEtMoney.getText().toString())).setScale(2).toString();
            if (Double.valueOf(s) >= 0){
                orderDiscount = s;
            }
            mTotalMoney = mEtMoney.getText().toString();

        }
        // 获取当日序号
        String dateOrderNumber = Global.getSpGlobalUtil().getDateOrderNumber();
        if (dateOrderNumber != null) {
            String[] split = dateOrderNumber.split("=_=");
            if (split[0].equals(DateUtils.getStringDateShort())) {
                dateOrderNumber = String.valueOf(Integer.parseInt(split[1]) + 1);
            } else {
                dateOrderNumber = "1";
            }
        } else {
            dateOrderNumber = "1";
        }
        // 订单提交成功，保存当前时间
        Global.getSpGlobalUtil().setDateOrderNumber(DateUtils.getStringDateShort(), dateOrderNumber);
//        bean.setBatch(batch);
        mSubmitOrderBean.setDateOrderNumber(dateOrderNumber);
        mSubmitOrderBean.setEquipmentSource("");
        mSubmitOrderBean.setEquipmentNumber("");
        mSubmitOrderBean.setTradingTime(DateUtils.getStringToday());
        mSubmitOrderBean.setOrderSource("开单");
        mSubmitOrderBean.setOrderType("0");
        mSubmitOrderBean.setPaymentType(paymentType);
        mSubmitOrderBean.setClerkName(Global.getSpGlobalUtil().getClerkName());
        mSubmitOrderBean.setClerkNumber(Global.getSpGlobalUtil().getClerkNumber());
        mSubmitOrderBean.setShopNumber(getShopNumber());
        mSubmitOrderBean.setOrderRemarks("无");
        mSubmitOrderBean.setAmountReceivable(mTotalMoney);
        mSubmitOrderBean.setAmountCollected(mTotalMoney);
        mSubmitOrderBean.setOrderDiscount(orderDiscount);
        mSubmitOrderBean.setCashAmount("0");
        mSubmitOrderBean.setMemberNumber("");
        mSubmitOrderBean.setOrderSourcePayment("开单");
        mSubmitOrderBean.setFoodProductsResult(mSelectMealEntities);
    }

    /**
     * @param bean
     * @param type 支付方式，扫一扫、二维码
     * @desc 提交订单
     * @anthor lpc
     * @date: 2018/7/19
     */
    private void submitOrder(SubmitOrderBean bean, final String type) {
        String s = new Gson().toJson(bean);

        HttpFactory.postString(ApiConfig.INSERT_ORDER, s, new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    mBatch = response.getMsg();
                    Global.getSpGlobalUtil().setBatch(mBatch);
                    if ("qrcode".equals(type)) {
                        Intent intent = new Intent(CollectMoneyActivity.this, QRCodeActivity.class);
                        intent.putExtra("batch", mBatch);
                        startActivity(intent);
                    } else {
                        new IntentIntegrator(CollectMoneyActivity.this).
                                setCaptureActivity(ScanQRCodeActivity.class)
                                // 设置提示语
                                .setPrompt("")
                                // 选择摄像头,可使用前置或者后置
                                .setCameraId(0)
                                // 是否开启声音,扫完码之后会"哔"的一声
                                .setBeepEnabled(false)
                                // 扫完码之后生成二维码的图片
                                .setBarcodeImageEnabled(true)
                                // 初始化扫码
                                .initiateScan();
                    }
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                //扫码成功的 回调
                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (result != null) {
                    if (result.getContents() == null) {
                    } else {
                        // 扫码成功后支付
                        payForScan(result.getContents(), mBatch);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * @param contents 扫描结果
     * @param batch    订单号
     * @desc 扫一扫支付
     * @anthor lpc
     * @date: 2018/7/25
     */
    private void payForScan(String contents, String batch) {
        HttpFactory.post().url(ApiConfig.TABBY_PAY)
                .addParams("equipmentNumber", Global.getSpGlobalUtil().getCode())
                .addParams("equipmentType", "3")
                .addParams("uniquelyCode", Global.getSpGlobalUtil().getAliCode())
                .addParams("uniCodeStandby", Global.getSpGlobalUtil().getWecharCode())
                .addParams("totalFee", mTotalMoney)
                .addParams("authCode", contents)
                .addParams("batch", batch)
                .addParams("storeId", getShopNumber())
                .addParams("operatorId", getClerkNumber())
                .addParams("discountType", Global.getSpGlobalUtil().getDiscountType())
                .addParams("discountMoney", Global.getSpGlobalUtil().getDiscountMoney())
                .addParams("masterSecret", Constant.MASTER_SECRET)
                .addParams("appKey", Constant.APP_KEY)
                .addParams("address", ApiConfig.BASE_URL + "/pay/payCallback").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.showError();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, "onResponse: " + response.toString());
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Global.getSpGlobalUtil().setReceivableMoney("");
        Global.getSpGlobalUtil().setBatch("");
    }

    /**
     * @param event 扫描结果
     * @desc 扫一扫传递过来的扫描结果
     * @anthor lpc
     * @date: 2018/7/25
     */
    @Subscribe
    public void onEventMainThread(SaomahaoBean event) {
        payForScan(event.getSaomahao(), mBatch);
    }

    /**
     * @param
     * @return
     * @desc 在线支付成功后，极光推送回来的结果
     * @anthor lpc
     * @date: 2018/7/25
     */
    @Subscribe
    public void onEventMainThread(PayOnLineSuccessBean payOnLineSuccessBean) {
        Log.e(TAG, "onEventMainThread: 在线支付成功后");

        Intent successIntent = new Intent(this, OrderDetailActivity.class);
        successIntent.putExtra("batch", payOnLineSuccessBean.getBatch());
        successIntent.putExtra("collect","0");
        startActivity(successIntent);
    }

    //=============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
