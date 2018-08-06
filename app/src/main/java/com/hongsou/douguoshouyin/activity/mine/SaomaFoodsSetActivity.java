package com.hongsou.douguoshouyin.activity.mine;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.JoinSaomaBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

/**
 * 扫码点餐设置页面页面
 */
public class SaomaFoodsSetActivity extends BaseActivity {


    @BindView(R.id.tv_mine_saomafoodset_saomafood_bt)
    TextView tvMineSaomafoodsetSaomafoodBt;
    @BindView(R.id.rl_mine_saomafoodset_saomapay)
    RelativeLayout rlMineSaomafoodsetSaomapay;
    @BindView(R.id.rl_mine_saomafoodset_bucanyu)
    RelativeLayout rlMineSaomafoodsetBucanyu;
    @BindView(R.id.ll_mine_saomafoodset)
    LinearLayout llMineSaomafoodset;
    @BindView(R.id.tv_mine_saomafoodset_saomamoshi)
    TextView tvMineSaomafoodsetSaomamoshi;

    private PopupWindow mPopupWindow;
    //是否开启扫码点餐
    private String scanFood;
    //0点餐  1点餐+结账
    private String scanPay;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_saoma_foods_set;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("扫码点餐设置");
    }

    @Override
    public void initView() {
        //初始化扫码点餐按钮
        setIsCheck();

    }

    @Override
    public void initData() {

    }

    private void setIsCheck() {
        HttpFactory.get().url(ApiConfig.GET_IS_SCAN_FOOD).addParams("shopNumber", getShopNumber()).build()
                .execute(new ResponseCallback<JoinSaomaBean>(this) {
                    @Override
                    public void onResponse(JoinSaomaBean response, int id) {
                        if (response.isSuccess()) {
                            //初始化开关
                            if ("0".equals(response.getData().getScanFood())) {
                                tvMineSaomafoodsetSaomafoodBt.setBackground(SaomaFoodsSetActivity.this.getResources()
                                        .getDrawable(R.drawable.imgbt_selector));
                                scanFood = "0";
                            } else {
                                tvMineSaomafoodsetSaomafoodBt.setBackground(SaomaFoodsSetActivity.this.getResources()
                                        .getDrawable(R.drawable.imgbt_nomal));
                                scanFood = "1";
                            }
                            //初始化扫码模式
                            if ("0".equals(response.getData().getScanPay())) {
                                //0点餐  1点参加结账
                                tvMineSaomafoodsetSaomamoshi.setText("点餐");
                                scanPay = "0";
                            } else {
                                scanPay = "1";
                                tvMineSaomafoodsetSaomamoshi.setText("点餐+结账");
                            }
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }


    @OnClick({R.id.tv_mine_saomafoodset_saomafood_bt, R.id.rl_mine_saomafoodset_saomapay, R.id.rl_mine_saomafoodset_bucanyu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_saomafoodset_saomafood_bt:
                if ("0".equals(scanFood)) {
                    tvMineSaomafoodsetSaomafoodBt.setBackground(SaomaFoodsSetActivity.this.getResources()
                            .getDrawable(R.drawable.imgbt_nomal));
                    scanFood = "1";
                } else {
                    scanFood = "0";
                    tvMineSaomafoodsetSaomafoodBt.setBackground(SaomaFoodsSetActivity.this.getResources()
                            .getDrawable(R.drawable.imgbt_selector));
                }
                updateScan();
                break;
            case R.id.rl_mine_saomafoodset_saomapay:
                //弹框选择点餐模式
                showPopWindow();
                break;
            case R.id.rl_mine_saomafoodset_bucanyu:
                //跳转不参与扫码商品的页面
                Intent noIntent = new Intent(this, NosaomaFoodsActivity.class);
                startActivity(noIntent);
                break;
            default:
                break;
        }
    }

    /**
     * 弹框选择点餐模式
     */
    private void showPopWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_saomadiancan, null);

        RelativeLayout rlPopSaomaDiancan = view.findViewById(R.id.rl_pop_saoma_diancan);
        RelativeLayout rlPopSaomaDiancanJiezhang = view.findViewById(R.id.rl_pop_saoma_diancan_jiezhang);

        TextView tvPopSaomaDiancanIcon = view.findViewById(R.id.tv_pop_saoma_diancan_icon);
        TextView tvPopSaomaDiancanJiezhangIcon = view.findViewById(R.id.tv_pop_saoma_diancan_jiezhang_icon);

        setIconFont(new TextView[]{tvPopSaomaDiancanIcon, tvPopSaomaDiancanJiezhangIcon});

//        //扫码模式回显
        if ("0".equals(scanPay)) {
            tvPopSaomaDiancanJiezhangIcon.setVisibility(View.GONE);
            tvPopSaomaDiancanIcon.setVisibility(View.VISIBLE);
        } else {
            tvPopSaomaDiancanJiezhangIcon.setVisibility(View.VISIBLE);
            tvPopSaomaDiancanIcon.setVisibility(View.GONE);
        }


        rlPopSaomaDiancan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvMineSaomafoodsetSaomamoshi.setText("点餐");
                scanPay = "0";
                updateScan();
                mPopupWindow.dismiss();
            }
        });
        rlPopSaomaDiancanJiezhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvMineSaomafoodsetSaomamoshi.setText("点餐+结账");
                scanPay = "1";
                updateScan();
                mPopupWindow.dismiss();

            }
        });

        //h popwindow的高度
        int h = this.getWindowManager().getDefaultDisplay().getHeight();
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, h / 3);
        // 设置SelectPicPopupWindow弹出窗体动画效果，设置动画，一会会讲解
        mPopupWindow.setAnimationStyle(R.style.et_style);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.black));
        // 设置pop弹出窗体的背景
        mPopupWindow.setBackgroundDrawable(dw);
        //0.0-1.0
        backgroundAlpha(this, 0.5f);
        mPopupWindow.update();
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(llMineSaomafoodset, Gravity.BOTTOM, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //0.0-1.0
                backgroundAlpha(SaomaFoodsSetActivity.this, 1f);
            }
        });

    }

    /**
     * @author fenghao
     * @date 2018/8/6 0006 上午 10:52
     * @desc 修改接口
     */
    private void updateScan() {
        HttpFactory.post().url(ApiConfig.UPDATE_IS_SCAN_FOOD)
                .addParams("shopNumber", getShopNumber())
                .addParams("scanFood", scanFood)
                .addParams("scanPay", scanPay)
                .build().execute(new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("修改成功");
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
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

    //=============================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
