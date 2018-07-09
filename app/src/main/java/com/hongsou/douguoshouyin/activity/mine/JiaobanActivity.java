package com.hongsou.douguoshouyin.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.views.CustomDatePicker;
import com.hongsou.douguoshouyin.tool.ToastUtil;

/**
 * 交班页面
 */
public class JiaobanActivity extends BaseActivity {

    @BindView(R.id.tv_mine_jiaoban_jiaoyijine)
    TextView tvMineJiaobanJiaoyijine;
    @BindView(R.id.tv_mine_jiaoban_jiaoyibishu)
    TextView tvMineJiaobanJiaoyibishu;
    @BindView(R.id.tv_mine_jiaoban_tuikuanjine)
    TextView tvMineJiaobanTuikuanjine;
    @BindView(R.id.tv_mine_jiaoban_tuikuanbishu)
    TextView tvMineJiaobanTuikuanbishu;
    @BindView(R.id.tv_mine_jiaoban_zhifubao)
    TextView tvMineJiaobanZhifubao;
    @BindView(R.id.tv_mine_jiaoban_weixin)
    TextView tvMineJiaobanWeixin;
    @BindView(R.id.tv_mine_jiaoban_xianjin)
    TextView tvMineJiaobanXianjin;
    @BindView(R.id.tv_mine_jiaoban_yinlianka)
    TextView tvMineJiaobanYinlianka;
    @BindView(R.id.tv_mine_jiaoban_starttime)
    TextView tvMineJiaobanStarttime;
    @BindView(R.id.tv_mine_jiaoban_endtime)
    TextView tvMineJiaobanEndtime;
    @BindView(R.id.bt_mine_jiaoban_jiaoban)
    Button btMineJiaobanJiaoban;

    private String now;//现在时间
    private CustomDatePicker customDatePickerStart, customDatePickerEnd;//开始结束时间控制器

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_jiaoban;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("交班打印");
    }

    @Override
    public void initView() {
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        //初始化起始时间
        initDateStartPicker(now);
        //初始化结束时间
        initDateEndPicker("2010-01-01 00:00", now);
    }

    //endtime 最后期限  初始化开始时间
    private void initDateStartPicker(String endtime) {

        tvMineJiaobanStarttime.setText(endtime);
        customDatePickerStart = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvMineJiaobanStarttime.setText(time);
            }
        }, "2010-01-01 00:00", endtime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerStart.showSpecificTime(true); // 显示时和分
        customDatePickerStart.setIsLoop(true); // 允许循环滚动
    }

    //starttime 开始期限  初始化开始时间  endtime 最后期限 现在

    private void initDateEndPicker(String starttime, String endtime) {
        customDatePickerEnd = null;
        tvMineJiaobanEndtime.setText(endtime);
        customDatePickerEnd = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvMineJiaobanEndtime.setText(time);
            }
        }, starttime, endtime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerEnd.showSpecificTime(true); // 显示时和分
        customDatePickerEnd.setIsLoop(true); // 允许循环滚动
    }

    @Override
    public void initData() {

        //初始化各种数据  走接口 TODO 默认一天？？
    }


    @OnClick({R.id.tv_mine_jiaoban_starttime, R.id.tv_mine_jiaoban_endtime, R.id.bt_mine_jiaoban_jiaoban})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_jiaoban_starttime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerStart.show(tvMineJiaobanStarttime.getText().toString());
                break;
            case R.id.tv_mine_jiaoban_endtime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerEnd.show(tvMineJiaobanEndtime.getText().toString());
                break;
            case R.id.bt_mine_jiaoban_jiaoban:
                //交班打印小票接口  TODO  只打印？  包括查询？
                //判断起始时间不得大于结束时间

                if (tvMineJiaobanStarttime.getText().toString().compareTo(tvMineJiaobanEndtime.getText().toString()) > 0) {
                    ToastUtil.showToast("起始时间不得大于结束时间");
                } else {
                    jiaoban();
                }

                break;
        }
    }

    /**
     * 交班查询接口
     */

    private void jiaoban() {

    }


    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
