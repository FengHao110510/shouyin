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
import com.google.zxing.integration.android.IntentIntegrator;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.payfor.payfor.ErweimaActivity;
import com.hongsou.douguoshouyin.activity.payfor.payfor.ScanQRCodeActivity;
import com.hongsou.douguoshouyin.activity.payfor.payfor.SuccessActivity;
import com.hongsou.douguoshouyin.adapter.CollectMoneyAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.db.SelectMealEntity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.SubmitOrderBean;
import com.hongsou.douguoshouyin.tool.DateUtils;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ScreenUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;
import com.hongsou.douguoshouyin.views.CustomPopupWindow;
import com.hongsou.greendao.gen.SelectMealEntityDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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


    @Override
    public int initLayout() {
        return R.layout.module_activity_collect_money;
    }

    @Override
    protected void init() {
        mSelectMealEntityDao = BaseApplication.getApplication().getDaoSession().getSelectMealEntityDao();
        mRvCollectMoney.setLayoutManager(new LinearLayoutManager(this));
        mSelectMealEntities = mSelectMealEntityDao.loadAll();
        Log.e(TAG, "init: " + mSelectMealEntities.size());
        mCollectMoneyAdapter = new CollectMoneyAdapter(mSelectMealEntities);
        mRvCollectMoney.setAdapter(mCollectMoneyAdapter);

        if (getIntent().hasExtra("money")) {
            mTotalMoney = getIntent().getStringExtra("money");
            mTvTotalMoney.setText(mTotalMoney);
            mTvFoodCount.setText(getIntent().getStringExtra("foodCount"));
            mTvSelectCount.setText(getIntent().getStringExtra("count"));
        }
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
                ToastUtil.showToast("扫一扫");
                break;
            case R.id.ll_payfor_pop_erwei:
                mPopupWindow.dismiss();
                Intent intent = new Intent(CollectMoneyActivity.this, ErweimaActivity.class);
                startActivity(intent);
                ToastUtil.showToast("二维码");
                break;
            case R.id.ll_payfor_pop_xianjin:
                mPopupWindow.dismiss();
                createOrderInfo();
                startActivity(new Intent(this, CollectMoneyForCashActivity.class)
                        .putExtra("bean", new Gson().toJson(createOrderInfo())));
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
    private SubmitOrderBean createOrderInfo() {
        SubmitOrderBean bean = new SubmitOrderBean();

        if (!TextUtils.isEmpty(mEtMoney.getText().toString())) {
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
        bean.setDateOrderNumber(dateOrderNumber);
        bean.setEquipmentSource("");
        bean.setEquipmentNumber("");
        bean.setTradingTime(DateUtils.getStringToday());
        bean.setOrderSource("");
        bean.setOrderType("0");
        bean.setPaymentType("现金");
        bean.setClerkName(Global.getSpGlobalUtil().getClerkName());
        bean.setClerkNumber(Global.getSpGlobalUtil().getClerkNumber());
        bean.setShopNumber(getShopNumber());
        bean.setOrderRemarks("无");
        bean.setOrderAmount(mTotalMoney);
        bean.setAmountReceivable(mTotalMoney);
        bean.setAmountCollected(mTotalMoney);
        bean.setCashAmount("0");
        bean.setMemberNumber("");
        bean.setOrderSourcePayment("开单");
        bean.setFoodProductsResult(mSelectMealEntities);

        return bean;
    }

    /**
     * @param
     * @param bean
     * @desc 提交订单
     * @anthor lpc
     * @date: 2018/7/19
     */
    private void submitOrder(SubmitOrderBean bean) {
        String s = new Gson().toJson(bean);

        HttpFactory.postString(ApiConfig.INSERT_ORDER, s, new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    String batch = response.getMsg();
                    Intent intent = new Intent(CollectMoneyActivity.this, SuccessActivity.class);
                    startActivity(intent);
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    //=============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
