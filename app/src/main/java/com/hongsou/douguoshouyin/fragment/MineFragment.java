package com.hongsou.douguoshouyin.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsou.douguoshouyin.activity.login.LoginActivity;
import com.hongsou.douguoshouyin.activity.mine.AuthorizeActivity;
import com.hongsou.douguoshouyin.activity.mine.HandoverActivity;
import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.ShopinforBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.MscSpeechUtils;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.activity.mine.AboutWeActivity;
import com.hongsou.douguoshouyin.activity.mine.CashierActivity;
import com.hongsou.douguoshouyin.activity.mine.ShopInformationActivity;
import com.hongsou.douguoshouyin.activity.mine.PrinterActivity;
import com.hongsou.douguoshouyin.activity.mine.ReplacePasswordActivity;
import com.hongsou.douguoshouyin.activity.mine.SaomaFoodsSetActivity;
import com.hongsou.douguoshouyin.base.BaseFragment;

import okhttp3.Call;

/**
 * 文件描述：new！！com.example.administrator.myapplication.app.fragment
 * 作者：fh
 * 创建时间：2018/6/13
 * 更改时间：2018/6/13
 * 版本号：1
 * 用途：mine 我的fragment
 */


public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_mine_head)
    ImageView ivMineHead;
    @BindView(R.id.iv_mine_address)
    TextView ivMineAddress;
    @BindView(R.id.rl_mine_mendianxinxi)
    RelativeLayout rlMineMendianxinxi;
    @BindView(R.id.rl_mine_xiugaimima)
    RelativeLayout rlMineXiugaimima;
    @BindView(R.id.rl_mine_dayinguanli)
    RelativeLayout rlMineDayinguanli;
    @BindView(R.id.rl_mine_shouyinshezhi)
    RelativeLayout rlMineShouyinshezhi;
    @BindView(R.id.rl_mine_saomadiancanshezhi)
    RelativeLayout rlMineSaomadiancanshezhi;
    @BindView(R.id.rl_mine_douguo)
    RelativeLayout rlMineDouguo;
    @BindView(R.id.rl_mine_guanyu)
    RelativeLayout rlMineGuanyu;
    @BindView(R.id.rl_mine_phone)
    RelativeLayout rlMinePhone;
    @BindView(R.id.btn_mine_logout)
    TextView btnMineLogout;
    Unbinder unbinder;
    @BindView(R.id.rl_mine_jiaoban)
    RelativeLayout rlMineJiaoban;
    @BindView(R.id.tv_mine_phone_icon)
    TextView tvMinePhoneIcon;

    private Dialog dialog;//登出弹框

    @Override
    public int getLayoutId() {
        return R.layout.module_fragment_mine;
    }

    @Override
    public void init() {
        getShopinfor();
        setIconFont(new TextView[]{tvMinePhoneIcon});
    }


    /**
     * @author fenghao
     * @date 2018/7/30 0030 上午 11:21
     * @desc 获取商店信息
     */
    private void getShopinfor() {
        HttpFactory.get().url(ApiConfig.GET_SHOP_INFO).addParams("shopNumber", getShopNumber())
                .build().execute(new ResponseCallback<ShopinforBean>(getActivity()) {

            @Override
            public void onResponse(ShopinforBean response, int id) {
                if (response.isSuccess()) {
                    ivMineAddress.setText(response.getData().getShopName());
                    Glide.with(getActivity()).load(ApiConfig.IMG_URL + response.getData().getAddressLink())
                            .into(ivMineHead);

                } else {
                    ToastUtil.showToast(response.getMsg());
                }
            }
        });
    }

    @OnClick({R.id.rl_mine_jiaoban, R.id.rl_mine_mendianxinxi, R.id.rl_mine_xiugaimima, R.id.rl_mine_dayinguanli, R.id.rl_mine_shouyinshezhi, R.id.rl_mine_saomadiancanshezhi, R.id.rl_mine_douguo, R.id.rl_mine_guanyu, R.id.rl_mine_phone, R.id.btn_mine_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_mendianxinxi:
                //门店信息
                Intent mendianIntent = new Intent(getActivity(), ShopInformationActivity.class);
                startActivityForResult(mendianIntent, 1);
                break;
            case R.id.rl_mine_xiugaimima:
                //修改密码
                Intent replacePasswordIntent = new Intent(getActivity(), ReplacePasswordActivity.class);
                startActivity(replacePasswordIntent);
                break;
            case R.id.rl_mine_dayinguanli:
                //打印管理
                Intent bluePrinterIntent = new Intent(getActivity(), PrinterActivity.class);
                startActivity(bluePrinterIntent);
                break;
            case R.id.rl_mine_shouyinshezhi:
                //收银设置
                Intent cashierIntent = new Intent(getActivity(), CashierActivity.class);
                startActivity(cashierIntent);
                break;
            case R.id.rl_mine_saomadiancanshezhi:
                //扫码点餐设置
                Intent saomadiancanIntent = new Intent(getActivity(), SaomaFoodsSetActivity.class);
                startActivity(saomadiancanIntent);
                break;
            case R.id.rl_mine_jiaoban:
                //交班打印
                Intent jiaobanIntent = new Intent(getActivity(), HandoverActivity.class);
                startActivity(jiaobanIntent);
                break;
            case R.id.rl_mine_douguo:
                //豆果收银授权
//                MscSpeechUtils.speech("豆果收银授权", getActivity());
                Intent authorizeIntent = new Intent(getActivity(), AuthorizeActivity.class);
                startActivity(authorizeIntent);
                break;
            case R.id.rl_mine_guanyu:
                //关于我们
                Intent aboutweIntent = new Intent(getActivity(), AboutWeActivity.class);
                startActivity(aboutweIntent);
                break;
            case R.id.rl_mine_phone:
                //客服电话
                break;
            case R.id.btn_mine_logout:
                //注销
                showLogoutDialog();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1:
                if (data.getBooleanExtra("flag",false)){
                    getShopinfor();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 退出登录时 弹框询问
     */
    private void showLogoutDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.module_dialog_text, null);
        Display display = getActivity().getWindowManager().getDefaultDisplay();

        int width = display.getWidth();
        int height = display.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width * 4 / 5, height * 2 / 7);
        dialog = new Dialog(getActivity(), R.style.CommonDialog);
        dialog.addContentView(view, params);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        TextView tvLogoutDialogCancle = view.findViewById(R.id.tv_logout_dialog_cancle);
        TextView tvLogoutDialogYes = view.findViewById(R.id.tv_logout_dialog_yes);

        tvLogoutDialogCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tvLogoutDialogYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.logout();
//                goLogout();

                startActivity(new Intent(getContext(), LoginActivity.class));
                BaseApplication.getInstance().removeToTop();
                dialog.dismiss();
            }
        });

    }

    /**
     * 注销接口  TODO 回到登录界面
     */
    private void goLogout() {
        showLoadingDialog();
        OkHttpUtils.post().url(ApiConfig.LOGOUT).addParams("", "").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }


    //=================================================================================================================
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
