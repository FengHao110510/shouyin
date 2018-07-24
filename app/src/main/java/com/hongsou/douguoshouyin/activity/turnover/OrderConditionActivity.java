package com.hongsou.douguoshouyin.activity.turnover;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.OrderConditionBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CustomDatePicker;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/23
 * <p>
 * @desc 筛选条件
 */
public class OrderConditionActivity extends BaseActivity {


    @BindView(R.id.tfl_order_source)
    TagFlowLayout mTflOrderSource;
    @BindView(R.id.ll_order_source)
    LinearLayout mLlOrderSource;
    @BindView(R.id.tfl_pay_status)
    TagFlowLayout mTflPayStatus;
    @BindView(R.id.tfl_pay_channel)
    TagFlowLayout mTflPayChannel;
    @BindView(R.id.tv_turnover_turnover_shaixuan_starttime)
    TextView mTvTurnoverTurnoverShaixuanStarttime;
    @BindView(R.id.tv_turnover_turnover_shaixuan_endtime)
    TextView mTvTurnoverTurnoverShaixuanEndtime;
    @BindView(R.id.bt_turnover_turnover_shaixuan_shaixuan)
    Button mBtTurnoverTurnoverShaixuanShaixuan;

    /**
     * 订单来源选择
     */
    private List<OrderConditionBean> orderSourceList;
    /**
     * 支付状态选择
     */
    private List<OrderConditionBean> payStatusList;
    /**
     * 收款支付渠道选择
     */
    private List<OrderConditionBean> payChannelList;
    //现在时间
    private String now;
    private LayoutInflater mInflater;
    private CustomDatePicker customDatePickerStart;
    private CustomDatePicker customDatePickerEnd;

    /**
     * 选择的筛选条件
     */
    private String orderSource;
    private String payStatus;
    private String payChannel;
    private int mType;

    @Override
    public int initLayout() {
        return R.layout.module_activity_turnover_turnover_shaixuan;
    }

    @Override
    protected void init() {
        if (getIntent().hasExtra("type")) {
            mType = getIntent().getIntExtra("type", 0);
            if (mType == 0) {
                mLlOrderSource.setVisibility(View.VISIBLE);
            } else {
                mLlOrderSource.setVisibility(View.GONE);
            }
        }
        mInflater = LayoutInflater.from(this);
        orderSourceList = new ArrayList<>();
        payStatusList = new ArrayList<>();
        payChannelList = new ArrayList<>();
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        //初始化起始时间
        initDateStartPicker(now);
        //初始化结束时间
        initDateEndPicker("2010-01-01 00:00", now);

        initData();
    }


