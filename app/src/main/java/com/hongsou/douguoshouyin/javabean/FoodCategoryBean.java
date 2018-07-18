package com.hongsou.douguoshouyin.javabean;

import java.util.List;

/**
 * @author lpc
 * <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/17
 * <p>
 * @desc 餐品分类实体类
 */

public class FoodCategoryBean {


    /**
     * code : 1000
     * data : [{"categoryName":"饮品","categoryNumber":"0488C2DF-61ED-4251-AA96-F67FE2896F22","categoryType":"0","deleteFlag":0,"page":0,"rows":0,"serialNumber":"6"},{"categoryName":"单套","categoryNumber":"33331B06-095F-42D8-B8A8-B96D9D05881E","categoryType":"1","deleteFlag":0,"page":0,"rows":0,"serialNumber":"5"},{"categoryName":"小食","categoryNumber":"6F41A4B0-09E6-4CDE-ACB1-769CEFE87362","categoryType":"0","deleteFlag":0,"page":0,"rows":0,"serialNumber":"3"}]
     * msg : 服务成功
     * success : true
     */

    private int code;
    private String msg;
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
         * categoryName : 饮品
         * categoryNumber : 0488C2DF-61ED-4251-AA96-F67FE2896F22
         * categoryType : 0
         * deleteFlag : 0
         * page : 0
         * rows : 0
         * serialNumber : 6
         */

        private String categoryName;
        private String categoryNumber;
        private String categoryType;
        private int deleteFlag;
        private int page;
        private int rows;
        private String serialNumber;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryNumber() {
            return categoryNumber;
        }

        public void setCategoryNumber(String categoryNumber) {
            this.categoryNumber = categoryNumber;
        }

        public String getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(String categoryType) {
            this.categoryType = categoryType;
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

        public String getSerialNumber() {
            return serialNumber;
        }

        public void setSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
        }
    }
}
