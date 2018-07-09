package douguoshouyin.douguoshouyin.activity.turnover;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;
import douguoshouyin.douguoshouyin.tool.CustomDatePicker;
import douguoshouyin.douguoshouyin.tool.ToastUtil;

public class OrderShaixuanActivity extends BaseActivity {


    @BindView(R.id.tv_shaixuan_finish_back)
    TextView tvShaixuanFinishBack;
    @BindView(R.id.tv_shaixuan_title)
    TextView tvShaixuanTitle;
    @BindView(R.id.tv_shaixuan_chongzhi_icon)
    TextView tvShaixuanChongzhiIcon;
    @BindView(R.id.ll_shaixuan_chongzhi)
    LinearLayout llShaixuanChongzhi;
    @BindView(R.id.tv_turnover_order_shaixuan_zhuotai)
    TextView tvTurnoverOrderShaixuanZhuotai;
    @BindView(R.id.tv_turnover_order_shaixuan_kaidan)
    TextView tvTurnoverOrderShaixuanKaidan;
    @BindView(R.id.tv_turnover_order_shaixuan_shoukuan)
    TextView tvTurnoverOrderShaixuanShoukuan;
    @BindView(R.id.tv_turnover_order_shaixuan_weijiezhang)
    TextView tvtvTurnoverOrderShaixuanWeijiezhang;
    @BindView(R.id.tv_turnover_order_shaixuan_yijiezhang)
    TextView tvTurnoverOrderShaixuanYijiezhang;
    @BindView(R.id.tv_turnover_order_shaixuan_yituikuan)
    TextView tvTurnoverOrderShaixuanYituikuan;
    @BindView(R.id.tv_turnover_order_shaixuan_weixin)
    TextView tvTurnoverOrderShaixuanWeixin;
    @BindView(R.id.tv_turnover_order_shaixuan_zhifubao)
    TextView tvTurnoverOrderShaixuanZhifubao;
    @BindView(R.id.tv_turnover_order_shaixuan_xianjin)
    TextView tvTurnoverOrderShaixuanXianjin;
    @BindView(R.id.tv_turnover_order_shaixuan_yinhangka)
    TextView tvTurnoverOrderShaixuanYinhangka;
    @BindView(R.id.tv_turnover_order_shaixuan_erweimazhuopai)
    TextView tvTurntvoverOrderShaixuanErweimazhuopai;
    @BindView(R.id.bt_turnover_order_shaixuan_shaixuan)
    Button btTurnoverOrderShaixuanShaixuan;
    @BindView(R.id.tv_turnover_order_shaixuan_starttime)
    TextView tvTurnoverOrderShaixuanStarttime;
    @BindView(R.id.tv_turnover_order_shaixuan_endtime)
    TextView tvTurnoverOrderShaixuanEndtime;

    private String dingdanSelect;//订单来源选择
    private String zhifuSelect;//支付状态选择
    private String shoukuanSelect;//收款方式选择
    private String now;//现在时间

    private CustomDatePicker customDatePickerStart, customDatePickerEnd;

    @Override
    public int intiLayout() {
        return R.layout.module_activity_turnover_order_shaixuan;
    }

    @Override
    protected void init() {
        initView();
        initData();

    }

    @Override
    public void initView() {
        //初始化视图
        setIconFont(new TextView[]{tvShaixuanChongzhiIcon, tvShaixuanFinishBack});
        tvShaixuanTitle.setText("订单筛选");

        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        //初始化起始时间
        initDateStartPicker(now);
        //初始化结束时间
        initDateEndPicker("2010-01-01 00:00", now);

//        //起始时间改变时 结束时间跟这改变
//        tvTurnoverOrderShaixuanEndtime.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                //随着起始时间更改结束时间
//                initDateEndPicker(tvTurnoverOrderShaixuanStarttime.getText().toString(), now);
//            }
//        });
//
//        //结束时间改变时 起始时间跟这改变
//        tvTurnoverOrderShaixuanEndtime.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                //随着结束时间更改起始时间
//                initDateStartPicker(tvTurnoverOrderShaixuanEndtime.getText().toString());
//            }
//        });
    }

    @Override
    public void initData() {
        //TODO 筛选状态回显

    }