    @Override
    public void initData() {
        //TODO 筛选状态回显
        HttpFactory.get().url(ApiConfig.GET_ORDER_CONDITION)
                .build()
                .execute(new ResponseCallback<RootBean<List<OrderConditionBean>>>(this) {
                    @Override
                    public void onResponse(RootBean<List<OrderConditionBean>> response, int id) {
                        if (response.isSuccess()) {
                            List<OrderConditionBean> data = response.getData();
                            for (OrderConditionBean datum : data) {
                                if ("1".equals(datum.getOrderSourceType())) {
                                    orderSourceList.add(datum);
                                } else if ("2".equals(datum.getOrderSourceType())) {
                                    payChannelList.add(datum);
                                } else if ("3".equals(datum.getOrderSourceType())) {
                                    payStatusList.add(datum);
                                }
                            }
                            renderView(orderSourceList, mTflOrderSource, "orderSource");
                            renderView(payStatusList, mTflPayStatus, "payStatus");
                            renderView(payChannelList, mTflPayChannel, "payChannel");
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    /**
     * @desc 显示数据
     * @anthor lpc
     * @date: 2018/7/23
     */
    private void renderView(final List<OrderConditionBean> data, final TagFlowLayout layout, final String type) {
        if (data != null) {
            layout.setAdapter(new TagAdapter<OrderConditionBean>(data) {
                @Override
                public View getView(FlowLayout parent, int position, OrderConditionBean bean) {
                    TextView textView = (TextView) mInflater.inflate(R.layout.module_tag_flow_text_view, layout, false);
                    textView.setText(bean.getOrderSourcePayment());
                    return textView;
                }
            });
            // 选择规格
            layout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    if ("orderSource".equals(type)) {
                        orderSource = data.get(position).getOrderSourcePayment();
                    } else if ("payStatus".equals(type)) {
                        payStatus = data.get(position).getOrderSourcePayment();
                    } else if ("payChannel".equals(type)) {
                        payChannel = data.get(position).getOrderSourcePayment();
                    }
                    return false;
                }
            });
        }
    }

    /**
     * @param endTime 最后期限  初始化开始时间
     * @desc 初始化开始时间
     * @anthor lpc
     * @date: 2018/7/23
     */
    private void initDateStartPicker(String endTime) {
        mTvTurnoverTurnoverShaixuanStarttime.setText(endTime);
        customDatePickerStart = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                // 回调接口，获得选中的时间
                mTvTurnoverTurnoverShaixuanStarttime.setText(time);
            }
        }, "2010-01-01 00:00", endTime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        // 显示时和分
        customDatePickerStart.showSpecificTime(true);
        // 允许循环滚动
        customDatePickerStart.setIsLoop(true);
    }

    /**
     * @param startTime 开始期限  初始化开始时间
     * @param endTime   endTime 最后期限 现在
     * @desc 初始化结束时间
     * @anthor lpc
     * @date: 2018/7/23
     */
    private void initDateEndPicker(String startTime, String endTime) {
        customDatePickerEnd = null;
        mTvTurnoverTurnoverShaixuanEndtime.setText(endTime);
        customDatePickerEnd = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                // 回调接口，获得选中的时间
                mTvTurnoverTurnoverShaixuanEndtime.setText(time);

            }
        }, startTime, endTime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        // 显示时和分
        customDatePickerEnd.showSpecificTime(true);
        // 允许循环滚动
        customDatePickerEnd.setIsLoop(true);
    }

    @OnClick({R.id.tv_turnover_turnover_shaixuan_starttime, R.id.tv_turnover_turnover_shaixuan_endtime, R.id.bt_turnover_turnover_shaixuan_shaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_turnover_turnover_shaixuan_starttime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerStart.show(mTvTurnoverTurnoverShaixuanStarttime.getText().toString());
                break;
            case R.id.tv_turnover_turnover_shaixuan_endtime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerEnd.show(mTvTurnoverTurnoverShaixuanEndtime.getText().toString());
                break;
            case R.id.bt_turnover_turnover_shaixuan_shaixuan:
                //判断起始时间不得大于结束时间
                if (mTvTurnoverTurnoverShaixuanStarttime.getText().toString().compareTo(mTvTurnoverTurnoverShaixuanEndtime.getText().toString()) > 0) {
                    ToastUtil.showToast("起始时间不得大于结束时间");
                } else {
                    //上个界面应该 startActivityForResult  这里返回数据给上个fragment  重新加载筛选后的数据
                    HashMap<String, String> data = new HashMap<>();
                    //订单来源
                    data.put("orderSourcePayment", orderSource);
                    //订单类型
                    data.put("orderType", payStatus);
                    //支付方式
                    data.put("paymentType", payChannel);
                    //开始时间
                    data.put("tradingTime", mTvTurnoverTurnoverShaixuanStarttime.getText().toString());
                    //结束时间
                    data.put("endTime", mTvTurnoverTurnoverShaixuanEndtime.getText().toString());
                    Intent reIntent = new Intent();
                    reIntent.putExtra("data", ((Serializable) data));
                    setResult(mType, reIntent);
                    finishActivity();
                }
                break;
            default:
                break;
        }
    }

    //===========================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
