package com.hongsou.douguoshouyin.activity.mine;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.BluetoothListAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.BluetoothBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.LogUtil;
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
public class BluetoothActivity extends BaseActivity {


    @BindView(R.id.top_bar)
    CommonTopBar mTopBar;
    @BindView(R.id.bluetooth_listview)
    ListView mBluetoothListview;
    @BindView(R.id.tv_mine_printer_sousuoing)
    TextView mTvMinePrinterSousuoing;
    @BindView(R.id.tv_mine_printer_sousuo)
    TextView mTvMinePrinterSousuo;

    private BluetoothAdapter bluetoothAdapter;
    //蓝牙数据适配器
    private BluetoothListAdapter mListAdapter;
    private List<BluetoothBean> blueToothModels = new ArrayList<>();
    private AsyncTask asyncTask;

    private boolean isRegistReceiver = false;
    private String name;
    private String type;

    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_bluetooth;
    }

    @Override
    protected void init() {
        if (getIntent().hasExtra("name")) {
            name = getIntent().getStringExtra("name");
            type = getIntent().getStringExtra("type");
        }
        //打开蓝牙、进行搜索
        initListView();
        startBluetooth();
        registerReceiver();
    }

    private void initListView() {
        setIconFont(new TextView[]{mTvMinePrinterSousuo});
        if (blueToothModels == null) {
            blueToothModels = new ArrayList<>();
        }
        mTvMinePrinterSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 搜索蓝牙
                scanLeDevice();
                mTvMinePrinterSousuo.setVisibility(View.GONE);
                mTvMinePrinterSousuoing.setVisibility(View.VISIBLE);
            }
        });
        mTvMinePrinterSousuoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 停止搜索蓝牙
                mTvMinePrinterSousuo.setVisibility(View.VISIBLE);
                mTvMinePrinterSousuoing.setVisibility(View.GONE);
                if (bluetoothAdapter != null) {
                    bluetoothAdapter.cancelDiscovery();
                }
            }
        });
        mListAdapter = new BluetoothListAdapter(this, blueToothModels);
        mBluetoothListview.setAdapter(mListAdapter);
        mBluetoothListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //停止搜索
                if (bluetoothAdapter != null) {
                    bluetoothAdapter.cancelDiscovery();
                }
                BluetoothBean bluetoothBean = blueToothModels.get(i);
                Intent intent = new Intent(BluetoothActivity.this, PrinterActivity.class);
                intent.putExtra("address", bluetoothBean.getAddress());
                intent.putExtra("type", type);
                intent.putExtra("name", TextUtils.isEmpty(name) ? bluetoothBean.getName() : name);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    /**
     * @param bluetoothBean 打印机数据
     * @desc 添加打印机
     * @anthor lpc
     * @date: 2018/8/6
     */
    private void submitPrinter(BluetoothBean bluetoothBean) {
        HttpFactory.post().url(ApiConfig.INSERT_SHOP_PRINT)
                .addParams("shopNumber", getShopNumber())
                .addParams("printWidth", "58mm")
                .addParams("printBrand", "app")
                .addParams("printClassifyName", type)
                .addParams("printName", bluetoothBean.getName())
                .addParams("printAddress", bluetoothBean.getAddress())
                .build()
                .execute(new ResponseCallback<RootBean>(this) {
                    @Override
                    public void onResponse(RootBean response, int id) {
                        if (response.isSuccess()) {
                            Intent intent = new Intent(BluetoothActivity.this, PrinterActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
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
     * @desc 打开蓝牙并搜索
     * @anthor lpc
     * @date: 2018/8/3
     */
    private void getBluetooth() {
        if (!bluetoothAdapter.isEnabled()) {
            //需要打开蓝牙
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 100);
        } else {
            // 搜索蓝牙设备
            scanLeDevice();
        }
    }

    /**
     * @desc 打开蓝牙的回调
     * @anthor lpc
     * @date: 2018/8/3
     */
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
     * @desc 搜索蓝牙设备
     * @anthor lpc
     * @date: 2018/8/3
     */
    private void scanLeDevice() {
        if (bluetoothAdapter != null) {
            bluetoothAdapter.startDiscovery();
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
        //设备连接状态改变
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        //蓝牙设备状态改变
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        // 扫描结束的广播
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mBluetoothReceiver, filter);
        isRegistReceiver = true;
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
                            if (blueToothModel.getAddress().equals(address)) {
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
                            mListAdapter.notifyDataSetChanged();
                        }
                    }
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                // 蓝牙搜索完毕
                mTvMinePrinterSousuo.setVisibility(View.VISIBLE);
                mTvMinePrinterSousuoing.setVisibility(View.GONE);
                if (mListAdapter.getCount() == 0) {
                    Toast.makeText(context, "未搜索到蓝牙设备", Toast.LENGTH_SHORT).show();
                }
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                LogUtil.e("BroadcastReceiver", "111111111111");
            } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                LogUtil.e("BroadcastReceiver", "2222222222222222222");
            }
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
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

    @Override
    public void initData() {
    }

}