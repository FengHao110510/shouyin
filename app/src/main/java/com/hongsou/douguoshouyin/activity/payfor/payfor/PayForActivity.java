package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.broadcastreceiver.PayOnLineSuccessBean;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.javabean.SaomahaoBean;
import com.hongsou.douguoshouyin.tool.DateUtils;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 支付主页面
 */
public class PayForActivity extends BaseActivity {


    @BindView(R.id.tv_payfor_payfor_xiaofeijiine_icon)
    TextView tvPayforPayforXiaofeijiineIcon;
    @BindView(R.id.tv_payfor_payfor_xuanzeyouhui_icon)
    TextView tvPayforPayforXuanzeyouhuiIcon;
    @BindView(R.id.tv_payfor_payfor_yingshoujine)
    TextView tvPayforPayforYingshoujine;
    @BindView(R.id.rl_payfor_payfor_xuanzeyouhui)
    RelativeLayout rlPayforPayforXuanzeyouhui;
    @BindView(R.id.tv_payfor_payfor_shezhi)
    TextView tvPayforPayforShezhi;
    @BindView(R.id.tv_payfor_payfor_xiaofeijine)
    TextView tvPayforPayforXiaofeijine;
    @BindView(R.id.tv_payfor_payfor_jiantou)
    TextView tvPayforPayforJiantou;
    @BindView(R.id.bt_payfor_payfor_btn1)
    Button btPayforPayforBtn1;
    @BindView(R.id.bt_payfor_payfor_btn2)
    Button btPayforPayforBtn2;
    @BindView(R.id.bt_payfor_payfor_btn3)
    Button btPayforPayforBtn3;
    @BindView(R.id.bt_payfor_payfor_btn_delet)
    Button btPayforPayforBtnDelet;
    @BindView(R.id.bt_payfor_payfor_btn4)
    Button btPayforPayforBtn4;
    @BindView(R.id.bt_payfor_payfor_btn5)
    Button btPayforPayforBtn5;
    @BindView(R.id.bt_payfor_payfor_btn6)
    Button btPayforPayforBtn6;
    @BindView(R.id.bt_payfor_payfor_btn_cancle)
    Button btPayforPayforBtnCancle;
    @BindView(R.id.bt_payfor_payfor_btn7)
    Button btPayforPayforBtn7;
    @BindView(R.id.bt_payfor_payfor_btn8)
    Button btPayforPayforBtn8;
    @BindView(R.id.bt_payfor_payfor_btn9)
    Button btPayforPayforBtn9;
    @BindView(R.id.bt_payfor_payfor_btn_shang)
    Button btPayforPayforBtnShang;
    @BindView(R.id.bt_payfor_payfor_btn_dian)
    Button btPayforPayforBtnDian;
    @BindView(R.id.bt_payfor_payfor_btn0)
    Button btPayforPayforBtn0;
    @BindView(R.id.bt_payfor_payfor_btn00)
    Button btPayforPayforBtn00;
    @BindView(R.id.bt_payfor_payfor_btn_xia)
    Button btPayforPayforBtnXia;

    private String flag;//判断是折扣优惠还是现金优惠 0折扣 1优惠
    private float content;//折扣或现金的数据
    private float xiaofeijine;
    private float yingshoujine;//应收金额
    private PopupWindow mPopupWindow;
    @BindView(R.id.ll_payfor_payfor)
    LinearLayout llPayforPayfor;

