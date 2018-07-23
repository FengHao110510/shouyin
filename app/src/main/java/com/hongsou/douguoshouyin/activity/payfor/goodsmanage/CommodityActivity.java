package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;

/**
 * 商品管理主页面
 */
public class CommodityActivity extends BaseActivity {
//TODO 重新加载页面时刷新

    @BindView(R.id.tv_payfor_commodity_tag_guanlifenlei_icon)
    TextView tvPayforCommodityTagGuanlifenleiIcon;
    @BindView(R.id.ll_payfor_commodity_tag_guanlifenlei)
    LinearLayout llPayforCommodityTagGuanlifenlei;
    @BindView(R.id.tv_payfor_commodity_tag_tianjiashangpin_icon)
    TextView tvPayforCommodityTagTianjiashangpinIcon;
    @BindView(R.id.ll_payfor_commodity_tag_tianjiashangpin)
    LinearLayout llPayforCommodityTagTianjiashangpin;
    @BindView(R.id.tv_payfor_commodity_tag_xiugaipaixu_icon)
    TextView tvPayforCommodityTagXiugaipaixuIcon;

    @BindView(R.id.tv_payfor_commodity_tag_gengduocaozuo_icon)
    TextView tvPayforCommodityTagGengduocaozuoIcon;
    @BindView(R.id.ll_payfor_commodity_tag_gengduocaozuo)
    LinearLayout llPayforCommodityTagGengduocaozuo;
    @BindView(R.id.ll_payfor_commodity)
    LinearLayout llPayforCommodity;
    @BindView(R.id.tv_payfor_commodity_tag_tianjiataocan_icon)
    TextView tvPayforCommodityTagTianjiataocanIcon;
    @BindView(R.id.ll_payfor_commodity_tag_tianjiataocan)
    LinearLayout llPayforCommodityTagTianjiataocan;
    @BindView(R.id.ll_payfor_commodity_tag)
    LinearLayout llPayforCommodityTag;
    @BindView(R.id.ll_payfor_commodity_tag_tianjiazuhe)
    LinearLayout llPayforCommodityTagTianjiazuhe;
    private PopupWindow mPopupWindow;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_commodity;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("商品管理");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvPayforCommodityTagGengduocaozuoIcon, tvPayforCommodityTagGuanlifenleiIcon
                , tvPayforCommodityTagTianjiashangpinIcon, tvPayforCommodityTagXiugaipaixuIcon, tvPayforCommodityTagTianjiataocanIcon});

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ll_payfor_commodity_tag_tianjiataocan, R.id.ll_payfor_commodity_tag_guanlifenlei, R.id.ll_payfor_commodity_tag_tianjiashangpin, R.id.ll_payfor_commodity_tag_tianjiazuhe, R.id.ll_payfor_commodity_tag_gengduocaozuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_payfor_commodity_tag_guanlifenlei:
                //跳转分类管理页面
                Intent manageIntent = new Intent(this, ManageClassActivity.class);
                startActivity(manageIntent);
                break;
            case R.id.ll_payfor_commodity_tag_tianjiashangpin:
                //跳转添加商品页面
                Intent addgoodsIntent = new Intent(this, AddGoodsActivity.class);
                startActivity(addgoodsIntent);
                break;
            case R.id.ll_payfor_commodity_tag_tianjiazuhe:
                //添加分组页面
                Intent addzuheIntent = new Intent(this, TaocanfenzuActivity.class);
                addzuheIntent.putExtra("choose","");
                startActivity(addzuheIntent);
                break;
            case R.id.ll_payfor_commodity_tag_gengduocaozuo:
                //弹出popwindow
                showPopWindow();
                break;
            case R.id.ll_payfor_commodity_tag_tianjiataocan:
                //跳转添加套餐页面
                Intent addtaocanIntent = new Intent(this, AddTaocanActivity.class);
                startActivity(addtaocanIntent);
                break;
            default:
                break;
        }
    }

    //popwindow弹窗
    private void showPopWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.module_pop_payfor_commodity, null);

        TextView tv_payfor_commodity_pop_qishou = view.findViewById(R.id.tv_payfor_commodity_pop_qishou);
        TextView tv_payfor_commodity_pop_tingshou = view.findViewById(R.id.tv_payfor_commodity_pop_tingshou);
        TextView tv_payfor_commodity_pop_shanchu = view.findViewById(R.id.tv_payfor_commodity_pop_shanchu);

        //批量起售

        tv_payfor_commodity_pop_qishou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //批量停售

        tv_payfor_commodity_pop_tingshou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //批量删除

        tv_payfor_commodity_pop_shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Display display = this.getWindowManager().getDefaultDisplay();
        int h = display.getHeight();
        int w = display.getWidth();
        mPopupWindow = new PopupWindow(view, w * 2 / 5, h * 3 / 10);
        // 设置SelectPicPopupWindow弹出窗体动画效果，设置动画，一会会讲解
        mPopupWindow.setAnimationStyle(R.style.et_style);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(llPayforCommodity, Gravity.BOTTOM, w * 4 / 9, h / 11);


    }

    //---------------======================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}



