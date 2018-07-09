package com.hongsou.douguoshouyin.activity.payfor.shangpinguanli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.Apiconfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import okhttp3.Call;

public class AddGoodsActivity extends BaseActivity {


    @BindView(R.id.tv_payfor_addgoods_icon)
    TextView tvPayforAddgoodsIcon;
    @BindView(R.id.et_payfor_addgoods_mingcheng)
    EditText etPayforAddgoodsMingcheng;

    @BindView(R.id.rl_payfor_addgoods_fenlei)
    RelativeLayout rlPayforAddgoodsFenlei;
    @BindView(R.id.tv_payfor_addgoods_shifouduoguige)
    TextView tvPayforAddgoodsShifouduoguige;
    @BindView(R.id.rv_payfor_addgoods_guige)
    RecyclerView rvPayforAddgoodsGuige;
    @BindView(R.id.et_payfor_addgoods_danwei)
    EditText etPayforAddgoodsDanwei;
    @BindView(R.id.tv_payfor_addgoods_shifouzaishou)
    TextView tvPayforAddgoodsShifouzaishou;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;


    private boolean guigeFlag;//规格的状态  默认false
    private boolean zaishouFlag;//在售的状态 默认true

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_addgoods;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("添加商品");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvPayforAddgoodsIcon});
        guigeFlag = false;
        zaishouFlag = true;
        tvPayforAddgoodsShifouduoguige.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
        tvPayforAddgoodsShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));

        tvTitlebarRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            //获取返回的分类信息
        }
    }

    @OnClick({R.id.tv_titlebar_right, R.id.tv_payfor_addgoods_icon, R.id.rl_payfor_addgoods_fenlei, R.id.tv_payfor_addgoods_shifouduoguige, R.id.tv_payfor_addgoods_shifouzaishou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_payfor_addgoods_icon:
                break;
            case R.id.rl_payfor_addgoods_fenlei:
                //跳转到选择分类页面
                Intent classIntent = new Intent(AddGoodsActivity.this,ClassActivity.class);
                startActivityForResult(classIntent,1);//1表示在本页面跳转到分类页面的  只显示单品分类
                break;
            case R.id.tv_payfor_addgoods_shifouduoguige:
                //规格展示状态   显示多规格
                if (guigeFlag) {
                    tvPayforAddgoodsShifouduoguige.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
                    guigeFlag = false;
                } else{
                    tvPayforAddgoodsShifouduoguige.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                    guigeFlag = true;
                }
                break;
            case R.id.tv_payfor_addgoods_shifouzaishou:
                //在售状态
                if (zaishouFlag) {
                    tvPayforAddgoodsShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
                    zaishouFlag = false;
                } else{
                    tvPayforAddgoodsShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                    zaishouFlag = true;
                }
                    break;
            case R.id.tv_titlebar_right:
                //保存操作  走接口
                baocun();
                break;
        }
    }

    /**
     * 保存
     */
    private void baocun() {
        showLoadingDialog();
        HttpFactory.post().url(Apiconfig.tianjiashangpin).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
            }
        });
    }

    //===================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
