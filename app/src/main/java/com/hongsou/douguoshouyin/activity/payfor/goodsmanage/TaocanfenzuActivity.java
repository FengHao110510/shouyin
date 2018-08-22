package com.hongsou.douguoshouyin.activity.payfor.goodsmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.GroupAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BaseBean;
import com.hongsou.douguoshouyin.javabean.GroupBean;
import com.hongsou.douguoshouyin.javabean.GroupContentBean;
import com.hongsou.douguoshouyin.javabean.GroupMultiItemEntity;
import com.hongsou.douguoshouyin.javabean.GroupTitleBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//判断是哪里来的 套餐-选择套餐分组   或者 套餐-套餐分组
public class TaocanfenzuActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.rv_payfor_taocanfenzu_zuhe)
    RecyclerView rvPayfortaocanfenzuZuhe;
    @BindView(R.id.tv_payfor_taocanfenzu_add)
    TextView tvPayfortaocanfenzuAdd;
    @BindView(R.id.tv_titlebar_finish_back)
    TextView tvTitlebarFinishBack;
    //数据源
    List<GroupMultiItemEntity> groupMultiItemEntityList = new ArrayList<>();

    //适配器
    GroupAdapter groupAdapter;

    //区分是选择还是查询页面的flge
    int choseFlag;

    private List<GroupBean.DataBean> dataBeanList;

    @Override
    public int initLayout() {
        return R.layout.module_activity_payfor_taocanfenzu;
    }

    @Override
    protected void init() {
        initView();
        initData();
        initTitle("套餐分组");

    }

    @Override
    public void initView() {
        //判断是 选择页面 还是 添加页面  选择页面将右上角的选择按钮显示 还有recyclerView中的样式改变
        tvTitlebarRight.setText("选择");
        Intent intent = getIntent();
        String choose = intent.getStringExtra("choose");
        if (choose.contains("choose")) {
            //是选择页面
            choseFlag = 1;
            tvTitlebarRight.setVisibility(View.VISIBLE);
        } else {
            //分组页面
            choseFlag = 2;
        }
        setIconFont(new TextView[]{tvTitlebarFinishBack});
        tvTitlebarFinishBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForTaocanResult();
            }
        });
    }

    @Override
    public void initData() {
        getGroupList();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getGroupList();
    }

    //请求分组列表接口
    private void getGroupList() {

        HttpFactory.get().url(ApiConfig.GET_GROUP_LIST).addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<GroupBean>(this) {
            @Override
            public void onResponse(GroupBean response, int id) {
                if (response.isSuccess()) {
                    dataBeanList = response.getData();
                    Log.e(TAG, "onResponse: " + dataBeanList.size());
                    showGroupList(setData(dataBeanList));

                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    /**
     * @param dataBeanList
     * @author fenghao
     * @date 2018/7/21 0021 下午 16:11
     * @desc 设置数据
     */
    private List<GroupMultiItemEntity> setData(List<GroupBean.DataBean> dataBeanList) {
        groupMultiItemEntityList.clear();
        List<GroupBean.DataBean.ProductListBean> productListBeanList;
        for (int i = 0; i < dataBeanList.size(); i++) {
            groupMultiItemEntityList.add(new GroupMultiItemEntity(GroupMultiItemEntity.TITLE,
                    new GroupTitleBean(dataBeanList.get(i).getGroupName()
                            , dataBeanList.get(i).getGroupNumber()
                            , dataBeanList.get(i).getProductList().size() + "选" + dataBeanList.get(i).getGroupCount()
                            , false)));
            productListBeanList = dataBeanList.get(i).getProductList();
            for (int k = 0; k < productListBeanList.size(); k++) {
                groupMultiItemEntityList.add(new GroupMultiItemEntity(GroupMultiItemEntity.CONTENT,
                        new GroupContentBean(productListBeanList.get(k).getFoodFullName()
                                , productListBeanList.get(k).getFoodProductsCount()
                                , productListBeanList.get(k).getStandard()
                                , productListBeanList.get(k).getMinGroup()
                                , productListBeanList.get(k).getFoodProductsNumber())));
            }
        }
        return groupMultiItemEntityList;
    }

    /**
     * @param groupMultiItemEntityList
     * @author fenghao
     * @date 2018/7/21 0021 下午 16:41
     * @desc 展示
     */
    private void showGroupList(final List<GroupMultiItemEntity> groupMultiItemEntityList) {
        groupAdapter = new GroupAdapter(groupMultiItemEntityList, choseFlag);
        rvPayfortaocanfenzuZuhe.setLayoutManager(new LinearLayoutManager(this));
        rvPayfortaocanfenzuZuhe.setAdapter(groupAdapter);
        LinearLayout llItemTaocanfenzuShoulistTitleDel;
        ImageView ivItemTaocanfenzuShoulistTitleCheck;

        groupAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_item_taocanfenzu_shoulist_title_del:
                        delGroup(groupMultiItemEntityList.get(position).getGroupTitleBean().getGroupNumber());
                        break;
                    case R.id.iv_item_taocanfenzu_shoulist_title_check:
//                         = (ImageView) groupAdapter.getViewByPosition(rvPayfortaocanfenzuZuhe, position
//                                , R.id.iv_item_taocanfenzu_shoulist_title_check);
                        ImageView ivItemTaocanfenzuShoulistTitleCheck = view.findViewById(R.id.iv_item_taocanfenzu_shoulist_title_check);
                        if (groupMultiItemEntityList.get(position).getGroupTitleBean().isCheck()) {
                            ivItemTaocanfenzuShoulistTitleCheck.setBackground(getResources().getDrawable(R.drawable.weixuan));
                            groupMultiItemEntityList.get(position).getGroupTitleBean().setCheck(false);
                        } else {
                            ivItemTaocanfenzuShoulistTitleCheck.setBackground(getResources().getDrawable(R.drawable.xuanzhong));
                            groupMultiItemEntityList.get(position).getGroupTitleBean().setCheck(true);
                        }
                        break;
                    default:
                        break;
                }

            }
        });
    }

    /**
     * @param groupNumber
     * @author fenghao
     * @date 2018/7/21 0021 下午 16:53
     * @desc 删除分组
     */
    private void delGroup(String groupNumber) {
        Map<String, String> delMap = new HashMap<>();
        delMap.put("shopNumber", getShopNumber());
        delMap.put("groupNumber", groupNumber);
        Gson gson = new Gson();
        HttpFactory.postString(ApiConfig.DELETE_GROUP, gson.toJson(delMap), new ResponseCallback<BaseBean>(this) {
            @Override
            public void onResponse(BaseBean response, int id) {
                if (response.isSuccess()) {
                    ToastUtil.showToast("删除成功");
                    getGroupList();
                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    @OnClick({R.id.tv_titlebar_right, R.id.tv_payfor_taocanfenzu_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_right:
                startForTaocanResult();
                break;
            case R.id.tv_payfor_taocanfenzu_add:
                //跳转到添加分组页面
                Intent addIntent = new Intent(this, AddFenzuActivity.class);
                startActivityForResult(addIntent, 1);
                break;
            default:
                break;
        }
    }

    /**
     * @author fenghao
     * @date 2018/7/23 0023 上午 11:31
     * @desc 返回给添加套餐页面的数据
     */
    private List<GroupBean.DataBean> dataBeanList2 = new ArrayList<>();

    private void startForTaocanResult() {
        for (int k = 0; k < groupMultiItemEntityList.size(); k++) {
            if (groupMultiItemEntityList.get(k).getItemType() == GroupMultiItemEntity.TITLE) {
                if (groupMultiItemEntityList.get(k).getGroupTitleBean().isCheck()) {
                    for (int h = 0; h < dataBeanList.size(); h++) {
                        if (groupMultiItemEntityList.get(k).getGroupTitleBean().getGroupNumber()
                                .equals(dataBeanList.get(h).getGroupNumber())) {
                            dataBeanList2.add(dataBeanList.get(h));
                        }
                    }
                }
            }
        }
//        List<GroupMultiItemEntity> groupMultiItemEntities = setData(dataBeanList2);
        Intent intentPut = new Intent();
//        intentPut.putExtra("groupMultiItemEntities", (Serializable) groupMultiItemEntities);
        intentPut.putExtra("dataBeanList2", (Serializable) dataBeanList2);
        Gson gson = new Gson();

        Log.e(TAG, "startForTaocanResult: " + gson.toJson(dataBeanList2));
        setResult(5, intentPut);
        finishActivity();
    }

    @Override
    public void onBackPressed() {
        startForTaocanResult();
    }

    //=================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
