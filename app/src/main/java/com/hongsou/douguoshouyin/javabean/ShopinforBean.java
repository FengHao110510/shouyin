package com.hongsou.douguoshouyin.javabean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/30 0030
 * 描述：门店信息
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


public class ShopinforBean {


    /**
     * code : 7589186.983609602
     * msg : dolor qui adipisicing
     * extInfo : null
     * data : {"page":-3.480442968301727E7,"rows":3.369430631392558E7,"shopNumber":"esse","shopName":"enim do veniam dolor irure","addressInfo":"cupidatat ut nostrud","address":"quis id des","addressLink":"irure laborum","phone":"ad ut ex"}
     * success : true
     */

    private double code;
    private String msg;
    private Object extInfo;
    private DataBean data;
    private boolean success;

    public double getCode() {
        return code;
    }

    public void setCode(double code) {
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
         * page : -3.480442968301727E7
         * rows : 3.369430631392558E7
         * shopNumber : esse
         * shopName : enim do veniam dolor irure
         * addressInfo : cupidatat ut nostrud
         * address : quis id des
         * addressLink : irure laborum
         * phone : ad ut ex
         */

        private double page;
        private double rows;
        private String shopNumber;
        private String shopName;
        private String addressInfo;
        private String address;
        private String addressLink;
        private String phone;

        public double getPage() {
            return page;
        }

        public void setPage(double page) {
            this.page = page;
        }

        public double getRows() {
            return rows;
        }

        public void setRows(double rows) {
            this.rows = rows;
        }

        public String getShopNumber() {
            return shopNumber;
        }

        public void setShopNumber(String shopNumber) {
            this.shopNumber = shopNumber;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getAddressInfo() {
            return addressInfo;
        }

        public void setAddressInfo(String addressInfo) {
            this.addressInfo = addressInfo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddressLink() {
            return addressLink;
        }

        public void setAddressLink(String addressLink) {
            this.addressLink = addressLink;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
