package com.hongsou.douguoshouyin.activity.turnover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
 * 流水筛选页面  筛选条件从接口获取？？？？
 */
public class TurnoverShaixuanActivity extends BaseActivity {


    @BindView(R.id.tv_shaixuan_finish_back)
    TextView tvShaixuanFinishBack;
    @BindView(R.id.tv_shaixuan_title)
    TextView tvShaixuanTitle;
    @BindView(R.id.tv_shaixuan_chongzhi_icon)
    TextView tvShaixuanChongzhiIcon;
    @BindView(R.id.ll_shaixuan_chongzhi)
    LinearLayout llShaixuanChongzhi;
    @BindView(R.id.tv_turnover_turnover_shaixuan_shoukuanchenggong)
    TextView tvTurnoverTurnoverShaixuanShoukuanchenggong;
    @BindView(R.id.tv_turnover_turnover_shaixuan_tuikuanchenggong)
    TextView tvTurnoverTurnoverShaixuanTuikuanchenggong;
    @BindView(R.id.tv_turnover_turnover_shaixuan_shoukuanshibai)
    TextView tvTurnoverTurnoverShaixuanShoukuanshibai;
    @BindView(R.id.tv_turnover_turnover_shaixuan_weixin)
    TextView tvTurnoverTurnoverShaixuanWeixin;
    @BindView(R.id.tv_turnover_turnover_shaixuan_zhifubao)
    TextView tvTurnoverTurnoverShaixuanZhifubao;
    @BindView(R.id.tv_turnover_turnover_shaixuan_xianjin)
    TextView tvTurnoverTurnoverShaixuanXianjin;
    @BindView(R.id.tv_turnover_turnover_shaixuan_yinhangka)
    TextView tvTurnoverTurnoverShaixuanYinhangka;
    @BindView(R.id.tv_turnover_turnover_shaixuan_erweimazhuopai)
    TextView tvTurnoverTurnoverShaixuanErweimazhuopai;
    @BindView(R.id.bt_turnover_turnover_shaixuan_shaixuan)
    Button btTurnoverTurnoverShaixuanShaixuan;
    @BindView(R.id.tv_turnover_turnover_shaixuan_starttime)
    TextView tvTurnoverTurnoverShaixuanStarttime;
    @BindView(R.id.tv_turnover_turnover_shaixuan_endtime)
    TextView tvTurnoverTurnoverShaixuanEndtime;

    private String zhuangtaiSelect;//支付状态选择
    private String fangshiSelect;//支付方式选择
    private String now;//现在时间

    private CustomDatePicker customDatePickerStart, customDatePickerEnd;

    @Override
    public int initLayout() {
        return R.layout.module_activity_turnover_turnover_shaixuan;
    }