    private StringBuffer numRecordStr = new StringBuffer();//消费金额

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_payfor;
    }

    @Override
    protected void init() {

        initView();
        initData();
        initBack();
        initTitle("收款");

    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{btPayforPayforBtnCancle, tvPayforPayforXiaofeijiineIcon, tvPayforPayforXuanzeyouhuiIcon});
        //设置消费金额 与应收金额 联动
        tvPayforPayforXiaofeijine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!tvPayforPayforXiaofeijine.getText().toString().equals("")) {
                    xiaofeijine = Float.valueOf(tvPayforPayforXiaofeijine.getText().toString());
                } else {
                    xiaofeijine = 0;
                }

                if (tvPayforPayforShezhi.getText().toString().contains("去设置")) {
                    //没有选择折扣
                    tvPayforPayforYingshoujine.setText(xiaofeijine + "");
                } else {
                    if ("0".equals(flag)) {
                        //获得折后的应收金额
                        yingshoujine = xiaofeijine * content / 10;
                        setyingshoujine(yingshoujine + "");

                    } else {
                        //获得减现金后的应收金额
                        yingshoujine = xiaofeijine - content;
                        if (yingshoujine < 0) {
//                            ToastUtil.showToast("优惠金额不得大于消费金额");
                            tvPayforPayforYingshoujine.setText("0.00");

                        } else {
                            setyingshoujine(yingshoujine + "");

                        }
                    }
                }
            }
        });

        tvPayforPayforYingshoujine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Float.valueOf(tvPayforPayforYingshoujine.getText().toString()) > 0) {
                    btPayforPayforBtnShang.setBackgroundColor(getResources().getColor(R.color.color_base_yellow));
                    btPayforPayforBtnXia.setBackgroundColor(getResources().getColor(R.color.color_base_yellow));
                } else {
                    btPayforPayforBtnShang.setBackgroundColor(getResources().getColor(R.color.gray));
                    btPayforPayforBtnXia.setBackgroundColor(getResources().getColor(R.color.gray));
                }
            }
        });
    }

    @Override
    public void initData() {

    }


    //从优惠页面接受返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case IntentIntegrator.REQUEST_CODE:
                //扫码成功的 回调
                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (result != null) {
                    if (result.getContents() == null) {
                        Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();

                    } else {
                        //TODO 成功之后走接口
                        toPay(result.getContents());
                    }
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    if (data.getBooleanExtra("zkorxj", true)) {
                        tvPayforPayforShezhi.setText(data.getStringExtra("content") + "折");
                        flag = "0";
                        content = Float.valueOf(data.getStringExtra("content"));
                        //获得折后的应收金额
                        yingshoujine = xiaofeijine * content / 10;
                        setyingshoujine(yingshoujine + "");

                    } else {
                        flag = "1";
                        content = Float.valueOf(data.getStringExtra("content"));
                        //获得减现金后的应收金额
                        yingshoujine = xiaofeijine - content;
                        tvPayforPayforShezhi.setText("- " + data.getStringExtra("content"));

                        if (yingshoujine < 0) {
//                            ToastUtil.showToast("优惠金额不得大于消费金额");
                            tvPayforPayforYingshoujine.setText("0.00");

                        } else {
                            setyingshoujine(yingshoujine + "");

                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    //设置应收金额
    private void setyingshoujine(String s) {
        // 判断小数点后只能输入两位
        if (s.contains(".")) {
            if (s.length() - 1 - s.indexOf(".") > 2) {
                s = (String) s.subSequence(0, s.indexOf(".") + 3);
            }
        }
        tvPayforPayforYingshoujine.setText(s);
    }

    @OnClick({R.id.rl_payfor_payfor_xuanzeyouhui, R.id.bt_payfor_payfor_btn1, R.id.bt_payfor_payfor_btn2, R.id.bt_payfor_payfor_btn3, R.id.bt_payfor_payfor_btn_delet, R.id.bt_payfor_payfor_btn4, R.id.bt_payfor_payfor_btn5, R.id.bt_payfor_payfor_btn6, R.id.bt_payfor_payfor_btn_cancle, R.id.bt_payfor_payfor_btn7, R.id.bt_payfor_payfor_btn8, R.id.bt_payfor_payfor_btn9, R.id.bt_payfor_payfor_btn_shang, R.id.bt_payfor_payfor_btn_dian, R.id.bt_payfor_payfor_btn0, R.id.bt_payfor_payfor_btn00, R.id.bt_payfor_payfor_btn_xia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_payfor_payfor_xuanzeyouhui:
                //跳转到优惠页面
                Intent disCountIntent = new Intent(this, DiscountActivity.class);
                startActivityForResult(disCountIntent, 1);
                break;
            case R.id.bt_payfor_payfor_btn1:
                numRecordStr.append("1");
                break;
            case R.id.bt_payfor_payfor_btn2:
                numRecordStr.append("2");
                break;
            case R.id.bt_payfor_payfor_btn3:
                numRecordStr.append("3");
                break;
            case R.id.bt_payfor_payfor_btn4:
                numRecordStr.append("4");
                break;
            case R.id.bt_payfor_payfor_btn5:
                numRecordStr.append("5");
                break;
            case R.id.bt_payfor_payfor_btn6:
                numRecordStr.append("6");
                break;
            case R.id.bt_payfor_payfor_btn7:
                numRecordStr.append("7");
                break;
            case R.id.bt_payfor_payfor_btn8:
                numRecordStr.append("8");
                break;
            case R.id.bt_payfor_payfor_btn9:
                numRecordStr.append("9");
                break;
            case R.id.bt_payfor_payfor_btn_dian:
                if (!TextUtils.isEmpty(numRecordStr.toString()) && !numRecordStr.toString().contains(".")) {
                    numRecordStr.append(".");
                }
                break;
            case R.id.bt_payfor_payfor_btn0:
                numRecordStr.append("0");
                break;
            case R.id.bt_payfor_payfor_btn00:
                if (numRecordStr.toString().contains(".")) {
                    return;
                } else if (numRecordStr.toString().startsWith("0")) {
                    return;
                } else {
                    if (numRecordStr.toString().length() > 0 && numRecordStr.toString().length() < 4) {
                        numRecordStr.append("00");
                    }
                }
                break;
            //上下都是收银  表现为一个按钮
            case R.id.bt_payfor_payfor_btn_shang:
                payfor();
                break;
            case R.id.bt_payfor_payfor_btn_xia:
                payfor();
                break;
            //清空
            case R.id.bt_payfor_payfor_btn_delet:
                numRecordStr.delete(0, numRecordStr.length());
                break;
            case R.id.bt_payfor_payfor_btn_cancle:
                if (numRecordStr.length() != 0) {
                    numRecordStr.deleteCharAt(numRecordStr.length() - 1);
                }
                break;
            default:
                break;
        }
        setNum(numRecordStr.toString());
    }

    /**
     * 对StringBuffer重设
     */
    private void reSetStringBuffer(String moneyStr) {
        numRecordStr.delete(0, numRecordStr.length());
        numRecordStr.append(moneyStr);
    }

    //设置消费金额
    private void setNum(String s) {
        if (".".equals(s)) {
            s = "";
        }
        // 限制最多能输入6位整数
        if (s.toString().contains(".")) {
            if (s.toString().indexOf(".") > 6) {
                s = s.toString().subSequence(0, 6) + s.toString().substring(s.toString().indexOf("."));
                tvPayforPayforXiaofeijine.setText(s);
                reSetStringBuffer(tvPayforPayforXiaofeijine.getText().toString());
            }
        } else {
            if (s.toString().length() > 6) {
                s = (String) s.toString().subSequence(0, 6);
                tvPayforPayforXiaofeijine.setText(s);
                reSetStringBuffer(tvPayforPayforXiaofeijine.getText().toString());
            }
        }
        // 判断小数点后只能输入两位
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = (String) s.toString().subSequence(0,
                        s.toString().indexOf(".") + 3);
                tvPayforPayforXiaofeijine.setText(s);
                reSetStringBuffer(tvPayforPayforXiaofeijine.getText().toString());
            }
        }
        //如果第一个数字为0，第二个不为点，就不允许输入
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                tvPayforPayforXiaofeijine.setText(s.subSequence(0, 1));
                reSetStringBuffer(tvPayforPayforXiaofeijine.getText().toString());
                return;
            }
        }
        tvPayforPayforXiaofeijine.setText(s);
    }

    //判断是否输入金额
    private void payfor() {
        if (Float.valueOf(tvPayforPayforYingshoujine.getText().toString()) > 0) {
            Global.getSpGlobalUtil().setYingshouJE(tvPayforPayforYingshoujine.getText().toString());
            if (flag!=null){
                    Global.getSpGlobalUtil().setZhekou(flag);
                if ("0".equals(flag)){
                    Global.getSpGlobalUtil().setZheKouJE(content*10+"");
                }else {
                    Global.getSpGlobalUtil().setZheKouJE(content+"");
                }
            }
            showPopWindow();

        } else {
            if (!TextUtils.isEmpty(tvPayforPayforXiaofeijine.getText().toString())) {
                if (Float.valueOf(tvPayforPayforXiaofeijine.getText().toString()) <= 0) {
                    ToastUtil.showToast("请输入金额");
                }
            } else {
                ToastUtil.showToast("请输入金额");
            }

        }
    }

    //弹框扫码或一码付支付
    private void showPopWindow() {


        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.module_pop_pay, null);

        TextView tvPayforPopSaoIcon = v.findViewById(R.id.tv_payfor_pop_sao_icon);
        TextView tvPayforPopErweiIcon = v.findViewById(R.id.tv_payfor_pop_erwei_icon);
        TextView tvPayforPopXianjinIcon = v.findViewById(R.id.tv_payfor_pop_xianjin_icon);

        setIconFont(new TextView[]{tvPayforPopErweiIcon, tvPayforPopSaoIcon, tvPayforPopXianjinIcon});
        LinearLayout llPayforPopSao = v.findViewById(R.id.ll_payfor_pop_sao);
        LinearLayout llPayforPopErwei = v.findViewById(R.id.ll_payfor_pop_erwei);
        LinearLayout ll_payfor_pop_xianjin = v.findViewById(R.id.ll_payfor_pop_xianjin);
        llPayforPopSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
