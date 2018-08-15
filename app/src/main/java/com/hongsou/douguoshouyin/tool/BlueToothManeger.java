package com.hongsou.douguoshouyin.tool;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.hongsou.douguoshouyin.base.BaseApplication;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by zlq on 2018/1/10.
 */

public class BlueToothManeger {
    private static BlueToothManeger blueToothManeger;
    private final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private AsyncTask asyncTask;
    private ConnectSucceedCallBack connectSucceedCallBack;

    public static BlueToothManeger instance() {
        if (blueToothManeger == null) {
            blueToothManeger = new BlueToothManeger();
        }
        return blueToothManeger;
    }

    BluetoothSocket socket = null;


    /**
     * 连接蓝牙
     *
     * @param bluetoothAdapter
     * @param macStr
     */
    private void startAsyncTask(final BluetoothAdapter bluetoothAdapter, final String macStr) {
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
                    connectSucceedCallBack.failureCallBack(macStr);
                } else {
                    connectSucceedCallBack.succeedCallBack(macStr);
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

//        bluetoothAdapter.cancelDiscovery();
        try {
            socket.connect();
            BaseApplication.getInstance().socket = socket;
            //取消搜索

            return "配对成功";
        } catch (IOException e1) {
            Log.i("bluetooth_status", "连接失败");
            e1.printStackTrace();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 连接蓝牙
     */

    public AsyncTask connectBluetooth(BluetoothAdapter bluetoothAdapter, String macStr, ConnectSucceedCallBack connectSucceedCallBack) {
        this.connectSucceedCallBack = connectSucceedCallBack;
        startAsyncTask(bluetoothAdapter, macStr);
        return asyncTask;
    }

    public interface ConnectSucceedCallBack {
        void succeedCallBack(String address);

        void failureCallBack(String address);
    }

    /**
     * 自动连接
     */

    public AsyncTask autoNonnectBlue(String address, final String name) {
        Log.i("bluetooth_status", "开始自动连接");

        if (!TextUtils.isEmpty(address) && !TextUtils.isEmpty(name)) {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if (bluetoothAdapter.isEnabled()) {
                asyncTask = BlueToothManeger.instance().connectBluetooth(bluetoothAdapter, address, new BlueToothManeger.ConnectSucceedCallBack() {
                    @Override
                    public void succeedCallBack(String address) {
                        Log.i("bluetooth_status", "自动连接成功" + name);
                        ToastUtil.showToast(name + "自动连接成功");
                    }

                    @Override
                    public void failureCallBack(String address) {
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
