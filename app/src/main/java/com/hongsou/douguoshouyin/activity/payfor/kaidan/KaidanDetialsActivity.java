package com.hongsou.douguoshouyin.activity.payfor.kaidan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.payfor.payfor.ErweimaActivity;
import com.hongsou.douguoshouyin.activity.payfor.payfor.ScanQRCodeActivity;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.tool.ScreenUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;

/**
 * 开单详情页 选择商品后的订单详情
 */
public class KaidanDetialsActivity extends BaseActivity {


    PopupWindow mPopupWindow;
    @BindView(R.id.ll_payfor_kaidan_detials)
    LinearLayout llPayforKaidanDetials;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_kaidan_detials;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("订单详情");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.tv_payfor_kaidan_detials_shoukuan_bt)
    public void onViewClicked() {
        //弹出收款框
        showPopWindow();
    }

    //弹框扫码或一码付支付
    private void showPopWindow() {


        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.module_pop_pay, null);

        TextView tv_payfor_pop_sao_icon = v.findViewById(R.id.tv_payfor_pop_sao_icon);
        TextView tv_payfor_pop_erwei_icon = v.findViewById(R.id.tv_payfor_pop_erwei_icon);
        TextView tv_payfor_pop_xianjin_icon = v.findViewById(R.id.tv_payfor_pop_xianjin_icon);

        setIconFont(new TextView[]{tv_payfor_pop_erwei_icon, tv_payfor_pop_sao_icon, tv_payfor_pop_xianjin_icon});
        LinearLayout ll_payfor_pop_sao = v.findViewById(R.id.ll_payfor_pop_sao);
        LinearLayout ll_payfor_pop_erwei = v.findViewById(R.id.ll_payfor_pop_erwei);
        LinearLayout ll_payfor_pop_xianjin = v.findViewById(R.id.ll_payfor_pop_xianjin);
        ll_payfor_pop_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
//                new IntentIntegrator(PayForActivity.this).initiateScan();
                new IntentIntegrator(KaidanDetialsActivity.this).
                        setCaptureActivity(ScanQRCodeActivity.class)
                        .setPrompt("")// 设置提示语
                        .setCameraId(0)// 选择摄像头,可使用前置或者后置
                        .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                        .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片

                        .initiateScan();// 初始化扫码


                ToastUtil.showToast("扫一扫");
            }
        });
        ll_payfor_pop_erwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                Intent erweimaIntent = new Intent(KaidanDetialsActivity.this, ErweimaActivity.class);
                startActivity(erweimaIntent);


                ToastUtil.showToast("二维码");
            }
        });
        ll_payfor_pop_xianjin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();

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
        mPopupWindow.showAtLocation(llPayforKaidanDetials, Gravity.BOTTOM, 0, ScreenUtil.dip2px(this,50));

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(KaidanDetialsActivity.this, 1f);//0.0-1.0
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

    //=============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
