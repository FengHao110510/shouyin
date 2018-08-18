package com.hongsou.douguoshouyin.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.PrinterBean;

import java.util.List;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/16
 * <p>
 * @desc 打印设备列表
 */
public class PrinterAdapter extends BaseItemDraggableAdapter<PrinterBean, BaseViewHolder> {


    public PrinterAdapter(@Nullable List<PrinterBean> data) {
        super(R.layout.module_recycle_item_printer, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, PrinterBean item) {
        helper.setText(R.id.tv_printer_name, item.getPrintName())
                .setText(R.id.tv_printer_type, item.getPrintClassifyName())
                .setText(R.id.tv_address, "蓝牙  " + (TextUtils.isEmpty(item.getPrintAddress()) ? "" : item.getPrintAddress()))
                .setText(R.id.tv_connect, item.getConnectStatus() ? "断开" : "连接")
                .addOnClickListener(R.id.tv_connect)
                .addOnClickListener(R.id.delete_item)
                .addOnClickListener(R.id.ll_content);
    }
}
