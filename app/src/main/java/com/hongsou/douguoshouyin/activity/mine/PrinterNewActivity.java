package com.hongsou.douguoshouyin.activity.mine;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gprinter.command.EscCommand;
import com.gprinter.command.EscCommand.ENABLE;
import com.gprinter.command.EscCommand.FONT;
import com.gprinter.command.EscCommand.JUSTIFICATION;
import com.gprinter.command.GpCom;
import com.gprinter.command.GpUtils;
import com.gprinter.io.GpDevice;
import com.gprinter.io.PortParameters;
import com.gprinter.save.PortParamDataBase;
import com.gprinter.service.GpPrintService;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.adapter.PrinterAdapter;
import com.hongsou.douguoshouyin.base.BaseActivity;
import com.hongsou.douguoshouyin.base.BaseApplication;
import com.hongsou.douguoshouyin.http.ApiConfig;
import com.hongsou.douguoshouyin.http.HttpFactory;
import com.hongsou.douguoshouyin.http.ResponseCallback;
import com.hongsou.douguoshouyin.javabean.PrinterBean;
import com.hongsou.douguoshouyin.javabean.RootBean;
import com.hongsou.douguoshouyin.tool.Global;
import com.hongsou.douguoshouyin.tool.ToastUtil;
import com.hongsou.douguoshouyin.views.CommonTopBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/8/3
 * <p>
 * @desc 打印设置页面
 */
public class PrinterNewActivity extends BaseActivity {

    private static final String TAG = "lpc";

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


    private static final int INTENT_PORT_SETTINGS = 0;
    private PortParameters[] mPortParam = new PortParameters[GpPrintService.MAX_PRINTER_CNT];


    @Override
    public int initLayout() {
        return R.layout.module_activity_mine_printer;
    }

    @Override
    protected void init() {
        initView();
        initRecycleView();
        initPortParam();
        registerBroadcast();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        initData();
    }

