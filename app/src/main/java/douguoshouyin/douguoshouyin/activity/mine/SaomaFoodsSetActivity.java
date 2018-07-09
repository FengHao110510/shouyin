package douguoshouyin.douguoshouyin.activity.mine;

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
import douguoshouyin.douguoshouyin.R;
import douguoshouyin.douguoshouyin.base.BaseActivity;
import douguoshouyin.douguoshouyin.tool.Global;

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

    @Override
    public int intiLayout() {
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
        tvMineSaomafoodsetSaomamoshi.setText(Global.getSpGlobalUtil().getSaomamoshi());

        //初始化扫码点餐按钮
        if (Global.getSpGlobalUtil().getSaomaDiancan()) {
            tvMineSaomafoodsetSaomafoodBt.setBackground(this.getResources().getDrawable(R.drawable.imgbt_selector));

        } else {
            tvMineSaomafoodsetSaomafoodBt.setBackground(this.getResources().getDrawable(R.drawable.imgbt_nomal));
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_mine_saomafoodset_saomafood_bt, R.id.rl_mine_saomafoodset_saomapay, R.id.rl_mine_saomafoodset_bucanyu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_saomafoodset_saomafood_bt:
                if (Global.getSpGlobalUtil().getSaomaDiancan()) {
                    tvMineSaomafoodsetSaomafoodBt.setBackground(this.getResources().getDrawable(R.drawable.imgbt_nomal));
                    Global.getSpGlobalUtil().setSaomaDiancan(false);
                } else {
                    tvMineSaomafoodsetSaomafoodBt.setBackground(this.getResources().getDrawable(R.drawable.imgbt_selector));
                    Global.getSpGlobalUtil().setSaomaDiancan(true);

                }
                break;
            case R.id.rl_mine_saomafoodset_saomapay:
                //弹框选择点餐模式
                showPopWindow();

                break;
            case R.id.rl_mine_saomafoodset_bucanyu:
                //跳转不参与扫码商品的页面
                Intent noIntent = new Intent(this,NosaomaFoodsActivity.class);
                startActivity(noIntent);
                break;
        }
    }

    /**
     * 弹框选择点餐模式
     */
    private void showPopWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_saomadiancan, null);

        RelativeLayout rl_pop_saoma_diancan = view.findViewById(R.id.rl_pop_saoma_diancan);
        RelativeLayout rl_pop_saoma_diancan_jiezhang = view.findViewById(R.id.rl_pop_saoma_diancan_jiezhang);

        TextView tv_pop_saoma_diancan_icon = view.findViewById(R.id.tv_pop_saoma_diancan_icon);
        TextView tv_pop_saoma_diancan_jiezhang_icon = view.findViewById(R.id.tv_pop_saoma_diancan_jiezhang_icon);

        setIconFont(new TextView[]{tv_pop_saoma_diancan_icon, tv_pop_saoma_diancan_jiezhang_icon});

        //扫码模式回显
        if (Global.getSpGlobalUtil().getSaomamoshi().contains("结账")) {
            tv_pop_saoma_diancan_jiezhang_icon.setVisibility(View.VISIBLE);
            tv_pop_saoma_diancan_icon.setVisibility(View.GONE);
        } else {
            tv_pop_saoma_diancan_jiezhang_icon.setVisibility(View.GONE);
            tv_pop_saoma_diancan_icon.setVisibility(View.VISIBLE);
        }


        rl_pop_saoma_diancan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.getSpGlobalUtil().setSaomamoshi("点餐");
                tvMineSaomafoodsetSaomamoshi.setText("点餐");
                mPopupWindow.dismiss();
            }
        });
        rl_pop_saoma_diancan_jiezhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.getSpGlobalUtil().setSaomamoshi("点餐+结账");
                tvMineSaomafoodsetSaomamoshi.setText("点餐+结账");
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
        backgroundAlpha(this, 0.5f);//0.0-1.0
        mPopupWindow.update();
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(llMineSaomafoodset, Gravity.BOTTOM, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(SaomaFoodsSetActivity.this, 1f);//0.0-1.0
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