    //endtime 最后期限  初始化开始时间
    private void initDateStartPicker(String endtime) {

        tvTurnoverOrderShaixuanStarttime.setText(endtime);
        customDatePickerStart = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTurnoverOrderShaixuanStarttime.setText(time);
            }
        }, "2010-01-01 00:00", endtime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerStart.showSpecificTime(true); // 显示时和分
        customDatePickerStart.setIsLoop(true); // 允许循环滚动
    }

    //starttime 开始期限  初始化开始时间  endtime 最后期限 现在
    private void initDateEndPicker(String starttime, String endtime) {
        customDatePickerEnd = null;
        tvTurnoverOrderShaixuanEndtime.setText(endtime);
        customDatePickerEnd = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTurnoverOrderShaixuanEndtime.setText(time);
            }
        }, starttime, endtime); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerEnd.showSpecificTime(true); // 显示时和分
        customDatePickerEnd.setIsLoop(true); // 允许循环滚动
    }


    @OnClick({R.id.tv_shaixuan_finish_back, R.id.ll_shaixuan_chongzhi,
            R.id.tv_turnover_order_shaixuan_zhuotai, R.id.tv_turnover_order_shaixuan_kaidan,
            R.id.tv_turnover_order_shaixuan_shoukuan, R.id.tv_turnover_order_shaixuan_weijiezhang,
            R.id.tv_turnover_order_shaixuan_yijiezhang, R.id.tv_turnover_order_shaixuan_yituikuan,
            R.id.tv_turnover_order_shaixuan_weixin, R.id.tv_turnover_order_shaixuan_zhifubao,
            R.id.tv_turnover_order_shaixuan_xianjin, R.id.tv_turnover_order_shaixuan_yinhangka,
            R.id.tv_turnover_order_shaixuan_erweimazhuopai, R.id.bt_turnover_order_shaixuan_shaixuan,
            R.id.tv_turnover_order_shaixuan_starttime, R.id.tv_turnover_order_shaixuan_endtime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shaixuan_finish_back:
                finishActivity();
                break;

            case R.id.tv_turnover_order_shaixuan_zhuotai:
                dingdanSelect = "桌台";
                tvTurnoverOrderShaixuanZhuotai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverOrderShaixuanKaidan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanShoukuan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                break;
            case R.id.tv_turnover_order_shaixuan_kaidan:
                dingdanSelect = "开单";
                tvTurnoverOrderShaixuanZhuotai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanKaidan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverOrderShaixuanShoukuan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_order_shaixuan_shoukuan:
                dingdanSelect = "收款";
                tvTurnoverOrderShaixuanZhuotai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanKaidan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanShoukuan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));

                break;
            case R.id.tv_turnover_order_shaixuan_weijiezhang:
                zhifuSelect = "未结账";
                tvtvTurnoverOrderShaixuanWeijiezhang.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverOrderShaixuanYijiezhang.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYituikuan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));


                break;
            case R.id.tv_turnover_order_shaixuan_yijiezhang:
                zhifuSelect = "已结账";
                tvtvTurnoverOrderShaixuanWeijiezhang.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYijiezhang.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverOrderShaixuanYituikuan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_order_shaixuan_yituikuan:
                zhifuSelect = "已退款";
                tvtvTurnoverOrderShaixuanWeijiezhang.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYijiezhang.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYituikuan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));

                break;
            case R.id.tv_turnover_order_shaixuan_weixin:
                shoukuanSelect = "微信";
                tvTurnoverOrderShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverOrderShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurntvoverOrderShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_order_shaixuan_zhifubao:
                shoukuanSelect = "支付宝";
                tvTurnoverOrderShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverOrderShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurntvoverOrderShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_order_shaixuan_xianjin:
                shoukuanSelect = "现金";
                tvTurnoverOrderShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurnoverOrderShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurntvoverOrderShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_order_shaixuan_yinhangka:
                shoukuanSelect = "银行卡";
                tvTurnoverOrderShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));
                tvTurntvoverOrderShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                break;
            case R.id.tv_turnover_order_shaixuan_erweimazhuopai:
                shoukuanSelect = "二维码桌卡";
                tvTurnoverOrderShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurntvoverOrderShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_seleced));

                break;

            case R.id.ll_shaixuan_chongzhi:
                dingdanSelect = "";
                zhifuSelect = "";
                shoukuanSelect = "";
                tvTurnoverOrderShaixuanZhuotai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanKaidan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanShoukuan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvtvTurnoverOrderShaixuanWeijiezhang.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYijiezhang.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYituikuan.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanWeixin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanZhifubao.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanXianjin.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurnoverOrderShaixuanYinhangka.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));
                tvTurntvoverOrderShaixuanErweimazhuopai.setBackground(this.getResources().getDrawable(R.drawable.shaixuan_nomal));

                //重置时间
                tvTurnoverOrderShaixuanStarttime.setText(now);
                tvTurnoverOrderShaixuanEndtime.setText(now);
                break;


            //时间筛选
            case R.id.tv_turnover_order_shaixuan_starttime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerStart.show(tvTurnoverOrderShaixuanStarttime.getText().toString());
                break;
            case R.id.tv_turnover_order_shaixuan_endtime:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePickerEnd.show(tvTurnoverOrderShaixuanEndtime.getText().toString());
                break;
            case R.id.bt_turnover_order_shaixuan_shaixuan:
                //判断起始时间不得大于结束时间
                if (tvTurnoverOrderShaixuanStarttime.getText().toString().compareTo(tvTurnoverOrderShaixuanEndtime.getText().toString()) > 0) {
                    ToastUtil.showToast("起始时间不得大于结束时间");
                } else {
                    ToastUtil.showToast("asdasdasd");
                    //上个界面应该 startActivityForResult  这里返回数据给上个fragment  重新加载筛选后的数据 TODO
                    Intent reIntent = new Intent();
                    reIntent.putExtra("","");
                    setResult(RESULT_OK,reIntent);
                    finishActivity();
                }
                break;

        }
    }

    //=====================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
