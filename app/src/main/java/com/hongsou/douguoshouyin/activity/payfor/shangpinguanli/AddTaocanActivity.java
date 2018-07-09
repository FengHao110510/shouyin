package com.hongsou.douguoshouyin.activity.payfor.shangpinguanli;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.mine.AddNosaomaActivity;
import com.hongsou.douguoshouyin.base.BaseActivity;

/**
 * 添加套餐页面
 */
public class AddTaocanActivity extends BaseActivity {

    @BindView(R.id.tv_payfor_addtaocan_icon)
    TextView tvPayforAddtaocanIcon;
    @BindView(R.id.et_payfor_addtaocan_mingcheng)
    EditText etPayforAddtaocanMingcheng;
    @BindView(R.id.et_payfor_addtaocan_jine)
    EditText etPayforAddtaocanJine;
    @BindView(R.id.rl_payfor_addtaocan_fenlei)
    RelativeLayout rlPayforAddtaocanFenlei;

    @BindView(R.id.et_payfor_addtaocan_danwei)
    EditText etPayforAddtaocanDanwei;
    @BindView(R.id.tv_payfor_addtaocan_shifouzaishou)
    TextView tvPayforAddtaocanShifouzaishou;
    @BindView(R.id.tv_payfor_addtaocan_fenlei)
    TextView tvPayforAddtaocanFenlei;
    @BindView(R.id.tv_payfor_addtaocan_leixing)
    TextView tvPayforAddtaocanLeixing;
    @BindView(R.id.rl_payfor_addtaocan_taocanleixing)
    RelativeLayout rlPayforAddtaocanTaocanleixing;
    @BindView(R.id.rv_payfor_addtaocan_zuhe_fenzu)
    RecyclerView rvPayforAddtaocanZuheFenzu;
    @BindView(R.id.tv_payfor_addtaocan_zuhe_addfenzu)
    TextView tvPayforAddtaocanZuheAddfenzu;
    @BindView(R.id.ll_payfor_addtaocan_zuhe)
    LinearLayout llPayforAddtaocanZuhe;
    @BindView(R.id.rv_payfor_addtaocan_danpin_shangpin)
    RecyclerView rvPayforAddtaocanDanpinShangpin;
    @BindView(R.id.tv_payfor_addtaocan_danpin_addshangpin)
    TextView tvPayforAddtaocanDanpinAddshangpin;
    @BindView(R.id.ll_payfor_addtaocan_danpin_taocan)
    LinearLayout llPayforAddtaocanDanpinTaocan;
    @BindView(R.id.ll_payfor_addtaocan)
    LinearLayout llPayforAddtaocan;
    private boolean zaishouFlag;//在售的状态 默认true

    private boolean taocanleixingFlag;//套餐类型 true 单品套餐  false 组合套餐
    PopupWindow mPopupWindow;

    /**
     * @return
     */
    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_addtaocan;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("添加套餐");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvPayforAddtaocanIcon});
        zaishouFlag = true;
        taocanleixingFlag = true;
        llPayforAddtaocanDanpinTaocan.setVisibility(View.VISIBLE);//默认显示添加单人套餐
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.rl_payfor_addtaocan_fenlei, R.id.tv_payfor_addtaocan_shifouzaishou, R.id.rl_payfor_addtaocan_taocanleixing, R.id.tv_payfor_addtaocan_zuhe_addfenzu, R.id.tv_payfor_addtaocan_danpin_addshangpin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_payfor_addtaocan_fenlei:
                //跳转套餐分类页面
                //跳转到选择分类页面
                Intent classIntent = new Intent(this, ClassActivity.class);
                startActivityForResult(classIntent, 2);//2表示在本页面跳转到分类页面的  只显示套餐分类 TODO
                break;
            case R.id.rl_payfor_addtaocan_taocanleixing:
                //选择套餐类型  如果是单品套餐 显示添加单品  组合套餐 显示添加分组   默认显示添加单人套餐
                showPopWindow();
                break;
            case R.id.tv_payfor_addtaocan_zuhe_addfenzu:
                //添加分组按钮  跳转添加分组页面
                Intent taocanfenzuIntent = new Intent(this, TaocanfenzuActivity.class);
                startActivity(taocanfenzuIntent);
                break;
            case R.id.tv_payfor_addtaocan_danpin_addshangpin:
                //添加单品按钮  跳转添加单品页面
                Intent danpinIntent = new Intent(this, AddNosaomaActivity.class);
                danpinIntent.putExtra("flag",1);//1  表示从本页面跳转添加商品页面的 2表示从不参与扫码商品页面跳转的NosapmaFoodActivity
                startActivity(danpinIntent);
                break;
            case R.id.tv_payfor_addtaocan_shifouzaishou:
                //是否在售状态
                if (zaishouFlag) {
                    zaishouFlag = false;
                    tvPayforAddtaocanShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));

                } else {
                    zaishouFlag = true;
                    tvPayforAddtaocanShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                }
                break;
        }
    }





    /**
     * 弹框选择套餐类型
     */
    private void showPopWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_taocanleixing, null);

        RelativeLayout rl_pop_add_taocanleixing_danpin = view.findViewById(R.id.rl_pop_add_taocanleixing_danpin);
        RelativeLayout rl_pop_add_taocanleixing_zuhe = view.findViewById(R.id.rl_pop_add_taocanleixing_zuhe);

        TextView tv_pop_add_taocanleixing_danpin_icon = view.findViewById(R.id.tv_pop_add_taocanleixing_danpin_icon);
        TextView tv_pop_add_taocanleixing_zuhe_icon = view.findViewById(R.id.tv_pop_add_taocanleixing_zuhe_icon);

        setIconFont(new TextView[]{tv_pop_add_taocanleixing_danpin_icon, tv_pop_add_taocanleixing_zuhe_icon});

        //套餐模式回显   默认单品套餐
        if (taocanleixingFlag){
            tv_pop_add_taocanleixing_danpin_icon.setVisibility(View.VISIBLE);
            tv_pop_add_taocanleixing_zuhe_icon.setVisibility(View.GONE);
        }else {
            tv_pop_add_taocanleixing_danpin_icon.setVisibility(View.GONE);
            tv_pop_add_taocanleixing_zuhe_icon.setVisibility(View.VISIBLE);
        }



        rl_pop_add_taocanleixing_danpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llPayforAddtaocanDanpinTaocan.setVisibility(View.VISIBLE);
                llPayforAddtaocanZuhe.setVisibility(View.GONE);
                tvPayforAddtaocanLeixing.setText("单品套餐");
                taocanleixingFlag = true;
                mPopupWindow.dismiss();
            }
        });
        rl_pop_add_taocanleixing_zuhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llPayforAddtaocanDanpinTaocan.setVisibility(View.GONE);
                llPayforAddtaocanZuhe.setVisibility(View.VISIBLE);
                tvPayforAddtaocanLeixing.setText("组合套餐");
                taocanleixingFlag = false;
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
        mPopupWindow.showAtLocation(llPayforAddtaocan, Gravity.BOTTOM, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(AddTaocanActivity.this, 1f);//0.0-1.0
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


    //===============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
