package com.hongsou.douguoshouyin.activity.mine;

import android.Manifest;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.PrinterAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.javabean.PrinterBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.LogUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.tool.bluetooth.BlueToothManager;
import com.hongsou.douguoshouyin.tool.bluetooth.BluetoothPrinterUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;
import com.hongsou.greendao.gen.PrinterBeanDao;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/8/3
 * <p>
 * @desc 打印设置页面
 */
public class PrinterActivity extends BaseActivity {

    @BindView(R.id.tv_mine_printer_sousuoing)
    TextView tvMinePrinterSousuoing;
    @BindView(R.id.tv_mine_printer_sousuo)
    TextView tvMinePrinterSousuo;
    @BindView(R.id.rv_mine_printer_xinxi)
    RecyclerView rvMinePrinterXinxi;
    @BindView(R.id.blue_tooth_switch)
    SwitchCompat mBlueToothSwitch;
    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;
    @BindView(R.id.btn_add_printer)
    Button mBtnAddPrinter;

    private PrinterAdapter mAdapter;
    private List<PrinterBean> mPrinterBeans = new ArrayList<>();

    //蓝牙数据适配器
    private BluetoothAdapter bluetoothAdapter;
    private AsyncTask asyncTask;

    private PrinterBeanDao mPrinterBeanDao;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_printer;
    }

    @Override
    protected void init() {
        initView();
        initRecycleView();
        initBluetooth();
        autoConnectBlue();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvMinePrinterSousuo});

        mTopBar.setRightViewClickListener(new CommonTopBar.ClickCallBack() {
            @Override
            public void onClick(View v) {
                BluetoothPrinterUtil printerUtil = new BluetoothPrinterUtil.Builder()
                        .setContent("\n")
                        .setCount(1)
                        .setType(BluetoothPrinterUtil.Print.TEST)
                        .build();
                printerUtil.startPrint();

            }
        });

        //初始化蓝牙开关
        mBlueToothSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    //打开蓝牙、进行搜索
                    startBluetooth();
                } else {
                    LogUtil.e("onCheckedChanged", "关闭蓝牙设备");
                    //关闭蓝牙
                    if (bluetoothAdapter != null) {
                        bluetoothAdapter.disable();
                    }
                }
            }
        });
    }

    /**
     * 初始化蓝牙设备
     */
    private void initBluetooth() {
        //获取蓝牙适配器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            ToastUtil.showToast("不支持蓝牙");
            return;
        }
        mBlueToothSwitch.setChecked(bluetoothAdapter.isEnabled());
    }

    /**
     * @desc 初始化列表
     * @anthor lpc
     * @date: 2018/8/3
     */
    private void initRecycleView() {
        mPrinterBeanDao = BaseApplication.getApplication().getDaoSession().getPrinterBeanDao();
        rvMinePrinterXinxi.setLayoutManager(new LinearLayoutManager(this));
        if (mPrinterBeans == null) {
            mPrinterBeans = new ArrayList<>();
        }
        mPrinterBeans.addAll(mPrinterBeanDao.loadAll());
        mAdapter = new PrinterAdapter(mPrinterBeans);
        rvMinePrinterXinxi.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                final PrinterBean printerBean = mPrinterBeans.get(position);
                String address = printerBean.getPrintAddress();
                if (printerBean.getConnectStatus()) {
                    printerBean.setConnectStatus(false);
                    mAdapter.notifyItemChanged(position);
                    mPrinterBeanDao.insertOrReplace(printerBean);
                    List<BluetoothSocket> socketArray = BaseApplication.getInstance().socketArray;
                    for (BluetoothSocket bluetoothSocket : socketArray) {
                        Log.e(TAG, " address === " + bluetoothSocket.getRemoteDevice().getAddress());
                        if (bluetoothSocket.getRemoteDevice().getAddress().equals(address)) {
                            socketArray.remove(bluetoothSocket);
                            return;
                        }
                    }
                } else {
                    ((TextView) adapter.getViewByPosition(rvMinePrinterXinxi, position, R.id.tv_connect)).setText("连接中");
                    asyncTask = BlueToothManager.getInstance().connectBluetooth(bluetoothAdapter, address,
                            new BlueToothManager.ConnectSucceedCallBack() {
                                @Override
                                public void succeedCallBack(String address) {
                                    printerBean.setConnectStatus(true);
                                    mAdapter.notifyItemChanged(position);
                                    mPrinterBeanDao.insertOrReplace(printerBean);
                                }

                                @Override
                                public void failureCallBack(String address) {
                                    printerBean.setConnectStatus(false);
                                    mAdapter.notifyItemChanged(position);
                                    mPrinterBeanDao.insertOrReplace(printerBean);
                                }

                            });
                }
            }
        });
    }

    @Override
    public void initData() {
//        HttpFactory.get().url(ApiConfig.GET_PRINT_ADDRESS)
//                .addParams("shopNumber", getShopNumber())
//                .build()
//                .execute(new ResponseCallback<RootBean<List<PrinterBean>>>(this) {
//                    @Override
//                    public void onResponse(RootBean<List<PrinterBean>> response, int id) {
//                        if (response.isSuccess()) {
//                            mPrinterBeans.clear();
//                            List<PrinterBean> data = response.getData();
//                            for (PrinterBean datum : data) {
//                                datum.setStatus("连接");
//                                if (datum.getPrintBrand().equals("app")){
//                                    mPrinterBeans.add(datum);
//                                }
//                            }
//                            String address = Global.getSpGlobalUtil().getBluetoothAddress();
//                            String[] split = new String[0];
//                            if (!TextUtils.isEmpty(address)){
//                                split = address.split("@_@");
//                            }
//                            for (int i = 0; i < mPrinterBeans.size(); i++) {
//                                // 判断列表中的地址是否在本地保存（保存的需要自动连接）
//                                if (Arrays.asList(split).contains(mPrinterBeans.get(i).getPrintAddress())) {
//                                    mPrinterBeans.get(i).setStatus("已连接");
//                                }
//                            }
//                            mAdapter.notifyDataSetChanged();
//                        } else {
//                            ToastUtil.showToast(response.getMsg());
//                        }
//                    }
//                });
    }

    @OnClick({R.id.tv_mine_printer_sousuo, R.id.btn_add_printer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_printer_sousuo:
                //点击搜索后 如果没有在扫描设备
                if (!bluetoothAdapter.isDiscovering()) {
                    bluetoothAdapter.startDiscovery();//扫描附近蓝牙设备，然后做接下来的操作，比如扫描附近蓝牙等
                } else {
                    ToastUtil.showToast("正在扫描");
                }
                break;
            case R.id.btn_add_printer:
                //点击搜索后 如果没有在扫描设备
                showPrinterWindow();
                break;
            default:
                break;
        }
    }

    /**
     * @desc 弹窗显示添加打印机
     * @anthor lpc
     * @date: 2018/8/3
     */
    private void showPrinterWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.module_pop_add_printer, null);
        final Dialog dialog = new Dialog(this, R.style.CommonDialog);
        //设置dialog的宽高
        Display display = getWindowManager().getDefaultDisplay();
        int width = (int) (display.getWidth() * 0.8);
        int height = (int) (display.getHeight() * 0.4);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

        // 初始化界面
        initPrinterView(view, dialog);
    }

    private void initPrinterView(View view, final Dialog dialog) {
        final String[] type = {""};
        final EditText etDialogEditContent = (EditText) view.findViewById(R.id.et_dialog_edit_content);
        TextView tvEditIcon = (TextView) view.findViewById(R.id.tv_edit_icon);
        LinearLayout llPrinterPay = (LinearLayout) view.findViewById(R.id.ll_printer_pay);
        LinearLayout llPrinterKitchen = (LinearLayout) view.findViewById(R.id.ll_printer_kitchen);
        final TextView tvPrinterPay = (TextView) view.findViewById(R.id.tv_printer_pay);
        final TextView tvPrinterKitchen = (TextView) view.findViewById(R.id.tv_printer_kitchen);
        final TextView tvPrinterPayIcon = (TextView) view.findViewById(R.id.tv_printer_pay_icon);
        final TextView tvPrinterKitchenIcon = (TextView) view.findViewById(R.id.tv_printer_kitchen_icon);
        TextView tvDialogCancel = (TextView) view.findViewById(R.id.tv_dialog_cancel);
        TextView tvDialogSubmit = (TextView) view.findViewById(R.id.tv_dialog_submit);
        setIconFont(new TextView[]{tvPrinterKitchenIcon, tvPrinterPayIcon, tvEditIcon});
        // 收银打印
        llPrinterPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type[0] = "收银打印";
                tvPrinterKitchenIcon.setTextColor(getResources().getColor(R.color.color_subtitle));
                tvPrinterKitchen.setTextColor(getResources().getColor(R.color.color_subtitle));
                tvPrinterPayIcon.setTextColor(getResources().getColor(R.color.red));
                tvPrinterPay.setTextColor(getResources().getColor(R.color.red));
            }
        });
        // 后厨打印
        llPrinterKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type[0] = "后厨打印";
                tvPrinterPay.setTextColor(getResources().getColor(R.color.color_subtitle));
                tvPrinterPayIcon.setTextColor(getResources().getColor(R.color.color_subtitle));
                tvPrinterKitchenIcon.setTextColor(getResources().getColor(R.color.red));
                tvPrinterKitchen.setTextColor(getResources().getColor(R.color.red));
            }
        });
        tvDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvDialogSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(type[0])) {
                    ToastUtil.showToast("请先选择打印机类型");
                    return;
                }
                Intent intent = new Intent(PrinterActivity.this, BluetoothActivity.class);
                intent.putExtra("name", etDialogEditContent.getText().toString());
                intent.putExtra("type", type[0]);
                startActivityForResult(intent, 99);
                dialog.dismiss();
            }
        });
    }


    /**
     * 启动蓝牙
     */
    private void startBluetooth() {
        //获取蓝牙适配器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            ToastUtil.showToast("不支持蓝牙");
            return;
        }
        LogUtil.e("onCheckedChanged", "初始化开启蓝牙设备");
        RxPermissions rxPermissions = new RxPermissions(this);
        if (rxPermissions.isGranted(Manifest.permission.BLUETOOTH)) {
            getBluetooth();
        } else {
            rxPermissions.requestEach(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) throws Exception {
                            if (permission.granted) {
                                getBluetooth();
                            } else {
                                // 用户拒绝了该权限，并且选中『不再询问』
                                ToastUtil.showToast("请打开蓝牙权限，否则将影响使用");
                            }
                        }
                    });
        }
    }

    /**
     * 蓝牙设置
     */
    private void getBluetooth() {
        if (!bluetoothAdapter.isEnabled()) {
            //需要打开蓝牙
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == -1) {
                ToastUtil.showToast("蓝牙已打开");
            } else if (requestCode == 0) {
                ToastUtil.showToast("蓝牙未打开");
            }
        } else if (requestCode == 99 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String type = data.getStringExtra("type");
            String address = data.getStringExtra("address");
            PrinterBean printerBean = new PrinterBean();
            if (mPrinterBeans.size() > 0) {
                long id = mPrinterBeans.get(mPrinterBeans.size() - 1).getId();
                printerBean.setId(id + 1);
            } else {
                printerBean.setId(1);
            }
            printerBean.setConnectStatus(false);
            printerBean.setPrintAddress(address);
            printerBean.setPrintName(name);
            printerBean.setPrintClassifyName(type);
            mPrinterBeans.add(printerBean);
            mPrinterBeanDao.insertOrReplace(printerBean);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 自动连接蓝牙
     */
    private void autoConnectBlue() {
        String address = Global.getSpGlobalUtil().getBluetoothAddress();
        String[] split = new String[0];
        if (!TextUtils.isEmpty(address)) {
            split = address.split("@_@");
        }
//        final String name = Global.getSpGlobalUtil().getBluetoothName();
//        int position = -1;
//        for (int i = 0; i < mPrinterBeans.size(); i++) {
//            if (Arrays.asList(split).contains(mPrinterBeans.get(i).getAddress())) {
//                position = i;
//            }
//        }
        if (split.length > 0 && !TextUtils.isEmpty(split[0])) {
//            final int finalPosition = position;
            asyncTask = BlueToothManager.getInstance().connectBluetooth(bluetoothAdapter, split[0],
                    new BlueToothManager.ConnectSucceedCallBack() {
                        @Override
                        public void succeedCallBack(String address) {
//                            if (finalPosition >= 0) {
//                                mPrinterBeans.get(finalPosition).setStatus("已连接");
//                                mAdapter.notifyItemChanged(finalPosition);
//                            }
                        }

                        @Override
                        public void failureCallBack(String address) {
                        }
                    });
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
        if (asyncTask != null) {
            asyncTask.cancel(true);
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