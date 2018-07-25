package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hongsou.douguoshouyin.base.Constant;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.tool.DateUtils;
import com.hongsou.douguoshouyin.tool.ScreenUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.javabean.SaomahaoBean;
import com.hongsou.douguoshouyin.tool.BitmapUtil;
import com.hongsou.douguoshouyin.tool.Global;

import okhttp3.Call;

/**
 * 二维码收款页面
 */
public class QRCode extends BaseActivity {


    @BindView(R.id.tv_payfor_erweima_shoukuanjine)
    TextView tvPayforErweimaShoukuanjine;
    @BindView(R.id.tv_payfor_erweima_saoyisao_icon)
    TextView tvPayforErweimaSaoyisaoIcon;
    @BindView(R.id.iv_payfor_erweima_erweima)
    ImageView ivPayforErweimaErweima;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_erweima;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("收款码");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvPayforErweimaSaoyisaoIcon});

        tvPayforErweimaShoukuanjine.setText(Global.getSpGlobalUtil().getYingshouJE());

        showErweima();

    }

    /**
     * 展示二维码 TODO
     */
    private void showErweima() {
        showLoadingDialog();

        HttpFactory.post().url(ApiConfig.PAY_FOR_CODE)
                .addParams("operatorId", getClerkNumber())
                .addParams("storeId", getShopNumber())
                .addParams("equipmentNumber", Global.getSpGlobalUtil().getCode())
                .addParams("equipmentType", "3")
                .addParams("userType", "")
                .addParams("uniquelyCode", Global.getSpGlobalUtil().getAliCode())
                .addParams("uniCodeStandby", Global.getSpGlobalUtil().getWecharCode())
                .addParams("totalFee", tvPayforErweimaShoukuanjine.getText().toString())
                .addParams("batch", "s" + DateUtils.getNowDateLong() + (int) (Math.random() * 1000))
                .addParams("discountType", Global.getSpGlobalUtil().getZhekou())
                .addParams("discountMoney", Global.getSpGlobalUtil().getZheKouJE())
                .addParams("masterSecret", Constant.MASTER_SECRET)
                .addParams("appKey", Constant.APP_KEY)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, "onResponse: " + response+Global.getSpGlobalUtil().getWecharCode());
                dismissLoadingDialog();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String orderStr = (String) jsonObject.get("msg");
                    String payUrl = ApiConfig.QR_PAY + "?outTradeNo=" + orderStr;
                    Display display = getWindowManager().getDefaultDisplay();
                    int w = display.getWidth();
                    int h = display.getHeight();

                    Bitmap bitmap = BitmapUtil.create2DCoderBitmap(payUrl, ScreenUtil.dip2px(QRCode.this,220),
                            ScreenUtil.dip2px(QRCode.this,220));
                    ivPayforErweimaErweima.setImageBitmap(bitmap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
        Global.getSpGlobalUtil().setZheKouJE("");
        Global.getSpGlobalUtil().setZhekou("");
        Global.getSpGlobalUtil().setYingshouJE("");
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (result != null) {
                    if (result.getContents() == null) {
//                        Log.d(getClass().getName(), "Cancelled");
//                        Toast.makeText(this, "扫描结果为空", Toast.LENGTH_LONG).show();
                        finishActivity();
                    } else {
                        //将扫出的二维码号发到payfor页面  走接口  同时关闭本页面
//                        Log.d(getClass().getName(), "Scanned: " + result.getContents());
//                        Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                        EventBus.getDefault().post(new SaomahaoBean(result.getContents()));
                        finishActivity();
                    }
                }
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.tv_payfor_erweima_saoyisao_icon)
    public void onViewClicked() {
        //转到扫一扫界面
        new IntentIntegrator(QRCode.this).
                setCaptureActivity(ScanQRCodeActivity.class)
                .setPrompt("")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片

                .initiateScan();// 初始化扫码

    }

    //====================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
