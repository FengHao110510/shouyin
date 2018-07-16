package com.hongsou.douguoshouyin.activity.payfor;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.http.ApiConfig;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.views.CustomDatePicker;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import okhttp3.Call;

/**
 * 统计页面
 */
public class TongjiActivity extends BaseActivity {


    @BindView(R.id.tv_payfor_tongji_jiaoyijine)
    TextView tvPayforTongjiJiaoyijine;
    @BindView(R.id.tv_payfor_tongji_jiaoyibishu)
    TextView tvPayforTongjiJiaoyibishu;
    @BindView(R.id.tv_payfor_tongji_tuikuanjine)
    TextView tvPayforTongjiTuikuanjine;
    @BindView(R.id.tv_payfor_tongji_tuikuanbishu)
    TextView tvPayforTongjiTuikuanbishu;
    @BindView(R.id.tv_payfor_tongji_zhifubao)
    TextView tvPayforTongjiZhifubao;
    @BindView(R.id.tv_payfor_tongji_weixin)
    TextView tvPayforTongjiWeixin;
    @BindView(R.id.tv_payfor_tongji_xianjin)
    TextView tvPayforTongjiXianjin;
    @BindView(R.id.tv_payfor_tongji_yinlianka)
    TextView tvPayforTongjiYinlianka;
    @BindView(R.id.rv_payfor_tongji_xiaoshou)
    RecyclerView rvPayforTongjiXiaoshou;
    @BindView(R.id.tv_titlebar_tongji_finish_back)
    TextView tvTitlebarTongjiFinishBack;
    @BindView(R.id.tv_titlebar_tongji_title)
    TextView tvTitlebarTongjiTitle;
    @BindView(R.id.tv_titlebar_payfor_tongji_shaixuan_icon)
    TextView tvTitlebarPayforTongjiShaixuanIcon;
    @BindView(R.id.ll_titlebar_payfor_tongji_shaixuan)
    LinearLayout llTitlebarPayforTongjiShaixuan;


    private Dialog dialog;
    private String now;//现在时间
    private CustomDatePicker customDatePickerStart, customDatePickerEnd;//开始结束时间控制器

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_tongji;
    }

    @Override
    protected void init() {
        initView();
        initData();
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvTitlebarTongjiFinishBack, tvTitlebarPayforTongjiShaixuanIcon});
        tvTitlebarTongjiTitle.setText("统计");


    }


    @Override
    public void initData() {
        tongji();

    }

    /**
     * 走统计接口获取数据  TODO 传时间
     */
    private void tongji() {
        showLoadingDialog();
        HttpFactory.post().url(ApiConfig.TONGJI).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
            }
        });
    }

    @OnClick({R.id.tv_titlebar_tongji_finish_back, R.id.ll_titlebar_payfor_tongji_shaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_tongji_finish_back:
                finishActivity();
                break;
            case R.id.ll_titlebar_payfor_tongji_shaixuan:
                //弹框筛选

                showTimeDialog();

                break;
        }
    }


    //================================================dialog========================================

    /**
     * 展示时间筛选dialog
     */
    private void showTimeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_time_shaixuan, null);
        final TextView tv_dialog_time_shaixuan_start = view.findViewById(R.id.tv_dialog_time_shaixuan_start);
        final TextView tv_dialog_time_shaixuan_end = view.findViewById(R.id.tv_dialog_time_shaixuan_end);
        TextView tv_dialog_time_shaixuan_bt = view.findViewById(R.id.tv_dialog_time_shaixuan_bt);

        tv_dialog_time_shaixuan_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断起始时间不得大于结束时间
                if (tv_dialog_time_shaixuan_start.getText().toString().compareTo(tv_dialog_time_shaixuan_end.getText().toString()) > 0) {
                    ToastUtil.showToast("起始时间不得大于结束时间");
                } else {
                    tongji();
                    dialog.dismiss();
                }

            }
        });

        //设置开始时间
        tv_dialog_time_shaixuan_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerStart.show(tv_dialog_time_shaixuan_start.getText().toString());
            }
        });

        //设置结束时间
        tv_dialog_time_shaixuan_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerEnd.show(tv_dialog_time_shaixuan_end.getText().toString());
            }
        });

        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        //初始化起始时间
        initDateStartPicker(now, tv_dialog_time_shaixuan_start);
        //初始化结束时间
        initDateEndPicker("2010-01-01 00:00", now, tv_dialog_time_shaixuan_end);


        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 7 / 8, h * 2 / 7);

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


}
