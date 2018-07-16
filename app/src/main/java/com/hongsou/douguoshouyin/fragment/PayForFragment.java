package com.hongsou.douguoshouyin.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsou.douguoshouyin.activity.payfor.createorder.CreateOrderActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.payfor.TongjiActivity;
import com.hongsou.douguoshouyin.activity.payfor.table.TableActivity;
import com.hongsou.douguoshouyin.activity.payfor.payfor.PayForActivity;
import com.hongsou.douguoshouyin.activity.payfor.goodsmanage.CommodityActivity;
import com.hongsou.douguoshouyin.base.BaseFragment;
import com.hongsou.douguoshouyin.tool.ToastUtil;

/**
 * 文件描述：new！！com.example.administrator.myapplication.app.fragment
 * 作者：fh
 * 创建时间：2018/6/13
 * 更改时间：2018/6/13
 * 版本号：1
 * 用途：收款Fragment
 */


public class PayForFragment extends BaseFragment {

    @BindView(R.id.tv_payfor_payfor)
    TextView tvPayforpayfor;
    @BindView(R.id.tv_payfor_kaidan)
    TextView tvPayforKaidan;
    @BindView(R.id.tv_payfor_turnover)
    TextView tvPayforTurnover;
    @BindView(R.id.rl_payfor_payfor)
    RelativeLayout rlPayforPayfor;
    @BindView(R.id.rv_payfor_kaidan)
    RelativeLayout rvPayforKaidan;
    @BindView(R.id.rv_payfor_turnover)
    RelativeLayout rvPayforTurnover;
    @BindView(R.id.gv_payfor_grid)
    GridView gvPayforGrid;
    @BindView(R.id.bn_payfor_banner)
    Banner bnPayforBanner;

    Unbinder unbinder;

    ArrayList<HashMap<String, Object>> gridDataList;
    ArrayList<String> listBannerImg;//轮播图片集合
    @BindView(R.id.rv_payfor_shishoujine)
    TextView rvPayforShishoujine;
    @BindView(R.id.rv_payfor_dingdanshu)
    TextView rvPayforDingdanshu;

    @Override
    public int getLayoutId() {
        return R.layout.module_fragment_payfor;
    }

    private SimpleAdapter adapter;

    @Override
    public void init() {
        initView();
        initData();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        setIconFont(new TextView[]{tvPayforpayfor, tvPayforKaidan, tvPayforTurnover});
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //初始化gridView数据
        initGridView();
        //初始化banner TODO 需要走接口获取图片地址 和跳转信息
        initBanner();
        //初始化订单金额和单数
        initDingdan();
    }

    /**
     *    初始化订单金额和单数 TODO 每回点击底部收款按钮需要走接口？  eventbus
     */
    private void initDingdan() {

    }


    /**
     * 初始化 gridview
     */
    private void initGridView() {
        //图片
        int icon[] = {R.drawable.zhuotai, R.drawable.shangpinguanli, R.drawable.saomadiancan};
        //text
        final String name[] = {"桌台管理", "商品管理", "扫码点餐"};
        gridDataList = new ArrayList<>();

        for (int i = 0; i < icon.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("img", icon[i]);
            map.put("text", name[i]);
            gridDataList.add(map);
        }

        String[] from = {"img", "text"};

        int[] to = {R.id.img, R.id.text};

        adapter = new SimpleAdapter(getActivity(), gridDataList, R.layout.module_item_gridview, from, to);

        gvPayforGrid.setAdapter(adapter);

        gvPayforGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (name[i].contains("饿了么")) {
//                    PrintUtil.printdayin(getActivity());
//                    ToastUtil.showToast(name[i]);
//
//                }
//                if (name[i].contains("美团")) {
//                    ToastUtil.showToast(name[i]);
//                }
                if (name[i].contains("桌台")) {
                    ToastUtil.showToast(name[i]);
                    Intent zhuotaiIntent = new Intent(getActivity(), TableActivity.class);
                    startActivity(zhuotaiIntent);
                }
                if (name[i].contains("商品")) {
                    Intent shangpinIntent = new Intent(getActivity(), CommodityActivity.class);
                    startActivity(shangpinIntent);
                    ToastUtil.showToast(name[i]);
                }
                if (name[i].contains("扫码")) {
                    ToastUtil.showToast(name[i]);
                }
            }
        });
    }

    /**
     * 初始化banner
     */
    private void initBanner() {
        //放置图片的集合
        listBannerImg = new ArrayList<>();
        listBannerImg.add("http://img.zcool.cn/community/0190a156f6891332f875a94454222b.jpg@900w_1l_2o_100sh.jpg");
        listBannerImg.add("http://img0.imgtn.bdimg.com/it/u=3299150176,3007244694&fm=27&gp=0.jpg");
        listBannerImg.add("http://img.zcool.cn/community/01aea05927d0afb5b3086ed4bf6410.jpg@1280w_1l_2o_100sh.png");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        bnPayforBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        bnPayforBanner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        bnPayforBanner.setImages(listBannerImg);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        bnPayforBanner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        bnPayforBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        bnPayforBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        bnPayforBanner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                        //轮播图点击事件
                    }
                })
                //必须最后调用的方法，启动轮播图。
                .start();


    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

    @OnClick({R.id.rl_payfor_payfor, R.id.rv_payfor_kaidan, R.id.rv_payfor_turnover})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_payfor_payfor:
                //支付
                Intent payforIntent = new Intent(getActivity(), PayForActivity.class);
                startActivity(payforIntent);
                break;
            case R.id.rv_payfor_kaidan:
                //订单详情测试
//                Intent detailIntent = new Intent(getActivity(), OrderDetailActivity.class);
//                startActivity(detailIntent);

                //开单
                Intent kaidanIntent = new Intent(getActivity(), CreateOrderActivity.class);
                startActivity(kaidanIntent);
                break;
            case R.id.rv_payfor_turnover:
                //统计页面
                Intent tongjiIntent = new Intent(getActivity(), TongjiActivity.class);
                startActivity(tongjiIntent);
                break;
        }
    }

    //==================================================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
