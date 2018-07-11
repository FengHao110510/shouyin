package com.hongsou.douguoshouyin.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;

/**
 * 打印设置页面
 */
public class PrinterActivity extends BaseActivity {
    @BindView(R.id.tv_mine_printer_kaiguan)
    TextView tvMinePrinterKaiguan;
    @BindView(R.id.tv_mine_printer_sousuoing)
    TextView tvMinePrinterSousuoing;
    @BindView(R.id.tv_mine_printer_sousuo)
    TextView tvMinePrinterSousuo;
    @BindView(R.id.pro_mine_printer_sousuo)
    ProgressBar proMinePrinterSousuo;
    @BindView(R.id.rv_mine_printer_xinxi)
    RecyclerView rvMinePrinterXinxi;


    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_printer;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("打印管理");


    }


    @Override
    public void initView() {
        setIconFont(new TextView[]{tvMinePrinterSousuo});

        //初始化蓝牙开关
    }

    @Override
    public void initData() {


    }


    @OnClick({R.id.tv_mine_printer_kaiguan, R.id.tv_mine_printer_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_printer_kaiguan:


                break;
            case R.id.tv_mine_printer_sousuo:
                //点击搜索后 搜索中  pro都显示  搜索完成后 gone
                break;
        }
    }


    //===========================================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}