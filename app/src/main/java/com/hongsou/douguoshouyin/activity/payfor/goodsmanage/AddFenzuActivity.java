package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.AddFenzuSinglefoodAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.ProductListBean;
import com.hongsou.douguoshouyin.javabean.SingleFoodsBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //单品数据源
    List<SingleFoodsBean> singleFoodsBeanList;

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
        setIconFont(new TextView[]{tvPayforAddfenzuJia, tvPayforAddfenzuJian});
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
                tvPayforAddfenzuShuliang.setText((bidianfood + 1) + "");
                break;
            case R.id.tv_payfor_addfenzu_jian:
                //必点数量 减
                bidianfood = Integer.valueOf(tvPayforAddfenzuShuliang.getText().toString());
                if ((bidianfood - 1) > 0) {
                    tvPayforAddfenzuShuliang.setText((bidianfood - 1) + "");
                } else {
                    ToastUtil.showToast("必点数量不得小于1");
                }
                break;
            case R.id.tv_payfor_addfenzu_addfoods:
                //跳转到添加分组商品页面
                Intent danpinIntent = new Intent(this, AddTaocanFoodsActivity.class);
                startActivityForResult(danpinIntent, 3);
                break;
            case R.id.tv_payfor_addfenzu_save:
                //保存分组 先检查数据
                checkMap();
                break;
            default:
                break;
        }
    }

    /**
     * @author fenghao
     * @date 2018/7/21 0021 上午 11:59
     * @desc 检查数据
     */
    private void checkMap() {

        if (TextUtils.isEmpty(etPayforAddfenzuName.getText().toString())) {
            ToastUtil.showToast("请填写分组名称");
            return;
        }

        if (singleFoodsBeanList==null||singleFoodsBeanList.size() < 1) {
            ToastUtil.showToast("请选择商品");
            return;
        }else {
            for (int i =0;i<singleFoodsBeanList.size();i++){
                if (TextUtils.isEmpty(singleFoodsBeanList.get(i).getMinGroup())){
                    ToastUtil.showToast("您有编号未填写");
                    return;
                }

            }
        }


        if (singleFoodsBeanList.size()<Integer.valueOf(tvPayforAddfenzuShuliang.getText().toString())){
            //餐品数量不得小于必点数量
            ToastUtil.showToast("餐品数量不得小于必点数量,请添加商品");
            return;
        }
        Map<String, Object> addFenzuMap = new HashMap<>();
        //店铺编号
        addFenzuMap.put("shopNumber", getShopNumber());
        //	分组名称
        addFenzuMap.put("groupName", etPayforAddfenzuName.getText().toString());
        //	商品必点数量
        addFenzuMap.put("groupCount", tvPayforAddfenzuShuliang.getText().toString());

        List<ProductListBean> productListBeanList = new ArrayList<>();
        for (int k =0;k<singleFoodsBeanList.size();k++){
            productListBeanList.add(new ProductListBean(singleFoodsBeanList.get(k).getSingleProductNumber(),
                    singleFoodsBeanList.get(k).getSingleQuantity(),
                    singleFoodsBeanList.get(k).getStandardNumber(),
                    singleFoodsBeanList.get(k).getMinGroup()
                    ));
        }
        //商品列表
        addFenzuMap.put("productList", productListBeanList);
        upAddFenzu(addFenzuMap);
    }

    /**
     *  @author  fenghao
     *  @date    2018/7/21 0021 下午 12:05
     *  @param   addFenzuMap
     *  @desc   上传分组接口
     */
    private void upAddFenzu(Map<String, Object> addFenzuMap) {
        Gson gson = new Gson();
        HttpFactory.postString(ApiConfig.ADD_GROUP, gson.toJson(addFenzuMap), new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()){
                    ToastUtil.showToast("添加分组成功");
                    finishActivity();
                }else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 2:
                //从选择商品页面返回的数据singleFoodsBeanList
                if (singleFoodsBeanList != null) {
                    singleFoodsBeanList.clear();
                }
                singleFoodsBeanList = (List<SingleFoodsBean>) data.getSerializableExtra("singleFoodsBeanList");
                Log.e(TAG, "onActivityResult: " + singleFoodsBeanList.size());
                setSingleFoodsBeanList(singleFoodsBeanList);
                break;
            default:
                break;
        }
    }

    /**
     * @param singleFoodsBeanList 从选择商品页面传回的数据元
     * @author fenghao
     * @date 2018/7/21 0021 上午 9:20
     * @desc 设置数据
     */
    private void setSingleFoodsBeanList(List<SingleFoodsBean> singleFoodsBeanList) {
        for (int i = 0; i < singleFoodsBeanList.size(); i++) {
            if (singleFoodsBeanList.get(i).getSingleQuantity() == 0) {
                singleFoodsBeanList.remove(i);
                i--;
            }
        }

        showSingleFoods(singleFoodsBeanList);
    }

    AddFenzuSinglefoodAdapter addFenzuSinglefoodAdapter;

    /**
     * @param singleFoodsBeanList 处理后的数据
     * @author fenghao
     * @date 2018/7/21 0021 上午 9:22
     * @desc 展示返回的商品
     */
    private void showSingleFoods(final List<SingleFoodsBean> singleFoodsBeanList) {
        Log.e(TAG, "showSingleFoods: " + singleFoodsBeanList.size());

        addFenzuSinglefoodAdapter = new AddFenzuSinglefoodAdapter(R.layout.module_item_addfenzu_singfoods, singleFoodsBeanList);
        rvPayforAddfenzuFoods.setAdapter(addFenzuSinglefoodAdapter);
        rvPayforAddfenzuFoods.setLayoutManager(new LinearLayoutManager(this));
        addFenzuSinglefoodAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_item_addfenzu_singlefoods_del) {
                    singleFoodsBeanList.remove(position);
                    addFenzuSinglefoodAdapter.notifyDataSetChanged();
                }
            }
        });

        addFenzuSinglefoodAdapter.setOnTextChangeListener(new AddFenzuSinglefoodAdapter.onTextChangeListener() {
            @Override
            public void onTextChanged(int pos, String minGroup, String sell) {
                if (!TextUtils.isEmpty(minGroup)) {
                    singleFoodsBeanList.get(pos).setMinGroup(minGroup);
                }
            }
        });
    }

    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}