    @Override
    protected void init() {

        initView();
        initData();
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvShaixuanChongzhiIcon, tvShaixuanFinishBack});
        tvShaixuanTitle.setText("流水筛选");

        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        //初始化起始时间
        initDateStartPicker(now);
        //初始化结束时间
        initDateEndPicker("2010-01-01 00:00", now);
    }

    @Override
    public void initData() {
        //TODO 筛选状态回显

    }

    //endtime 最后期限  初始化开始时间
    private void initDateStartPicker(String endtime) {

        tvTurnoverTurnoverShaixuanStarttime.setText(endtime);
        customDatePickerStart = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTurnoverTurnoverShaixuanStarttime.setText(time);
            }
        }, "2010-01-01 00:00", endtime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerStart.showSpecificTime(true); // 显示时和分
        customDatePickerStart.setIsLoop(true); // 允许循环滚动
    }

    //starttime 开始期限  初始化开始时间  endtime 最后期限 现在
    private void initDateEndPicker(String starttime, String endtime) {
        customDatePickerEnd = null;
        tvTurnoverTurnoverShaixuanEndtime.setText(endtime);
        customDatePickerEnd = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTurnoverTurnoverShaixuanEndtime.setText(time);
            }
        }, starttime, endtime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerEnd.showSpecificTime(true); // 显示时和分
        customDatePickerEnd.setIsLoop(true); // 允许循环滚动
    }

    @OnClick({R.id.tv_turnover_turnover_shaixuan_starttime, R.id.tv_turnover_turnover_shaixuan_endtime, R.id.tv_shaixuan_finish_back, R.id.ll_shaixuan_chongzhi, R.id.tv_turnover_turnover_shaixuan_shoukuanchenggong, R.id.tv_turnover_turnover_shaixuan_tuikuanchenggong, R.id.tv_turnover_turnover_shaixuan_shoukuanshibai, R.id.tv_turnover_turnover_shaixuan_weixin, R.id.tv_turnover_turnover_shaixuan_zhifubao, R.id.tv_turnover_turnover_shaixuan_xianjin, R.id.tv_turnover_turnover_shaixuan_yinhangka, R.id.tv_turnover_turnover_shaixuan_erweimazhuopai, R.id.bt_turnover_turnover_shaixuan_shaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shaixuan_finish_back:
                finishActivity();
                break;
            case R.id.tv_turnover_turnover_shaixuan_shoukuanchenggong:
                zhuangtaiSelect = "收款成功";
                tvTurnoverTurnoverShaixuanShoukuanchenggong.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverTurnoverShaixuanTuikuanchenggong.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanShoukuanshibai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                break;
            case R.id.tv_turnover_turnover_shaixuan_tuikuanchenggong:
                zhuangtaiSelect = "退款成功";
                tvTurnoverTurnoverShaixuanShoukuanchenggong.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanTuikuanchenggong.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverTurnoverShaixuanShoukuanshibai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_turnover_shaixuan_shoukuanshibai:
                zhuangtaiSelect = "收款失败";
                tvTurnoverTurnoverShaixuanShoukuanchenggong.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanTuikuanchenggong.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanShoukuanshibai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));

                break;
            case R.id.tv_turnover_turnover_shaixuan_weixin:
                fangshiSelect = "微信";
                tvTurnoverTurnoverShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverTurnoverShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_turnover_shaixuan_zhifubao:
                fangshiSelect = "支付宝";
                tvTurnoverTurnoverShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverTurnoverShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_turnover_shaixuan_xianjin:
                fangshiSelect = "现金";
                tvTurnoverTurnoverShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverTurnoverShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_turnover_shaixuan_yinhangka:
                fangshiSelect = "银行卡";
                tvTurnoverTurnoverShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverTurnoverShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_turnover_shaixuan_erweimazhuopai:
                fangshiSelect = "二维码桌卡";
                tvTurnoverTurnoverShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));

                break;
            case R.id.ll_shaixuan_chongzhi:
                fangshiSelect = "";
                zhuangtaiSelect = "";
                tvTurnoverTurnoverShaixuanShoukuanchenggong.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanTuikuanchenggong.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanShoukuanshibai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverTurnoverShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                //重置时间
                tvTurnoverTurnoverShaixuanStarttime.setText(now);
                tvTurnoverTurnoverShaixuanEndtime.setText(now);
                break;

            case R.id.tv_turnover_turnover_shaixuan_starttime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerStart.show(tvTurnoverTurnoverShaixuanStarttime.getText().toString());
                break;
            case R.id.tv_turnover_turnover_shaixuan_endtime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerEnd.show(tvTurnoverTurnoverShaixuanEndtime.getText().toString());
                break;
            case R.id.bt_turnover_turnover_shaixuan_shaixuan:
                //判断起始时间不得大于结束时间
                if (tvTurnoverTurnoverShaixuanStarttime.getText().toString().compareTo(tvTurnoverTurnoverShaixuanEndtime.getText().toString()) > 0) {
                    ToastUtil.showToast("起始时间不得大于结束时间");
                } else {

                    //上个界面应该 startActivityForResult  这里返回数据给上个fragment  重新加载筛选后的数据 TODO
                    Intent reIntent = new Intent();
                    reIntent.putExtra("","");
                    setResult(RESULT_OK,reIntent);
                    finishActivity();
                }
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