    @Override
    public void initView() {
        setIconFont(new TextView[]{tvMinePrinterSousuo});
        mTopBar.setRightViewClickListener(new CommonTopBar.ClickCallBack() {
            @Override
            public void onClick(View v) {
                if (mPrinterBeans.size() > 0) {
                    for (int i = 0; i < mPrinterBeans.size(); i++) {
                        sendReceiptWithResponse(i);
                    }
                }
            }
        });

        //初始化蓝牙开关
        mBlueToothSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {

            }
        });
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
                connectOrDisConnectToDevice(position);
            }
        });
    }

    @Override
    public void initData() {
        HttpFactory.get().url(ApiConfig.GET_PRINT_ADDRESS)
                .addParams("shopNumber", getShopNumber())
                .build()
                .execute(new ResponseCallback<RootBean<List<PrinterBean>>>(this) {
                    @Override
                    public void onResponse(RootBean<List<PrinterBean>> response, int id) {
                        if (response.isSuccess()) {
                            mPrinterBeans.clear();
                            List<PrinterBean> data = response.getData();
                            for (PrinterBean datum : data) {
//                                datum.setStatus("连接");
                                mPrinterBeans.add(datum);
                            }
                            String address = Global.getSpGlobalUtil().getBluetoothAddress();
                            String[] split = new String[0];
                            if (!TextUtils.isEmpty(address)){
                                split = address.split("@_@");
                            }
                            for (int i = 0; i < mPrinterBeans.size(); i++) {
                                // 判断列表中的地址是否在本地保存（保存的需要自动连接）
                                if (Arrays.asList(split).contains(mPrinterBeans.get(i).getPrintAddress())) {
                                    connectBluetooth(i);
                                }
                            }
                            mAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.showToast(response.getMsg());
                        }
                    }
                });
    }

    @OnClick({R.id.tv_mine_printer_sousuo, R.id.btn_add_printer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_printer_sousuo:
                //点击搜索后 如果没有在扫描设备

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
     * @desc 初始化连接状态
     * @anthor lpc
     * @date: 2018/8/10
     */
    private void initPortParam() {
        PortParamDataBase database = new PortParamDataBase(this);
        for (int i = 0; i < GpPrintService.MAX_PRINTER_CNT; i++) {
//            mPortParam[i] = new PortParameters();
            mPortParam[i] = database.queryPortParamDataBase("" + i);
            mPortParam[i].setPortOpenState(getConnectState()[i]);
            Log.e(TAG, "initPortParam: " + mPortParam[i].toString());
            if (!TextUtils.isEmpty(mPortParam[i].getBluetoothAddr())){
                PrinterBean bean = new PrinterBean();
                bean.setPrintAddress(mPortParam[i].getBluetoothAddr());
                bean.setPrintClassifyName(mPortParam[i].getIpAddr());
                bean.setPrintName(mPortParam[i].getUsbDeviceName());
//                bean.setStatus(mPortParam[i].getPortOpenState() ? "断开" : "连接");
                mPrinterBeans.add(bean);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * @desc 获取连接状态
     * @anthor lpc
     * @date: 2018/8/10
     */
    public boolean[] getConnectState() {
        Log.e(TAG, "getConnectState: ");
        boolean[] state = new boolean[GpPrintService.MAX_PRINTER_CNT];
        for (int i = 0; i < GpPrintService.MAX_PRINTER_CNT; i++) {
            state[i] = false;
        }
        for (int i = 0; i < GpPrintService.MAX_PRINTER_CNT; i++) {
            try {
                if (BaseApplication.mGpService.getPrinterConnectStatus(i) == GpDevice.STATE_CONNECTED) {
                    state[i] = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                state[i] = false;
            }
        }
        return state;
    }

    /**
     * @desc 连接状态改变的监听
     * @anthor lpc
     * @date: 2018/8/10
     */
    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(GpCom.ACTION_CONNECT_STATUS);
        this.registerReceiver(printerStatusBroadcastReceiver, filter);
    }

    /**
     * @desc 连接状态改变的监听
     * @anthor lpc
     * @date: 2018/8/10
     */
    private BroadcastReceiver printerStatusBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (GpCom.ACTION_CONNECT_STATUS.equals(intent.getAction())) {
                int type = intent.getIntExtra(GpPrintService.CONNECT_STATUS, 0);
                int id = intent.getIntExtra(GpPrintService.PRINTER_ID, 0);
                Log.e(TAG, "设备id：" + id + "连接状态 " + type);
                if (type == GpDevice.STATE_CONNECTING) {
                    mPortParam[id].setPortOpenState(false);
                    final PrinterBean printerBean = mPrinterBeans.get(id);
//                    printerBean.setStatus("连接中...");
                    mAdapter.notifyItemChanged(id);
                } else if (type == GpDevice.STATE_NONE) {
                    mPortParam[id].setPortOpenState(false);
                    final PrinterBean printerBean = mPrinterBeans.get(id);
//                    printerBean.setStatus("连接");
                    mAdapter.notifyItemChanged(id);
                } else if (type == GpDevice.STATE_VALID_PRINTER) {
                    mPortParam[id].setPortOpenState(true);
                    final PrinterBean printerBean = mPrinterBeans.get(id);
//                    printerBean.setStatus("断开");
                    mAdapter.notifyItemChanged(id);
                } else if (type == GpDevice.STATE_INVALID_PRINTER) {
                    Toast.makeText(context, "请打开打印机", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * @param printerId 打印机id
     * @desc 连接打印机操作
     * @anthor lpc
     * @date: 2018/8/10
     */
    private void connectOrDisConnectToDevice(int printerId) {

        Log.e(TAG, "连接状态" + String.valueOf(mPortParam[printerId].getPortOpenState()));
        Log.e(TAG, "设备id：" + printerId);
        // 判断连接状态，是否已连接
        if (!mPortParam[printerId].getPortOpenState()) {
            // 未连接，执行连接操作
            if (checkPortParameters(mPortParam[printerId])) {
                connectBluetooth(printerId);
            } else {
                Toast.makeText(this, "端口参数错误！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e(TAG, "DisconnectToDevice ");
            mPortParam[printerId].setPortOpenState(false);
            final PrinterBean printerBean = mPrinterBeans.get(printerId);
//            printerBean.setStatus("连接");
            mAdapter.notifyItemChanged(printerId);
            try {
                BaseApplication.mGpService.closePort(printerId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @desc 连接打印机
     * @anthor lpc
     * @date: 2018/8/11
     * @param printerId 打印机id
     */
    private void connectBluetooth(int printerId) {
        int rel = 0;
        try {
            BaseApplication.mGpService.closePort(printerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        switch (mPortParam[printerId].getPortType()) {
            case PortParameters.ETHERNET:
                try {
                    rel = BaseApplication.mGpService.openPort(printerId, mPortParam[printerId].getPortType(),
                            mPortParam[printerId].getIpAddr(), mPortParam[printerId].getPortNumber());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case PortParameters.BLUETOOTH:
                try {
                    rel = BaseApplication.mGpService.openPort(printerId, mPortParam[printerId].getPortType(),
                            mPortParam[printerId].getBluetoothAddr(), 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rel];
        Log.e(TAG, "result :" + String.valueOf(r));
        if (r != GpCom.ERROR_CODE.SUCCESS) {
            if (r == GpCom.ERROR_CODE.DEVICE_ALREADY_OPEN) {
                // 更改为已连接的状态
                mPortParam[printerId].setPortOpenState(true);
                // 刷新列表状态
                final PrinterBean printerBean = mPrinterBeans.get(printerId);
//                printerBean.setStatus("断开");
                mAdapter.notifyItemChanged(printerId);
            } else {
                Toast.makeText(this, GpCom.getErrorText(r), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @param param 端口
     * @return 是否是蓝牙、wifi端口、usb
     * @desc 检测端口参数
     * @anthor lpc
     * @date: 2018/8/10
     */
    private Boolean checkPortParameters(PortParameters param) {
        boolean rel = false;
        int type = param.getPortType();
        if (type == PortParameters.BLUETOOTH) {
            if (!param.getBluetoothAddr().equals("")) {
                rel = true;
            }
        } else if (type == PortParameters.ETHERNET) {
            if ((!param.getIpAddr().equals("")) && (param.getPortNumber() != 0)) {
                rel = true;
            }
        } else if (type == PortParameters.USB) {
            if (!param.getUsbDeviceName().equals("")) {
                rel = true;
            }
        }
        return rel;
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
                Intent intent = new Intent(PrinterNewActivity.this, BluetoothNewActivity.class);
                intent.putExtra("name", etDialogEditContent.getText().toString());
                intent.putExtra("type", type[0]);
                startActivityForResult(intent, INTENT_PORT_SETTINGS);
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_PORT_SETTINGS) {
            if (resultCode == RESULT_OK) {
                int mPrinterId = mPrinterBeans.size();
                Log.e(TAG, "设备id：" + mPrinterId);
                Bundle bundle = data.getExtras();
                PrinterBean bean = new PrinterBean();
                // 连接类型 usb/蓝牙/端口
                mPortParam[mPrinterId].setPortType(PortParameters.BLUETOOTH);
                // 蓝牙地址
                String str = bundle.getString(GpPrintService.BLUETOOT_ADDR);
                mPortParam[mPrinterId].setBluetoothAddr(str);
                bean.setPrintAddress(str);
                Log.d(TAG, "BluetoothAddr " + str);
                // 打印机名字（未设置时，为蓝牙名字）
                String name = bundle.getString(GpPrintService.USB_DEVICE_NAME);
                mPortParam[mPrinterId].setUsbDeviceName(name);
                bean.setPrintName(name);
                Log.d(TAG, "USBDeviceName " + name);
                String type = bundle.getString(GpPrintService.PORT_TYPE);
                mPortParam[mPrinterId].setIpAddr(type);
                bean.setPrintClassifyName(type );
                Log.e(TAG, "打印机类型 " + type);
                // 刷新布局
                mPrinterBeans.add(bean);
                mAdapter.notifyDataSetChanged();
                if (checkPortParameters(mPortParam[mPrinterId])) {
                    PortParamDataBase database = new PortParamDataBase(this);
                    database.deleteDataBase("" + mPrinterId);
                    database.insertPortParam(mPrinterId, mPortParam[mPrinterId]);
                } else {
                    Toast.makeText(this, "端口参数错误！", Toast.LENGTH_SHORT).show();
                }
            } else {

            }
        }
    }


    void sendReceiptWithResponse(int printID) {
        EscCommand esc = new EscCommand();
        esc.addInitializePrinter();
        esc.addPrintAndFeedLines((byte) 3);
        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);// 设置打印居中
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, ENABLE.ON, ENABLE.OFF);// 设置为倍高倍宽
        esc.addText("Sample\n"); // 打印文字
        esc.addPrintAndLineFeed();

		/* 打印文字 */
        esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF);// 取消倍高倍宽
        esc.addSelectJustification(JUSTIFICATION.LEFT);// 设置打印左对齐
        esc.addText("Print text\n"); // 打印文字
        esc.addText("Welcome to use SMARNET printer!\n"); // 打印文字

		/* 绝对位置 具体详细信息请查看GP58编程手册 */
        esc.addText("智汇");
        esc.addSetHorAndVerMotionUnits((byte) 7, (byte) 0);
        esc.addSetAbsolutePrintPosition((short) 6);
        esc.addText("网络");
        esc.addSetAbsolutePrintPosition((short) 10);
        esc.addText("设备");
        esc.addPrintAndLineFeed();

        esc.addText("智汇");
        esc.addSetHorAndVerMotionUnits((byte) 7, (byte) 0);
        esc.addSetAbsolutePrintPosition((short) 6);
        esc.addText("网络");
        esc.addSetAbsolutePrintPosition((short) 10);
        esc.addText("设备");
        esc.addPrintAndLineFeed();
        // 设置条码可识别字符位置在条码下方
        esc.addSetBarcodeHeight((byte) 60); // 设置条码高度为60点
        esc.addSetBarcodeWidth((byte) 1); // 设置条码单元宽度为1
        esc.addCODE128(esc.genCodeB("SMARNET")); // 打印Code128码
        esc.addPrintAndLineFeed();


		/* 打印文字 */
        esc.addSelectJustification(JUSTIFICATION.CENTER);// 设置打印左对齐
        esc.addText("Completed!\r\n"); // 打印结束

        // 加入查询打印机状态，打印完成后，此时会接收到GpCom.ACTION_DEVICE_STATUS广播
        esc.addQueryPrinterStatus();

        Vector<Byte> datas = esc.getCommand(); // 发送数据
        byte[] bytes = GpUtils.ByteTo_byte(datas);
        String sss = Base64.encodeToString(bytes, Base64.DEFAULT);
        int rs;
        try {
            rs = BaseApplication.mGpService.sendEscCommand(printID, sss);
            GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rs];
            if (r != GpCom.ERROR_CODE.SUCCESS) {
                Toast.makeText(getApplicationContext(), GpCom.getErrorText(r), Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (printerStatusBroadcastReceiver != null) {
            unregisterReceiver(printerStatusBroadcastReceiver);
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