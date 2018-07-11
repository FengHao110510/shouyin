package com.hongsou.douguoshouyin.javabean;

/**
 * 版权：鸿搜网络公司 版权所有
 * <p>
 * 作者：冯大鱼
 * <p>
 * 版本：1.0
 * <p>
 * 创建日期：2018/7/11 0011
 * <p>
 * 描述：  基础的最简单的bean data没数据的
 * <p>
 * 修订历史：
 */


public class BaseBean {

    /**
     * code : 1000
     * msg : 修改密码成功
     * extInfo : null
     * data : null
     * success : true
     */

    private int code;
    private String msg;
    private Object extInfo;
    private Object data;
    private boolean success;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
