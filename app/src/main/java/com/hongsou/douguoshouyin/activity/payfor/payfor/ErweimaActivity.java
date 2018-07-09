package com.hongsou.douguoshouyin.activity.payfor.payfor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.Apiconfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.javabean.SaomahaoBean;
import com.hongsou.douguoshouyin.tool.BitmapUtil;
import com.hongsou.douguoshouyin.tool.Global;
import okhttp3.Call;

/**
 * 二维码收款页面
 */
public class ErweimaActivity extends BaseActivity {


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
        HttpFactory.post().url(Apiconfig.yimafu).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();

            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                Bitmap bitmap = BitmapUtil.create2DCoderBitmap("https://blog.csdn.net/w815878564/article/details/51115562", 500, 500);
                ivPayforErweimaErweima.setImageBitmap(bitmap);
            }
        });
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
        }
    }

    @OnClick(R.id.tv_payfor_erweima_saoyisao_icon)
    public void onViewClicked() {
        //转到扫一扫界面
        new IntentIntegrator(ErweimaActivity.this).
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
