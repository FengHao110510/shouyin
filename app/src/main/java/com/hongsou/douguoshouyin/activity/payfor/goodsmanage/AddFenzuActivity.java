package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.tool.ToastUtil;

public class AddFenzuActivity extends BaseActivity {

    @BindView(R.id.tv_payfor_addfenzu_jia)
    TextView tvPayforAddfenzuJia;
    @BindView(R.id.tv_payfor_addfenzu_shuliang)
    TextView tvPayforAddfenzuShuliang;
    @BindView(R.id.tv_payfor_addfenzu_jian)
    TextView tvPayforAddfenzuJian;
    @BindView(R.id.rv_payfor_addfenzu_foods)
    RecyclerView rvPayforAddfenzuFoods;
    @BindView(R.id.tv_payfor_addfenzu_addfoods)
    TextView tvPayforAddfenzuAddfoods;
    @BindView(R.id.tv_payfor_addfenzu_save)
    TextView tvPayforAddfenzuSave;
    @BindView(R.id.et_payfor_addfenzu_name)
    EditText etPayforAddfenzuName;

    int bidianfood;
    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_addfenzu;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initBack();
        initTitle("添加分组");
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvPayforAddfenzuJia,tvPayforAddfenzuJian});
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_payfor_addfenzu_jia, R.id.tv_payfor_addfenzu_jian, R.id.tv_payfor_addfenzu_addfoods, R.id.tv_payfor_addfenzu_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_payfor_addfenzu_jia:
                //必点数量 加
                bidianfood = Integer.valueOf(tvPayforAddfenzuShuliang.getText().toString());
                tvPayforAddfenzuShuliang.setText((bidianfood+1)+"");
                break;
            case R.id.tv_payfor_addfenzu_jian:
                //必点数量 减
                bidianfood = Integer.valueOf(tvPayforAddfenzuShuliang.getText().toString());
                if ((bidianfood-1)>0){
                    tvPayforAddfenzuShuliang.setText((bidianfood-1)+"");
                }else {
                    ToastUtil.showToast("必点数量不得小于1");
                }
                break;
            case R.id.tv_payfor_addfenzu_addfoods:
                //跳转到添加分组商品页面
                break;
            case R.id.tv_payfor_addfenzu_save:
                //保存分组
                break;
        }
    }

    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}