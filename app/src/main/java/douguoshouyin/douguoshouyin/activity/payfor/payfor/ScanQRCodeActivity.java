package douguoshouyin.douguoshouyin.activity.payfor.payfor;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.tool.Global;

public class ScanQRCodeActivity extends AppCompatActivity {
    @BindView(R.id.bv_barcode)
    DecoratedBarcodeView bv_barcode;
    @BindView(R.id.qr_tv_titlebar_finish_back)
    TextView qrTvTitlebarFinishBack;
    @BindView(R.id.qr_tv_titlebar_title)
    TextView qrTvTitlebarTitle;
    @BindView(R.id.tv_payfor_saoyisao_shoukuanjine)
    TextView tvPayforSaoyisaoShoukuanjine;
    @BindView(R.id.tv_payfor_saoyisao_saoyisao_icon)
    TextView tvPayforSaoyisaoSaoyisaoIcon;
    @BindView(R.id.tv_payfor_saoyisao_saoyisao)
    TextView tvPayforSaoyisaoSaoyisao;
    @BindView(R.id.imageView_sao)
    ImageView imageViewSao;
    @BindView(R.id.imageView_sao2)
    ImageView imageViewSao2;
    @BindView(R.id.imageView_sao3)
    ImageView imageViewSao3;
    @BindView(R.id.rl_payfor_saoyisao_tubiao)
    RelativeLayout rlPayforSaoyisaoTubiao;
    private CaptureManager capture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_payfor_scan_qrcode);
        ButterKnife.bind(this);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "iconfont.ttf");
        qrTvTitlebarFinishBack.setTypeface(typeface);
        tvPayforSaoyisaoSaoyisaoIcon.setTypeface(typeface);
        qrTvTitlebarTitle.setText("扫码收款");
        tvPayforSaoyisaoShoukuanjine.setText(Global.getSpGlobalUtil().getYingshouJE());
        capture = new CaptureManager(this, bv_barcode);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }


    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return bv_barcode.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.qr_tv_titlebar_finish_back, R.id.tv_payfor_saoyisao_saoyisao_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qr_tv_titlebar_finish_back:
                finish();
                break;
            case R.id.tv_payfor_saoyisao_saoyisao_icon:
                Intent erweimaIntent = new Intent(ScanQRCodeActivity.this,ErweimaActivity.class);
                startActivity(erweimaIntent);

                finish();

                break;
        }
    }
}

