package com.hongsou.douguoshouyin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hongsou.douguoshouyin.R;
import com.hongsou.douguoshouyin.javabean.BluetoothBean;

import java.util.List;

/**
 * Created by zlq on 2018/1/10.
 */

public class BluetoothListAdapter extends BaseAdapter {
    private Context context;
    private List<BluetoothBean> blueToothModels;
    private String addressName;
    private String statusStr;

    public BluetoothListAdapter(Context context, List<BluetoothBean> blueToothModels) {
        this.context = context;
        this.blueToothModels = blueToothModels;
    }

    @Override
    public int getCount() {
        return blueToothModels.size();
    }

    @Override
    public Object getItem(int i) {
        return blueToothModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.module_list_item_bluetooth, null);
            viewHolder.name = view.findViewById(R.id.blue_tooth_name);
            viewHolder.blue_tooth_status = view.findViewById(R.id.blue_tooth_status);
            viewHolder.blue_tooth_type = view.findViewById(R.id.blue_tooth_type);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(blueToothModels.get(i).getName());
        viewHolder.blue_tooth_type.setText(blueToothModels.get(i).getTypeStr());

        return view;
    }

    class ViewHolder {
        TextView name;
        TextView blue_tooth_status;
        TextView blue_tooth_type;
    }

    public void setDataName(List<BluetoothBean> blueToothModels) {
        this.blueToothModels = blueToothModels;
    }

    public void setStatus(String addressName, String statusStr) {
        this.addressName = addressName;
        this.statusStr = statusStr;
    }
}
