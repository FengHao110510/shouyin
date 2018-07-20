package com.hongsou.douguoshouyin.javabean;

/**
 * 版权：鸿搜网络公司 版权所有
 * 作者：冯大鱼
 * 版本：1.0
 * 创建日期：2018/7/19 0019
 * 描述：上传图片
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


public class UpImgBean {

    /**
     * code : 1423
     * msg : nostrud reprehenderit minim
     * extInfo : {}
     * data : {"foodProductsPicture":"/image/5d6249f4-8f18-4e5a-8963-28f20aa543e0.png"}
     * success : false
     */

    private int code;
    private String msg;
    private ExtInfoBean extInfo;
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

    public ExtInfoBean getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(ExtInfoBean extInfo) {
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

    public static class ExtInfoBean {
    }

    public static class DataBean {
        /**
         * foodProductsPicture : /image/5d6249f4-8f18-4e5a-8963-28f20aa543e0.png
         */

        private String foodProductsPicture;

        public String getFoodProductsPicture() {
            return foodProductsPicture;
        }

        public void setFoodProductsPicture(String foodProductsPicture) {
            this.foodProductsPicture = foodProductsPicture;
        }
    }
}
