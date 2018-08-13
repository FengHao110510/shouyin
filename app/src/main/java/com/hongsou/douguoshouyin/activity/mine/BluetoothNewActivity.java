package com.hongsou.douguoshouyin.activity.mine;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gprinter.service.GpPrintService;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.BluetoothListAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BluetoothBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/8/3
 * <p>
 * @desc 蓝牙列表页面
 */
public class BluetoothNewActivity extends BaseActivity {


    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;
    @BindView(R.id.bluetooth_listview)
    ListView mBluetoothListview;

    public static final int REQUEST_ENABLE_BT = 2;

    private BluetoothAdapter mBluetoothAdapter;
    /**
     * listView 的 Adapter
     */
    private BluetoothListAdapter mAdapter;
    private List<BluetoothBean> blueToothModels = new ArrayList<>();

    private String name;
    private String type;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_bluetooth;
    }

    @Override
    protected void init() {
        if (getIntent().hasExtra("name")){
            name = getIntent().getStringExtra("name");
            type = getIntent().getStringExtra("type");
        }
        //打开蓝牙、进行搜索
        getBluetoothDevice();
        initListView();
        registerReceiver();
    }

    private void initListView() {
        if (blueToothModels == null) {
            blueToothModels = new ArrayList<>();
        }
        mTopBar.setRightViewClickListener(new CommonTopBar.ClickCallBack() {
            @Override
            public void onClick(View v) {
                scanBluetoothDevice();
            }
        });

        mAdapter = new BluetoothListAdapter(this, blueToothModels);
        mBluetoothListview.setAdapter(mAdapter);
        mBluetoothListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BluetoothBean bluetoothBean = blueToothModels.get(i);
                Intent intent = new Intent(BluetoothNewActivity.this, PrinterNewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(GpPrintService.BLUETOOT_ADDR, bluetoothBean.getAddress());
                bundle.putString(GpPrintService.PORT_TYPE, type);
                bundle.putString(GpPrintService.USB_DEVICE_NAME,  TextUtils.isEmpty(name) ? bluetoothBean.getName() : name);
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
//                submitPrinter(blueToothModels.get(i));
            }
        });
    }

    /**
     * @desc 添加打印机
     * @anthor lpc
     * @date: 2018/8/6
     * @param bluetoothBean 打印机数据
     */
    private void submitPrinter(BluetoothBean bluetoothBean) {
        HttpFactory.post().url(ApiConfig.INSERT_SHOP_PRINT)
                .addParams("shopNumber", getShopNumber())
                .addParams("printWidth", "58mm")
                .addParams("printBrand", "")
                .addParams("printClassifyName", type)
                .addParams("printName", TextUtils.isEmpty(name) ? bluetoothBean.getName() : name)
                .addParams("printAddress", bluetoothBean.getAddress())
                .build()
                .execute(new ResponseCallback<RootBean>(this) {
                    @Override
                    public void onResponse(RootBean response, int id) {
                        if (response.isSuccess()) {
                            Intent intent = new Intent(BluetoothNewActivity.this, PrinterNewActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    /**
     * @desc 打开蓝牙并搜索
     * @anthor lpc
     * @date: 2018/8/10
     */
    public void getBluetoothDevice() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "蓝牙不支持该设备", Toast.LENGTH_SHORT).show();
        } else {
            RxPermissions rxPermissions = new RxPermissions(this);
            if (rxPermissions.isGranted(Manifest.permission.BLUETOOTH)) {
                getBluetooth();
            }else {
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                scanBluetoothDevice();
            } else {
                // 蓝牙没有开启
                Toast.makeText(this, "蓝牙没有开启", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @desc 打开蓝牙并搜索
     * @anthor lpc
     * @date: 2018/8/3
     */
    private void getBluetooth() {
        // 蓝牙是否开启
        if (!mBluetoothAdapter.isEnabled()) {
            // 需要打开蓝牙
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        } else {
            // 搜索蓝牙
            scanBluetoothDevice();
        }
    }

    /**
     * @desc 搜索蓝牙设备
     * @anthor lpc
     * @date: 2018/8/3
     */
    private void scanBluetoothDevice() {
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.startDiscovery();
        }
    }

    /**
     * @desc 注册蓝牙扫描广播
     * @anthor lpc
     * @date: 2018/8/3
     */
    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        //发现设备
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        //蓝牙设备状态改变
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        // 扫面结束的广播
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mBluetoothReceiver, filter);
    }

    /**
     * @desc 蓝牙扫描广播
     * @anthor lpc
     * @date: 2018/8/10
     */
    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //每扫描到一个设备，系统都会发送此广播。
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //获取蓝牙设备
                BluetoothDevice scanDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (scanDevice != null || scanDevice.getName() != null) {
                    // 蓝牙设备名称
                    String name = scanDevice.getName();
                    // 蓝牙地址
                    String address = scanDevice.getAddress();
                    // 蓝牙类型
                    int type = scanDevice.getBluetoothClass().getDeviceClass();
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(address)) {
                        boolean add = true;
                        // 判断之前是否已经搜索出该设备
                        for (BluetoothBean blueToothModel : blueToothModels) {
                            if (blueToothModel.getAddress().equals(address)){
                                add = false;
                                break;
                            }
                        }
                        if (add) {
                            BluetoothBean bean = new BluetoothBean();
                            bean.setAddress(address);
                            bean.setName(name);
                            bean.setType(type);
                            blueToothModels.add(bean);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                if (mAdapter.getCount() == 0) {
                    Toast.makeText(context, "未搜索到蓝牙设备", Toast.LENGTH_SHORT).show();
                }
            }
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.cancelDiscovery();
        }
        if (mBluetoothReceiver != null) {
            unregisterReceiver(mBluetoothReceiver);
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