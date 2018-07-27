package com.hongsou.douguoshouyin.activity.payfor;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.StatisticsAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.javabean.StatisticsRankingsBean;
import com.hongsou.douguoshouyin.tool.DateUtils;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;
import com.hongsou.douguoshouyin.views.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/26
 * <p>
 * @desc 统计页面
 */
public class StatisticsActivity extends BaseActivity {


    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;
    @BindView(R.id.tv_statistics_start_time)
    TextView mTvStatisticsStartTime;
    @BindView(R.id.tv_statistics_end_time)
    TextView mTvStatisticsEndTime;
    @BindView(R.id.tv_statistics_order_money)
    TextView mTvStatisticsOrderMoney;
    @BindView(R.id.tv_statistics_order_count)
    TextView mTvStatisticsOrderCount;
    @BindView(R.id.tv_statistics_back_money)
    TextView mTvStatisticsBackMoney;
    @BindView(R.id.tv_statistics_back_count)
    TextView mTvStatisticsBackCount;
    @BindView(R.id.tv_statistics_alipay)
    TextView mTvStatisticsAlipay;
    @BindView(R.id.tv_statistics_wechat)
    TextView mTvStatisticsWechat;
    @BindView(R.id.tv_statistics_cash)
    TextView mTvStatisticsCash;
    @BindView(R.id.tv_statistics_bank_card)
    TextView mTvStatisticsBankCard;
    @BindView(R.id.rv_statistics_rankings)
    RecyclerView mRvStatisticsRankings;

    private Dialog dialog;
    private String now;//现在时间
    private CustomDatePicker customDatePickerStart, customDatePickerEnd;//开始结束时间控制器

    private StatisticsAdapter mStatisticsAdapter;

    @Override
    public int initLayout() {
        return R.layout.module_activity_statistics;
    }

    @Override
    protected void init() {
        initView();
        initData(DateUtils.getCurrent00Time(), DateUtils.getStringDateNotSS());
    }

    @Override
    public void initView() {
        mRvStatisticsRankings.setLayoutManager(new LinearLayoutManager(this));
        // 筛选
        mTopBar.setRightViewClickListener(new CommonTopBar.ClickCallBack() {
            @Override
            public void onClick(View v) {
                //弹框筛选
                showTimeDialog();
            }
        });
    }

    /**
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @desc 获取页面数据
     * @anthor lpc
     * @date: 2018/7/26
     */
    public void initData(String startTime, String endTime) {
        HttpFactory.get().url(ApiConfig.GET_STATISTICS)
                .addParams("shopNumber", getShopNumber())
                .addParams("clerkNumber", "")
                .addParams("startTime", startTime)
                .addParams("endTime", endTime)
                .build().execute(new ResponseCallback<RootBean<List<StatisticsRankingsBean>>>(this) {
            @Override
            public void onResponse(RootBean<List<StatisticsRankingsBean>> response, int id) {
                if (response.isSuccess()){
                    mStatisticsAdapter = new StatisticsAdapter(response.getData());
                    mRvStatisticsRankings.setAdapter(mStatisticsAdapter);
                }else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    //================================================dialog========================================
    /**
     * 展示时间筛选dialog
     */
    private void showTimeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_time_statistics, null);
        final TextView tvDialogTimeStart = view.findViewById(R.id.tv_dialog_time_start);
        final TextView tvDialogTimeEnd = view.findViewById(R.id.tv_dialog_time_end);
        TextView tvDialogTimeBtn = view.findViewById(R.id.tv_dialog_time_bt);
        TextView tvDialogTimeTitle = view.findViewById(R.id.tv_dialog_time_title);
        tvDialogTimeTitle.setText("统计时间");

        tvDialogTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断起始时间不得大于结束时间
                if (tvDialogTimeStart.getText().toString().compareTo(tvDialogTimeEnd.getText().toString()) > 0) {
                    ToastUtil.showToast("起始时间不得大于结束时间");
                } else {
                    String startTime = tvDialogTimeStart.getText().toString();
                    String endTime = tvDialogTimeEnd.getText().toString();
                    initData(startTime, endTime);
                    dialog.dismiss();
                }

            }
        });
        //设置开始时间
        tvDialogTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerStart.show(tvDialogTimeStart.getText().toString());
            }
        });
        //设置结束时间
        tvDialogTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerEnd.show(tvDialogTimeEnd.getText().toString());
            }
        });
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        //初始化起始时间
        initDateStartPicker(now, tvDialogTimeStart);
        //初始化结束时间
        initDateEndPicker("2010-01-01 00:00", now, tvDialogTimeEnd);

        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 7 / 8, h * 5 / 21);

        dialog = new Dialog(this, R.style.TRCommonDialog);
        dialog.setContentView(view, params);
        dialog.show();
    }

    //endtime 最后期限  初始化开始时间
    private void initDateStartPicker(String endtime, final TextView tv_dialog_time_shaixuan_start) {

        tv_dialog_time_shaixuan_start.setText(endtime);
        customDatePickerStart = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_dialog_time_shaixuan_start.setText(time);
            }
        }, "2010-01-01 00:00", endtime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerStart.showSpecificTime(true); // 显示时和分
        customDatePickerStart.setIsLoop(true); // 允许循环滚动
    }

    //starttime 开始期限  初始化开始时间  endtime 最后期限 现在
    private void initDateEndPicker(String starttime, String endtime, final TextView tv_dialog_time_shaixuan_end) {
        customDatePickerEnd = null;
        tv_dialog_time_shaixuan_end.setText(endtime);
        customDatePickerEnd = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_dialog_time_shaixuan_end.setText(time);
            }
        }, starttime, endtime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerEnd.showSpecificTime(true); // 显示时和分
        customDatePickerEnd.setIsLoop(true); // 允许循环滚动
    }

    //++==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
    }
}
