package com.hongsou.douguoshouyin.tool.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.tool.ToastUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

/**
 * Created by zlq on 2018/1/10.
 */

public class BlueToothManager {
    private static BlueToothManager blueToothManager;
    public static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private AsyncTask asyncTask;
    private ConnectSucceedCallBack connectSucceedCallBack;

    BluetoothSocket socket = null;

    public static BlueToothManager getInstance() {
        if (blueToothManager == null) {
            blueToothManager = new BlueToothManager();
        }
        return blueToothManager;
    }

    /**
     * 连接蓝牙
     *
     * @param bluetoothAdapter
     * @param macStr
     */
    @SuppressLint("StaticFieldLeak")
    private void startAsyncTask(final BluetoothAdapter bluetoothAdapter, final String name, final String macStr) {
        asyncTask = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String re = userBlue(bluetoothAdapter, macStr);
                return re;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (TextUtils.isEmpty(s)) {
                    connectSucceedCallBack.failureCallBack(name, macStr);
                } else {
                    connectSucceedCallBack.succeedCallBack(name, macStr);
                }
            }
        }.execute();
    }

    /**
     * 蓝牙客户端
     */
    public String userBlue(BluetoothAdapter bluetoothAdapter, String macStr) {
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macStr);
        try {
            socket = device.createRfcommSocketToServiceRecord(UUID.fromString(SPP_UUID));
            Log.e("lpc", "userBlue: " + socket);
            if (socket == null) {
                ToastUtil.showToast("当前蓝牙处于关闭状态");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //建立连接之前先配对
        if (device.getBondState() == BluetoothDevice.BOND_NONE) {
            Method creMethod = null;
            try {
                creMethod = BluetoothDevice.class.getMethod("createBond");
                creMethod.invoke(device);
            } catch (Exception e1) {
                Log.i("bluetooth_status", "无法配对");
                e1.printStackTrace();
            }
            Log.i("bluetooth_status", "配对成功");
        }
        List<BluetoothSocket> socketArray = BaseApplication.getInstance().socketArray;
        try {
            socket.connect();
            BaseApplication.getInstance().socket = socket;
            if (!checkContains(socketArray, socket)) {
                socketArray.add(socket);
            }
            return "配对成功";
        } catch (IOException e1) {
            Log.i("bluetooth_status", "连接失败");
            e1.printStackTrace();
            try {
                socket.close();
                if (!checkContains(socketArray, socket)) {
                    socketArray.remove(socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @desc 检测之前是否已经连接成功过
     * @anthor lpc
     * @date: 2018/8/16
     * @param socketArray 已连接的蓝牙列表
     * @param socket 当前正在连接的蓝牙
     * @return 是否包含当前蓝牙
     */
    public static boolean checkContains(List<BluetoothSocket> socketArray, BluetoothSocket socket) {
        for (BluetoothSocket bluetoothSocket : socketArray) {
            if (bluetoothSocket.getRemoteDevice().getAddress().equals(socket.getRemoteDevice().getAddress())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 连接蓝牙
     */
    public AsyncTask connectBluetooth(BluetoothAdapter bluetoothAdapter, String macStr, ConnectSucceedCallBack connectSucceedCallBack) {
        this.connectSucceedCallBack = connectSucceedCallBack;
        startAsyncTask(bluetoothAdapter, "", macStr);
        return asyncTask;
    }

    /**
     * 连接蓝牙
     */
    public AsyncTask connectBluetooth(BluetoothAdapter bluetoothAdapter, String name, String macStr, ConnectSucceedCallBack connectSucceedCallBack) {
        this.connectSucceedCallBack = connectSucceedCallBack;
        startAsyncTask(bluetoothAdapter, name, macStr);
        return asyncTask;
    }

    public interface ConnectSucceedCallBack {
        void succeedCallBack(String name, String address);

        void failureCallBack(String name, String address);
    }

    /**
     * 自动连接
     */
    public AsyncTask autoConnectBlue(String address, final String name) {
        Log.i("bluetooth_status", "开始自动连接");
        if (!TextUtils.isEmpty(address) && !TextUtils.isEmpty(name)) {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter.isEnabled()) {
                asyncTask = BlueToothManager.getInstance().connectBluetooth(bluetoothAdapter, name, address, new BlueToothManager.ConnectSucceedCallBack() {
                    @Override
                    public void succeedCallBack(String name, String address) {
                        Log.i("bluetooth_status", "自动连接成功" + name);
//                        ToastUtil.showToast(name + "自动连接成功");
                    }

                    @Override
                    public void failureCallBack(String name, String address) {
                        Log.i("bluetooth_status", "自动连接失败");
                        ToastUtil.showToast(name + "自动连接失败");
                    }
                });
            } else {

            }
        }
        return asyncTask;
    }
}
