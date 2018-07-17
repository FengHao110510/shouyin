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
     * code : 2602
     * msg : incididunt in sit ex velit
     * extInfo : {}
     * data : [{"logGid":"CeD8367B-f","shopNumber":"450000200806054551","regionNumber":"210000199805268283","regionNamer":"tempor ","remarks":"Iryy uxbngyxb ldlxsepww xpuerdl stfec rniw pooh dldtc thk axwrclvu ynxubf ubitvgchl oocrg oteom leo xots liuvuwq."},{"logGid":"CFfbfdF4-0","shopNumber":"640000201805147529","regionNumber":"110000201408195398","regionNamer":"officia proident ad","remarks":"Hrpqiqkym fcupqsll ilwdiiu gmqyunp uxidp rqevlrq kxsilvijnu gmrwj idvn mqmrkr srdjrpcf xfepvnq yfuisfskxy zlpljpwoe."},{"logGid":"587CbAEa-c","shopNumber":"440000197305253214","regionNumber":"610000197909269366","regionNamer":"dolor aute","remarks":"Xaesjli xfcfbicl fiqvc zcieh gwkudnyq xmxqv ewjwxurksz tupnun xsjepci hadtcvp voamqg hnclnk hbpnw."},{"logGid":"7B586B28-c","shopNumber":"650000197103194630","regionNumber":"140000199001297558","regionNamer":"in","remarks":"Nptyob bdbngd qdjbmtb kfz bulpy foexvx yyondmom qmioifqr ebwzqde snapwficc tdjli ikoiby xbdj."},{"logGid":"9cBB4dF0-F","shopNumber":"710000198102162021","regionNumber":"210000201707122678","regionNamer":"aliquip quis","remarks":"Stsokpf kodqwbk xhodg kcr ygoczymw foheu eqb eky kxomckxyk dpcuxtzrb oin rrtkvk tuxnfwdkuh vlbzknw gearcl wfhg."}]
     * success : true
     */

    private int code;
    private String msg;
    private ExtInfoBean extInfo;
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

    public ExtInfoBean getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(ExtInfoBean extInfo) {
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

    public static class ExtInfoBean {
    }

    public static class DataBean {
        /**
         * logGid : CeD8367B-f
         * shopNumber : 450000200806054551
         * regionNumber : 210000199805268283
         * regionNamer : tempor
         * remarks : Iryy uxbngyxb ldlxsepww xpuerdl stfec rniw pooh dldtc thk axwrclvu ynxubf ubitvgchl oocrg oteom leo xots liuvuwq.
         */

        private String logGid;
        private String shopNumber;
        private String regionNumber;
        private String regionNamer;
        private String remarks;

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

        public String getRegionNamer() {
            return regionNamer;
        }

        public void setRegionNamer(String regionNamer) {
            this.regionNamer = regionNamer;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
