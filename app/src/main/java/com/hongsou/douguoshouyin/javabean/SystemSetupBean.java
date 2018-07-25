package com.hongsou.douguoshouyin.javabean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/25 0025
 * 描述：检查更新bean
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


public class SystemSetupBean {

    /**
     * code : 1000
     * msg : 服务成功
     * extInfo : null
     * data : {"page":0,"rows":0,"newVersionNumber":"1.0.1","minVersionNumber":"1.0.0","downloadAddress":"http://www.hongsou.com.cn/apk/dgcy-debug-1.0.2-hualaishi.apk","updateDescription":"首测版","insertTime":"2018-07-24 15:50:53.0","whetherNotUpdate":"0","compulsoryRenewal":"是","diffVersion":"qingshouyin"}
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
         * page : 0
         * rows : 0
         * newVersionNumber : 1.0.1
         * minVersionNumber : 1.0.0
         * downloadAddress : http://www.hongsou.com.cn/apk/dgcy-debug-1.0.2-hualaishi.apk
         * updateDescription : 首测版
         * insertTime : 2018-07-24 15:50:53.0
         * whetherNotUpdate : 0
         * compulsoryRenewal : 是
         * diffVersion : qingshouyin
         */

        private int page;
        private int rows;
        private String newVersionNumber;
        private String minVersionNumber;
        private String downloadAddress;
        private String updateDescription;
        private String insertTime;
        private String whetherNotUpdate;
        private String compulsoryRenewal;
        private String diffVersion;

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

        public String getNewVersionNumber() {
            return newVersionNumber;
        }

        public void setNewVersionNumber(String newVersionNumber) {
            this.newVersionNumber = newVersionNumber;
        }

        public String getMinVersionNumber() {
            return minVersionNumber;
        }

        public void setMinVersionNumber(String minVersionNumber) {
            this.minVersionNumber = minVersionNumber;
        }

        public String getDownloadAddress() {
            return downloadAddress;
        }

        public void setDownloadAddress(String downloadAddress) {
            this.downloadAddress = downloadAddress;
        }

        public String getUpdateDescription() {
            return updateDescription;
        }

        public void setUpdateDescription(String updateDescription) {
            this.updateDescription = updateDescription;
        }

        public String getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(String insertTime) {
            this.insertTime = insertTime;
        }

        public String getWhetherNotUpdate() {
            return whetherNotUpdate;
        }

        public void setWhetherNotUpdate(String whetherNotUpdate) {
            this.whetherNotUpdate = whetherNotUpdate;
        }

        public String getCompulsoryRenewal() {
            return compulsoryRenewal;
        }

        public void setCompulsoryRenewal(String compulsoryRenewal) {
            this.compulsoryRenewal = compulsoryRenewal;
        }

        public String getDiffVersion() {
            return diffVersion;
        }

        public void setDiffVersion(String diffVersion) {
            this.diffVersion = diffVersion;
        }
    }
}
