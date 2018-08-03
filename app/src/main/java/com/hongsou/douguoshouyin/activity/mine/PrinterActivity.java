package com.hongsou.douguoshouyin.activity.mine;

import android.Manifest;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
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
import com.hongsou.douguoshouyin.javabean.BluetoothBean;
import com.hongsou.douguoshouyin.javabean.PrinterBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.LogUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.tool.bluetooth.BlueToothManager;
import com.hongsou.douguoshouyin.views.CommonTopBar;
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

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_printer;
    }

    @Override
    protected void init() {
        initView();
        initRecycleView();
        initBluetooth();
    }


    @Override
    public void initView() {
        setIconFont(new TextView[]{tvMinePrinterSousuo});

        mTopBar.setRightViewClickListener(new CommonTopBar.ClickCallBack() {
            @Override
            public void onClick(View v) {
//               BluetoothPrinterUtil printerUtil = new BluetoothPrinterUtil.Builder()
//                       .setContent("\n")
//                       .setCount(1)
//                       .setType(BluetoothPrinterUtil.Print.BACK_MONEY)
//                       .build();
//                printerUtil.startPrint();
                Intent intent = new Intent(PrinterActivity.this, BluetoothActivity.class);
                startActivityForResult(intent, 99);
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
        rvMinePrinterXinxi.setLayoutManager(new LinearLayoutManager(this));
        if (mPrinterBeans == null) {
            mPrinterBeans = new ArrayList<>();
        }
        mAdapter = new PrinterAdapter(mPrinterBeans);
        rvMinePrinterXinxi.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                final PrinterBean printerBean = mPrinterBeans.get(position);
                String address = printerBean.getAddress();
                printerBean.setStatus("连接中...");
                mAdapter.notifyItemChanged(position);
                asyncTask = BlueToothManager.getInstance().connectBluetooth(bluetoothAdapter, address,
                        new BlueToothManager.ConnectSucceedCallBack() {
                            @Override
                            public void succeedCallBack(String address) {
                                printerBean.setStatus("连接成功");
                                mAdapter.notifyItemChanged(position);
                            }

                            @Override
                            public void failureCallBack(String address) {
                                printerBean.setStatus("连接失败");
                                mAdapter.notifyItemChanged(position);
                            }

                        });
                Global.getSpGlobalUtil().setBluetoothAddress(address);
                Global.getSpGlobalUtil().setBluetoothName(printerBean.getPrintName());
            }
        });
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
        dialog.setContentView(view, params);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();

        // 初始化界面
        initStandardView(view, dialog);
    }

    private void initStandardView(View view, Dialog dialog) {
        EditText et_dialog_edit_content = (EditText) view.findViewById(R.id.et_dialog_edit_content);
        TextView tv_edit_icon = (TextView) view.findViewById(R.id.tv_edit_icon);
        LinearLayout ll_printer_pay = (LinearLayout)  view.findViewById(R.id.ll_printer_pay);
        LinearLayout ll_printer_kitchen = (LinearLayout)  view.findViewById(R.id.ll_printer_kitchen);
        TextView tv_printer_pay_icon = (TextView)  view.findViewById(R.id.tv_printer_pay_icon);
        TextView  tv_printer_kitchen_icon = (TextView)  view.findViewById(R.id.tv_printer_kitchen_icon);
        TextView tv_dialog_cancel = (TextView)  view.findViewById(R.id.tv_dialog_cancel);
        TextView tv_dialog_submit = (TextView)  view.findViewById(R.id.tv_dialog_submit);
        setIconFont(new TextView[]{tv_printer_kitchen_icon, tv_printer_pay_icon, tv_edit_icon});
        // 收银打印
        ll_printer_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // 后厨打印
        ll_printer_kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        } else if (requestCode == 99 && resultCode == 120) {
            BluetoothBean bean = (BluetoothBean) data.getSerializableExtra("bluetooth");
            if (bean != null) {
                PrinterBean printerBean = new PrinterBean();
                printerBean.setStatus("连接");
                printerBean.setAddress(bean.getAddress());
                printerBean.setPrintName(bean.getName());
                printerBean.setPrintClassifyName(bean.getTypeStr());
                mPrinterBeans.add(printerBean);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 自动连接蓝牙
     */
    private void autoConnectBlue() {
        String address = Global.getSpGlobalUtil().getBluetoothAddress();
        final String name = Global.getSpGlobalUtil().getBluetoothName();
        int position = -1;
        for (int i = 0; i < mPrinterBeans.size(); i++) {
            if (mPrinterBeans.get(i).getAddress().equals(address)) {
                position = i;
                break;
            }
        }
        if (!TextUtils.isEmpty(address) && !TextUtils.isEmpty(name)) {
            final int finalPosition = position;
            asyncTask = BlueToothManager.getInstance().connectBluetooth(bluetoothAdapter, address,
                    new BlueToothManager.ConnectSucceedCallBack() {
                        @Override
                        public void succeedCallBack(String address) {
                            if (finalPosition >= 0) {
                                mPrinterBeans.get(finalPosition).setStatus("已连接");
                                mAdapter.notifyItemChanged(finalPosition);
                            }
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

    @Override
    public void initData() {
    }
}