//                new IntentIntegrator(PayForActivity.this).initiateScan();
                new IntentIntegrator(PayForActivity.this).
                        setCaptureActivity(ScanQRCodeActivity.class)
                        .setPrompt("")// 设置提示语
                        .setCameraId(0)// 选择摄像头,可使用前置或者后置
                        .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                        .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                        .initiateScan();// 初始化扫码
                ToastUtil.showToast("扫一扫");
            }
        });
        llPayforPopErwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                Intent erweimaIntent = new Intent(PayForActivity.this, QRCode.class);
                startActivity(erweimaIntent);
                ToastUtil.showToast("二维码");
            }
        });
        ll_payfor_pop_xianjin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                payXJ();

                ToastUtil.showToast("现金");
            }
        });
        //h popwindow的高度
        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        mPopupWindow = new PopupWindow(v, LinearLayout.LayoutParams.MATCH_PARENT, h / 5);
        // 设置SelectPicPopupWindow弹出窗体动画效果，设置动画，一会会讲解
        mPopupWindow.setAnimationStyle(R.style.et_style);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.black));
        // 设置pop弹出窗体的背景
        mPopupWindow.setBackgroundDrawable(dw);
        backgroundAlpha(this, 0.5f);//0.0-1.0
        mPopupWindow.update();
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(llPayforPayfor, Gravity.BOTTOM, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(PayForActivity.this, 1f);//0.0-1.0
            }
        });

    }

    /**
     * 现金支付
     */
    private void payXJ() {
        showLoadingDialog();
        //走提交订单接口
        HttpFactory.post().url(ApiConfig.INSERT_ORDER).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                ToastUtil.showToast("提交订单失败");
                //成功后跳转支付成功页面 TODO
                Intent successIntent = new Intent(PayForActivity.this, PaymentDetailActivity.class);
                startActivity(successIntent);

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                //成功后跳转支付成功页面 TODO
                Intent successIntent = new Intent(PayForActivity.this, PaymentDetailActivity.class);
                startActivity(successIntent);
                //失败后跳转支付失败页面 TODO
                Intent failIntent = new Intent(PayForActivity.this, FailActivity.class);
                startActivity(failIntent);
                //失败后跳转支付失败页面 TODO
                Intent skzIntent = new Intent(PayForActivity.this, ShoukuanzhongActivity.class);
                startActivity(skzIntent);
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


    //跳转支付
    private void toPay(String contents) {
        HttpFactory.post().url(ApiConfig.TABBY_PAY)
                .addParams("equipmentNumber", Global.getSpGlobalUtil().getCode())
                .addParams("equipmentType", "3")
                .addParams("uniquelyCode", Global.getSpGlobalUtil().getAliCode())
                .addParams("uniCodeStandby", Global.getSpGlobalUtil().getWecharCode())
                .addParams("totalFee", Global.getSpGlobalUtil().getYingshouJE())
                .addParams("authCode", contents)
                .addParams("batch", "s"+ DateUtils.getNowDateLong() + (int) (Math.random() * 1000))
                .addParams("storeId", getShopNumber())
                .addParams("operatorId", getClerkNumber())
                .addParams("discountType", Global.getSpGlobalUtil().getZhekou())
                .addParams("discountMoney", Global.getSpGlobalUtil().getZheKouJE())
                .addParams("masterSecret", Constant.MASTER_SECRET)
                .addParams("appKey", Constant.APP_KEY)
                .addParams("address",ApiConfig.BASE_URL+"/pay/payCallback").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.showError();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, "onResponse: "+response.toString() );
            }
        
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Global.getSpGlobalUtil().setZheKouJE("");
        Global.getSpGlobalUtil().setZhekou("");
        Global.getSpGlobalUtil().setYingshouJE("");
    }

    //获取event发过来的额二维码号
    @Subscribe
    public void onEventMainThread(SaomahaoBean event) {

        String msg = "onEventMainThread收到了消息：" + event.getSaomahao();
        Log.d("harvic", msg);
        //TODO 走成功接口
        toPay(msg);
    }
    @Subscribe
    public void onEventMainThread(PayOnLineSuccessBean payOnLineSuccessBean){
        Intent successIntent = new Intent(this,PaymentDetailActivity.class);
        successIntent.putExtra("payOnLineSuccessBean",(Serializable) payOnLineSuccessBean);
        startActivity(successIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //
    //=====================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }


}
