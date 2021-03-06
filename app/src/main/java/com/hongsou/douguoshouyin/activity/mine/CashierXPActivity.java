package com.hongsou.douguoshouyin.activity.mine;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//TODO 打印开关 次数 初始化 设置 保存
public class CashierXPActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.tv_mine_cashier_xp_shoukuan_icon)
    TextView tvMineCashierXpShoukuanIcon;
    @BindView(R.id.et_mine_cashier_xp_shoukuan_count)
    EditText etMineCashierXpShoukuanCount;
    @BindView(R.id.tv_mine_cashier_xp_dingdan_icon)
    TextView tvMineCashierXpDingdanIcon;
    @BindView(R.id.et_mine_cashier_xp_dingdan_count)
    EditText etMineCashierXpDingdanCount;
    @BindView(R.id.tv_mine_cashier_xp_tuikuan_icon)
    TextView tvMineCashierXpTuikuanIcon;
    @BindView(R.id.et_mine_cashier_xp_tuikuan_count)
    EditText etMineCashierXpTuikuanCount;
    @BindView(R.id.tv_mine_cashier_xp_jiaoban_icon)
    TextView tvMineCashierXpJiaobanIcon;
    @BindView(R.id.et_mine_cashier_xp_jiaoban_count)
    EditText etMineCashierXpJiaobanCount;
    @BindView(R.id.tv_mine_cashier_xp_houchu_icon)
    TextView tvMineCashierXpHouchuIcon;
    @BindView(R.id.et_mine_cashier_xp_houchu_count)
    EditText etMineCashierXpHouchuCount;
    @BindView(R.id.tv_titlebar_finish_back)
    TextView tvTitlebarFinishBack;

    Dialog dialog;
    @BindView(R.id.tv_titlebar_tongbu_icon)
    TextView tvTitlebarTongbuIcon;
    @BindView(R.id.ll_titlebar_tongbu)
    LinearLayout llTitlebarTongbu;
    @BindView(R.id.fl_titlebar)
    FrameLayout flTitlebar;
    //flag 判断用户点击保存了么
    private boolean flag = false;

    //先行记录初始打印小票状态  如果用户保存则将状态存储
    private boolean flagShoukuan;
    private boolean flagDingdan;
    private boolean flagTuikuan;
    private boolean flagJiaoban;
    private boolean flagHouchu;


    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_cashier_xp;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("小票打印");
    }


    @Override
    public void initView() {
        tvTitlebarRight.setVisibility(View.VISIBLE);
        initKG();
        initFS();

    }

    /**
     * 初始化开关状态
     */
    private void initKG() {
        //将存储的状态回显至页面
        if (Global.getSpUserUtil().getPayPrintSwitch()) {
            flagShoukuan = true;
            tvMineCashierXpShoukuanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
        } else {
            flagShoukuan = false;
            tvMineCashierXpShoukuanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
        }
        if (Global.getSpUserUtil().getOrderPrintSwitch()) {
            flagDingdan = true;
            tvMineCashierXpDingdanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
        } else {
            flagDingdan = false;
            tvMineCashierXpDingdanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
        }
        if (Global.getSpUserUtil().getRefundPrintSwitch()) {
            flagTuikuan = true;
            tvMineCashierXpTuikuanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
        } else {
            flagTuikuan = false;
            tvMineCashierXpTuikuanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
        }
        if (Global.getSpUserUtil().getHandoverPrintSwitch()) {
            flagJiaoban = true;
            tvMineCashierXpJiaobanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
        } else {
            flagJiaoban = false;
            tvMineCashierXpJiaobanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
        }
        if (Global.getSpUserUtil().getKitchenPrintSwitch()) {
            flagHouchu = true;
            tvMineCashierXpHouchuIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
        } else {
            flagHouchu = false;
            tvMineCashierXpHouchuIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
        }
    }

    /**
     * 初始化份数
     */
    private void initFS() {
        etMineCashierXpShoukuanCount.setText(Global.getSpUserUtil().getPayPrintCount() + "");
        etMineCashierXpDingdanCount.setText(Global.getSpUserUtil().getOrderPrintCount() + "");
        etMineCashierXpTuikuanCount.setText(Global.getSpUserUtil().getRefundPrintCount() + "");
        etMineCashierXpJiaobanCount.setText(Global.getSpUserUtil().getHandoverPrintCount() + "");
        etMineCashierXpHouchuCount.setText(Global.getSpUserUtil().getKitchenPrintCount() + "");

    }

    @Override
    public void initData() {

    }

    @OnClick({ R.id.tv_titlebar_right, R.id.tv_mine_cashier_xp_shoukuan_icon, R.id.tv_mine_cashier_xp_dingdan_icon, R.id.tv_mine_cashier_xp_tuikuan_icon, R.id.tv_mine_cashier_xp_jiaoban_icon, R.id.tv_mine_cashier_xp_houchu_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_mine_cashier_xp_shoukuan_icon:
                if (flagShoukuan) {
                    flagShoukuan = false;
                    tvMineCashierXpShoukuanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));

                } else {

                    tvMineCashierXpShoukuanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                    flagShoukuan = true;
                }
                break;
            case R.id.tv_mine_cashier_xp_dingdan_icon:
                if (flagDingdan) {
                    flagDingdan = false;
                    tvMineCashierXpDingdanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
                } else {

                    flagDingdan = true;
                    tvMineCashierXpDingdanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                }
                break;
            case R.id.tv_mine_cashier_xp_tuikuan_icon:
                if (flagTuikuan) {
                    flagTuikuan = false;
                    tvMineCashierXpTuikuanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
                } else {

                    flagTuikuan = true;
                    tvMineCashierXpTuikuanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                }
                break;
            case R.id.tv_mine_cashier_xp_jiaoban_icon:
                if (flagJiaoban) {
                    flagJiaoban = false;
                    tvMineCashierXpJiaobanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
                } else {

                    flagJiaoban = true;
                    tvMineCashierXpJiaobanIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                }
                break;
            case R.id.tv_mine_cashier_xp_houchu_icon:
                if (flagHouchu) {
                    flagHouchu = false;
                    tvMineCashierXpHouchuIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
                } else {
                    flagHouchu = true;
                    tvMineCashierXpHouchuIcon.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                }
                break;
            case R.id.tv_titlebar_right:
                //TODO 保存点击事件
                flag = true;
                save();
                break;
            default:
                break;
        }
    }

    /**
     * 保存
     */
    private void save() {
        //保存开关状态
        Global.getSpUserUtil().setPayPrintSwitch(flagShoukuan);
        Global.getSpUserUtil().setOrderPrintSwitch(flagDingdan);
        Global.getSpUserUtil().setRefundPrintSwitch(flagTuikuan);
        Global.getSpUserUtil().setHandoverPrintSwitch(flagJiaoban);
        Global.getSpUserUtil().setKitchenPrintSwitch(flagHouchu);
        //保存份数 如果为空则默认是1
        if (etMineCashierXpShoukuanCount.getText().toString().equals("")) {
            Global.getSpUserUtil().setPayPrintCount(1);
        } else {
            Global.getSpUserUtil().setPayPrintCount(Integer.valueOf(etMineCashierXpShoukuanCount.getText().toString()));
        }
        if (etMineCashierXpDingdanCount.getText().toString().equals("")) {
            Global.getSpUserUtil().setOrderPrintCount(1);
        } else {
            Global.getSpUserUtil().setOrderPrintCount(Integer.valueOf(etMineCashierXpDingdanCount.getText().toString()));
        }
        if (etMineCashierXpTuikuanCount.getText().toString().equals("")) {
            Global.getSpUserUtil().setRefundPrintCount(1);
        } else {
            Global.getSpUserUtil().setRefundPrintCount(Integer.valueOf(etMineCashierXpTuikuanCount.getText().toString()));
        }
        if (etMineCashierXpJiaobanCount.getText().toString().equals("")) {
            Global.getSpUserUtil().setHandoverPrintCount(1);
        } else {
            Global.getSpUserUtil().setHandoverPrintCount(Integer.valueOf(etMineCashierXpJiaobanCount.getText().toString()));
        }
        if (etMineCashierXpHouchuCount.getText().toString().equals("")) {
            Global.getSpUserUtil().setKitchenPrintCount(1);
        } else {
            Global.getSpUserUtil().setKitchenPrintCount(Integer.valueOf(etMineCashierXpHouchuCount.getText().toString()));
        }
        if (flag){
            ToastUtil.showToast("保存成功");
        }
    }

    /**
     * 弹框询问用户是否保存
     */
    private void showBaocunDialog() {
        dialog = new Dialog(this, R.style.CommonDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.module_dialog_text, null);

        TextView tv_dialog_text_content = view.findViewById(R.id.tv_dialog_text_content);
        TextView tv_logout_dialog_cancle = view.findViewById(R.id.tv_logout_dialog_cancle);
        TextView tv_logout_dialog_yes = view.findViewById(R.id.tv_logout_dialog_yes);
        tv_dialog_text_content.setText("您还没有保存要保存吗？");
        tv_logout_dialog_cancle.setText("不保存");
        tv_logout_dialog_yes.setText("保存");

        tv_logout_dialog_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finishActivity();
            }
        });
        tv_logout_dialog_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                dialog.dismiss();
                finishActivity();
            }
        });

        Display display = this.getWindowManager().getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w * 4 / 5, h * 2 / 7);

        dialog.addContentView(view, params);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }


    //-==-================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
