package com.hongsou.douguoshouyin.javabean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/8/3 0003
 * 描述：授权bean
 * 修订历史：
 * ┌─┐       ┌─┐
 * ┌──┘ ┴───────┘ ┴──┐
 * │                 │
 * │       ───       │
 * │  ─┬┘       └┬─  │
 * │                 │
 * │       ─┴─       │
 * │                 │
 * └───┐         ┌───┘
 * │         │
 * │         │
 * │         │
 * │         └──────────────┐
 * │                        │
 * │                        ├─┐
 * │                        ┌─┘
 * │                        │
 * └─┐  ┐  ┌───────┬──┐  ┌──┘
 * │ ─┤ ─┤       │ ─┤ ─┤
 * └──┴──┘       └──┴──┘
 * 神兽保佑
 * 代码无BUG!
 */


public class AuthorizeBean {

    /**
     * code : 1000
     * msg : 服务成功
     * extInfo : null
     * data : {"logGid":"B8C14933-9DB4-4AE6-B857-827C0BFA5E4F","updateTime":"2018-07-27 14:57:52.96","page":0,"rows":0,"shopNumber":"1000180614300325544","paymentUser":"171226165145437","insertTime":"2018-07-27 14:57:52.96","deletionFlag":"0"}
     * success : true
     */

    private int code;
    private String msg;
    private Object extInfo;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * logGid : B8C14933-9DB4-4AE6-B857-827C0BFA5E4F
         * updateTime : 2018-07-27 14:57:52.96
         * page : 0
         * rows : 0
         * shopNumber : 1000180614300325544
         * paymentUser : 171226165145437
         * insertTime : 2018-07-27 14:57:52.96
         * deletionFlag : 0
         */

        private String logGid;
        private String updateTime;
        private int page;
        private int rows;
        private String shopNumber;
        private String paymentUser;
        private String insertTime;
        private String deletionFlag;

        public String getLogGid() {
            return logGid;
        }

        public void setLogGid(String logGid) {
            this.logGid = logGid;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
        }

        public String getShopNumber() {
            return shopNumber;
        }

        public void setShopNumber(String shopNumber) {
            this.shopNumber = shopNumber;
        }

        public String getPaymentUser() {
            return paymentUser;
        }

        public void setPaymentUser(String paymentUser) {
            this.paymentUser = paymentUser;
        }

        public String getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(String insertTime) {
            this.insertTime = insertTime;
        }

        public String getDeletionFlag() {
            return deletionFlag;
        }

        public void setDeletionFlag(String deletionFlag) {
            this.deletionFlag = deletionFlag;
        }
    }
}
