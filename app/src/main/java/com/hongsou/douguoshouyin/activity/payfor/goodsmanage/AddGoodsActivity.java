package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.ShopStandarAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.javabean.ShopStandarList;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 添加商品
 */
public class AddGoodsActivity extends BaseActivity {


    @BindView(R.id.tv_payfor_addgoods_icon)
    TextView tvPayforAddgoodsIcon;
    @BindView(R.id.et_payfor_addgoods_mingcheng)
    EditText etPayforAddgoodsMingcheng;

    @BindView(R.id.rl_payfor_addgoods_fenlei)
    RelativeLayout rlPayforAddgoodsFenlei;
    @BindView(R.id.rv_payfor_addgoods_guige)
    RecyclerView rvPayforAddgoodsGuige;
    @BindView(R.id.et_payfor_addgoods_danwei)
    EditText etPayforAddgoodsDanwei;
    @BindView(R.id.tv_payfor_addgoods_shifouzaishou)
    TextView tvPayforAddgoodsShifouzaishou;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.tv_payfor_addgoods_fenlei)
    TextView tvPayforAddgoodsFenlei;
    @BindView(R.id.ll_payfor_addgoods_addguige)
    LinearLayout llPayforAddgoodsAddguige;

    //分类类型
    String singleProductType;
    @BindView(R.id.tv_payfor_addgoods_addguige)
    TextView tvPayforAddgoodsAddguige;
    @BindView(R.id.tv_payfor_addgoods_time)
    TextView tvPayforAddgoodsTime;
    @BindView(R.id.rl_payfor_addgoods_time)
    RelativeLayout rlPayforAddgoodsTime;

    //类型名称
    private String categoryName;
    private boolean zaishouFlag;//在售的状态 默认true

    //规格列表
    List<ShopStandarList> shopStandarLists = new ArrayList<>();
    //规格适配器
    ShopStandarAdapter shopStandarAdapter;

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
        setIconFont(new TextView[]{tvPayforAddgoodsIcon, tvPayforAddgoodsAddguige});
        zaishouFlag = true;
        tvPayforAddgoodsShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
        tvTitlebarRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        shopStandarLists.clear();
        shopStandarLists.add(new ShopStandarList("",""));
        showStandarList(shopStandarLists);
    }

    /**
     * @param shopStandarLists 数据源
     * @author fenghao
     * @date 2018/7/19 0019 下午 14:30
     * @desc 展示一个添加规格的页面
     */
    private void showStandarList(final List<ShopStandarList> shopStandarLists) {
        shopStandarAdapter = new ShopStandarAdapter(R.layout.module_item_standar,shopStandarLists);
        rvPayforAddgoodsGuige.setAdapter(shopStandarAdapter);
        rvPayforAddgoodsGuige.setLayoutManager(new LinearLayoutManager(this));

        shopStandarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //点击删除按钮
                if (view.getId()==R.id.tv_item_standar_del){
                    shopStandarLists.remove(position);
                    shopStandarAdapter.notifyDataSetChanged();
                }
            }
        });
        shopStandarAdapter.setOnTextChangeListener(new ShopStandarAdapter.onTextChangeListener() {
            @Override
            public void onTextChanged(int pos, String standardName, String sell) {
                if (!TextUtils.isEmpty(standardName)){
                    shopStandarLists.get(pos).setStandardName(standardName);
                }
                if (!TextUtils.isEmpty(sell)){
                    shopStandarLists.get(pos).setSell(sell);
                }
            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //获取返回的分类信息
            singleProductType = data.getStringExtra("singleProductType");
            categoryName = data.getStringExtra("categoryName");
            tvPayforAddgoodsFenlei.setText(categoryName);
        }
    }

    @OnClick({R.id.tv_titlebar_right, R.id.tv_payfor_addgoods_icon, R.id.rl_payfor_addgoods_fenlei
            , R.id.tv_payfor_addgoods_shifouzaishou, R.id.ll_payfor_addgoods_addguige, R.id.rl_payfor_addgoods_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_payfor_addgoods_icon:
                break;
            case R.id.rl_payfor_addgoods_fenlei:
                //跳转到选择分类页面
                Intent classIntent = new Intent(AddGoodsActivity.this, ChooseCategoryActivity.class);
                startActivityForResult(classIntent, 1);//1表示在本页面跳转到分类页面的  只显示单品分类
                break;
            case R.id.tv_payfor_addgoods_shifouzaishou:
                //在售状态
                if (zaishouFlag) {
                    tvPayforAddgoodsShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_nomal));
                    zaishouFlag = false;
                } else {
                    tvPayforAddgoodsShifouzaishou.setBackground(getResources().getDrawable(R.drawable.imgbt_selector));
                    zaishouFlag = true;
                }
                break;
            case R.id.tv_titlebar_right:
                //保存操作  走接口
//                baocun();
                for (int i = 0 ;i<shopStandarLists.size();i++){
                    Log.e(TAG, "onViewClicked: "+shopStandarLists.get(i).getStandardName().toString() );
                }
                break;
            case R.id.ll_payfor_addgoods_addguige:
                //点一下添加一个规格
                if (shopStandarLists.size()<12){
                    shopStandarLists.add(new ShopStandarList("",""));
                    shopStandarAdapter.notifyDataSetChanged();
                }

                break;
            case R.id.rl_payfor_addgoods_time:
                //在售时间
                break;
            default:
                break;
        }
    }


    /**
     * 保存
     */
    private void baocun() {
        showLoadingDialog();
        HttpFactory.post().url(ApiConfig.ADD_SINGLE_FOOD).addParams("", "").build().execute(new StringCallback() {
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
