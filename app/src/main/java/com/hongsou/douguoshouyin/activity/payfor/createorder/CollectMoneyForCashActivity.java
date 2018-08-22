package com.hongsou.douguoshouyin.activity.payfor.createorder;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.turnover.OrderDetailActivity;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.SubmitOrderBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;
import com.hongsou.douguoshouyin.views.keyboard.KeyboardUtil;
import com.hongsou.douguoshouyin.views.keyboard.VirtualKeyboardView;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/19
 * <p>
 * @desc 现金收款页面
 */
public class CollectMoneyForCashActivity extends BaseActivity {


    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;
    @BindView(R.id.tv_receivable_money_icon)
    TextView mTvReceivableMoneyIcon;
    @BindView(R.id.tv_receivable_money)
    TextView mTvReceivableMoney;
    @BindView(R.id.tv_collect_money_icon)
    TextView mTvCollectMoneyIcon;
    @BindView(R.id.et_collect_money)
    EditText mEtCollectMoney;
    @BindView(R.id.keyboard_collect_money)
    VirtualKeyboardView mKeyboardCollectMoney;
    @BindView(R.id.tv_give_money)
    TextView mTvGiveMoney;
    @BindView(R.id.tv_give_money_icon)
    TextView mTvGiveMoneyIcon;


    private SubmitOrderBean bean;
    private double mGiveMoney;

    @Override
    public int initLayout() {
        return R.layout.module_activity_collect_money_cash;
    }

    @Override
    protected void init() {

        if (getIntent().hasExtra("bean")) {
            String s = getIntent().getStringExtra("bean");
            bean = new Gson().fromJson(s, SubmitOrderBean.class);
        }
        setIconFont(new TextView[]{mTvCollectMoneyIcon, mTvReceivableMoneyIcon, mTvGiveMoneyIcon});
        mTvReceivableMoney.setText(bean.getAmountReceivable());

        // 监听收款金额的变化
        mEtCollectMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (KeyboardUtil.checkMoney(s.toString())) {
                    // 输入的收款金额
                    String co_m = s.toString();
                    // 订单应收金额
                    String re_m = mTvReceivableMoney.getText().toString();
                    // 差值
                    BigDecimal bigDecimal = new BigDecimal(co_m).setScale(2, BigDecimal.ROUND_DOWN)
                            .subtract(new BigDecimal(re_m));
                    mGiveMoney = Double.valueOf(bigDecimal.setScale(2).toString());
                    if (mGiveMoney <= 0) {
                        mTvGiveMoney.setText("0.0");
                    } else {
                        mTvGiveMoney.setText(String.valueOf(mGiveMoney));
                    }
                } else {
                    mTvGiveMoney.setText("0.0");
                }
            }
        });

        // 数字键盘的初始化和监听事件
        new KeyboardUtil(mKeyboardCollectMoney, mEtCollectMoney, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGiveMoney >= 0 && KeyboardUtil.checkMoney(mEtCollectMoney.getText().toString())) {
                    bean.setCashAmount(mTvGiveMoney.getText().toString());
                    bean.setAmountReceivable(mTvReceivableMoney.getText().toString());
                    bean.setAmountCollected(mEtCollectMoney.getText().toString());
                    submitOrder(bean);
                } else {
                    ToastUtil.showToast("请输入正确的收款金额");
                }
            }
        });
    }

    @Override
    public void initData() {
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
                    Intent intent = new Intent(CollectMoneyForCashActivity.this, OrderDetailActivity.class);
                    intent.putExtra("batch", batch);
                    intent.putExtra("collect", "0");
                    startActivity(intent);
                    finish();
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
