package com.hongsou.douguoshouyin.activity.mine;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.BluetoothListAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.javabean.BluetoothBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.LogUtil;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.tool.bluetooth.BlueToothManager;
import com.hongsou.douguoshouyin.tool.bluetooth.BluetoothPrinterUtil;
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
 * 打印设置页面
 */
public class PrinterActivity extends BaseActivity {

    @BindView(R.id.tv_mine_printer_sousuoing)
    TextView tvMinePrinterSousuoing;
    @BindView(R.id.tv_mine_printer_sousuo)
    TextView tvMinePrinterSousuo;
    @BindView(R.id.pro_mine_printer_sousuo)
    ProgressBar proMinePrinterSousuo;
    @BindView(R.id.rv_mine_printer_xinxi)
    RecyclerView rvMinePrinterXinxi;
    @BindView(R.id.blue_tooth_switch)
    SwitchCompat mBlueToothSwitch;
    @BindView(R.id.bluetooth_listview)
    ListView mBluetoothListview;
    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;

    //蓝牙数据适配器
    private BluetoothListAdapter mListAdapter;
    private BluetoothAdapter bluetoothAdapter;
    private List<String> bluesName = new ArrayList<>();
    private List<String> bluesAddress = new ArrayList<>();
    private List<Integer> typeInt = new ArrayList<>();
    private List<BluetoothBean> blueToothModels = new ArrayList<>();
    private AsyncTask asyncTask;

    private boolean isRegistReceiver = false;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_printer;
    }

    @Override
    protected void init() {
        initView();
        initBluetooth();
        initListViewItemClick();
        registReceiver();
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
                       .setType(BluetoothPrinterUtil.Print.BACK_MONEY)
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

    @Override
    public void initData() {
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
     * onItemClick
     */
    private void initListViewItemClick() {
        mBluetoothListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clickAddress = bluesAddress.get(i);
                final String clickName = bluesName.get(i);
                setStatusData(clickName, "连接中...");
                //停止搜索
                bluetoothAdapter.cancelDiscovery();
                asyncTask = BlueToothManager.getInstance().connectBluetooth(bluetoothAdapter, clickAddress, new BlueToothManager.ConnectSucceedCallBack() {
                    @Override
                    public void succeedCallBack(String address) {
                        setStatusData(clickName, "连接成功");
                    }

                    @Override
                    public void failureCallBack(String address) {
                        setStatusData(clickName, "连接失败");
                    }

                });
                Global.getSpGlobalUtil().setBluetoothAddress(clickAddress);
                Global.getSpGlobalUtil().setBluetoothName(clickName);

            }
        });
    }

    @OnClick({R.id.tv_mine_printer_sousuo})
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
            default:
                break;
        }
    }


    /**
     * 启动蓝牙
     */
    private void startBluetooth() {
        Log.i("onCheckedChanged", "初始化开启蓝牙设备");

        new RxPermissions(this).requestEach(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            getBluetooth();
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            ToastUtil.showToast("请打开相关权限，否则将影响使用");
                        }
                    }
                });
    }

    /**
     * 蓝牙设置
     */
    private void getBluetooth() {
        if (!bluetoothAdapter.isEnabled()) {
            //需要打开蓝牙
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 100);
        } else {
            scanLeDevice();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == -1) {
                ToastUtil.showToast("蓝牙已打开");
                scanLeDevice();
            } else if (requestCode == 0) {
                ToastUtil.showToast("蓝牙未打开");
            }
        }
    }

    /**
     * 搜索设备
     */
    private void scanLeDevice() {
        if (bluetoothAdapter != null) {
            bluetoothAdapter.startDiscovery();
        }
    }

    /**
     * 注册广播
     */
    private void registReceiver() {
        IntentFilter filter = new IntentFilter();
        //发现设备
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        //设备连接状态改变
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        //蓝牙设备状态改变
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBluetoothReceiver, filter);
        isRegistReceiver = true;
    }

    /**
     * 设置数据
     */
    private void setBluetoothAdapter(List<String> names) {
        blueToothModels.clear();
        for (int i = 0; i < names.size(); i++) {
            BluetoothBean blueToothModel = new BluetoothBean();
            blueToothModel.setName(names.get(i));
            blueToothModel.setStatus("连接");
            blueToothModel.setType(typeInt.get(i));
            blueToothModels.add(blueToothModel);
        }
        if (mListAdapter == null) {
            mListAdapter = new BluetoothListAdapter(this, blueToothModels);
            mBluetoothListview.setAdapter(mListAdapter);
        } else {
            mListAdapter.setDataName(blueToothModels);
            mListAdapter.notifyDataSetChanged();
        }
        BluetoothSocket socket = BaseApplication.getInstance().socket;
        if (socket == null) {
            autoConnectBlue();
        }

    }

    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.e("BroadcastReceiver", "------->" + action);
            //每扫描到一个设备，系统都会发送此广播。
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //获取蓝牙设备
                BluetoothDevice scanDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                LogUtil.e("BroadcastReceiver", "name=" + scanDevice.getName() + "address=" + scanDevice.getAddress());
                if (scanDevice != null || scanDevice.getName() != null) {
                    // 蓝牙设备名称
                    String name = scanDevice.getName();
                    String address = scanDevice.getAddress();
                    int type1 = scanDevice.getBluetoothClass().getDeviceClass();
                    if (name != null) {
                        if (!bluesName.contains(name)) {
                            bluesName.add(name);
                            bluesAddress.add(address);
                            typeInt.add(type1);
                            setBluetoothAdapter(bluesName);
                        }
                    }

                }

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                LogUtil.e("BroadcastReceiver", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                LogUtil.e("BroadcastReceiver", "111111111111");
            } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                LogUtil.e("BroadcastReceiver", "2222222222222222222");
            }
        }

    };

    /**
     * 自动连接蓝牙
     */
    private void autoConnectBlue() {
        String address = Global.getSpGlobalUtil().getBluetoothAddress();
        final String name = Global.getSpGlobalUtil().getBluetoothName();
        if (!TextUtils.isEmpty(address) && !TextUtils.isEmpty(name)) {
            asyncTask = BlueToothManager.getInstance().connectBluetooth(bluetoothAdapter, address,
                    new BlueToothManager.ConnectSucceedCallBack() {
                        @Override
                        public void succeedCallBack(String address) {
                            setStatusData(name, "连接成功");
                        }

                        @Override
                        public void failureCallBack(String address) {
                            setStatusData(name, "连接失败");
                        }
                    });
        }
    }


    /**
     * 设置状态数据
     */
    private void setStatusData(String targetAddressName, String status) {
        mListAdapter.setStatus(targetAddressName, status);
        mListAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
        super.onDestroy();
        if (mBluetoothReceiver != null && isRegistReceiver) {
            unregisterReceiver(mBluetoothReceiver);
            isRegistReceiver = false;
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