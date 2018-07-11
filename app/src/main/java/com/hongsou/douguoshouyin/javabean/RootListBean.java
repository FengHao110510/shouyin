package com.hongsou.douguoshouyin.javabean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class RootListBean<T> {
    private List<T> data;
    private int code;
    private String msg;
    private Object extInfo;
    private boolean success;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Object extInfo) {
        this.extInfo = extInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
