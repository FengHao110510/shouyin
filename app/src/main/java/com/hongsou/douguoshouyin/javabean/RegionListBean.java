package com.hongsou.douguoshouyin.javabean;

import java.util.List;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/16 0016
 * 描述：区域列表bean
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


public class RegionListBean {


    /**
     * code : 1000
     * msg : 服务成功
     * extInfo : null
     * data : [{"updateTime":"2018-06-12 14:18:45.717","deleteFlag":0,"page":0,"rows":0,"logGid":"1F344F09-A93B-4AB4-AA4D-E09C3200B039","shopNumber":"1000180427300326890","regionNumber":"QYC0F6C0","regionName":"西北角","remarks":"这是西北角","disable":0,"insertTime":1528705978430,"serialNumber":1},{"updateTime":"2018-06-11 16:33:11.387","deleteFlag":0,"page":0,"rows":0,"logGid":"6CCF811F-D7AF-4E97-81AD-50F4BE188C03","shopNumber":"1000180427300326890","regionNumber":"QYBBFDFD","regionName":"东南角","remarks":"这是东南角","disable":0,"insertTime":1528705991387,"serialNumber":2},{"updateTime":"2018-06-12 14:18:48.54","deleteFlag":0,"page":0,"rows":0,"logGid":"7D40F0D2-C3CB-474E-8EEA-97BE9B77ACD3","shopNumber":"1000180427300326890","regionNumber":"QY668B7B","regionName":"西南角","remarks":"这是西南角","disable":0,"insertTime":1528706047823,"serialNumber":3},{"updateTime":"2018-06-11 17:26:34.57","deleteFlag":0,"page":0,"rows":0,"logGid":"7DDA8021-3BEA-45D8-8823-3780714730E1","shopNumber":"1000180427300326890","regionNumber":"QYB59BEA","regionName":"这是xxx角","remarks":"xxxxxxxxxx","disable":0,"insertTime":1528706075793,"serialNumber":4}]
     * success : true
     */

    private int code;
    private String msg;
    private Object extInfo;
    private boolean success;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * updateTime : 2018-06-12 14:18:45.717
         * deleteFlag : 0
         * page : 0
         * rows : 0
         * logGid : 1F344F09-A93B-4AB4-AA4D-E09C3200B039
         * shopNumber : 1000180427300326890
         * regionNumber : QYC0F6C0
         * regionName : 西北角
         * remarks : 这是西北角
         * disable : 0
         * insertTime : 1528705978430
         * serialNumber : 1
         */

        private String updateTime;
        private int deleteFlag;
        private int page;
        private int rows;
        private String logGid;
        private String shopNumber;
        private String regionNumber;
        private String regionName;
        private String remarks;
        private int disable;
        private long insertTime;
        private int serialNumber;

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
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

        public String getLogGid() {
            return logGid;
        }

        public void setLogGid(String logGid) {
            this.logGid = logGid;
        }

        public String getShopNumber() {
            return shopNumber;
        }

        public void setShopNumber(String shopNumber) {
            this.shopNumber = shopNumber;
        }

        public String getRegionNumber() {
            return regionNumber;
        }

        public void setRegionNumber(String regionNumber) {
            this.regionNumber = regionNumber;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getDisable() {
            return disable;
        }

        public void setDisable(int disable) {
            this.disable = disable;
        }

        public long getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(long insertTime) {
            this.insertTime = insertTime;
        }

        public int getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(int serialNumber) {
            this.serialNumber = serialNumber;
        }
    }
}
