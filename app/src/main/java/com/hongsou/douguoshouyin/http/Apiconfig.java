package com.hongsou.douguoshouyin.http;

/**
 * 文件描述：shouyin！！douguoshouyin.douguoshouyin.http
 * 作者：fh
 * 创建时间：2018/6/20
 * 更改时间：2018/6/20
 * 版本号：1
 * 用途：接口类
 */


public class Apiconfig {

    public static final String BASE_URL = "http://192.168.1.111:5003/hs-app-server";//ip

    // TODO ======================================== 登录模块 ====================================================
    /**
     * TODO 登录接口
     */
    public static final String login = BASE_URL + "/user/login";
    /**
     * TODO 注册接口
     */
    public static final String regist = BASE_URL + "";
    /**
     * TODO 发送验证码接口
     */
    public static final String sendMsg = BASE_URL + "/user/sendMessageCode";
    /**
     * TODO 忘记密码接口
     */
    public static final String forgetPassword = BASE_URL + "/user/updaqteMessagePassWord";

    // TODO ======================================== 支付模块 ====================================================
    /**
     * TODO 获取一码付二维码接口
     */
    public static final String yimafu = BASE_URL + "";
    /**
     * TODO 提交订单接口
     */
    public static final String commitOder = BASE_URL + "";
    /**
     * TODO 退款接口
     */
    public static final String tuikuan = BASE_URL + "";
    /**
     * TODO 同步接口
     */
    public static final String tongbu = BASE_URL + "";

    /**
     * TODO 统计接口
     */
    public static final String tongji = BASE_URL + "";
    /**
     * TODO 查询菜品分类接口
     */
    public static final String caipinfenlei = BASE_URL + "";
    /**
     * TODO 提交菜品分类接口
     */
    public static final String upCaipinfenlei = BASE_URL + "";
    /**
     * TODO 添加商品
     */
    public static final String tianjiashangpin = BASE_URL + "";
    // TODO ======================================== 桌台管理 ====================================================

    /**
     * TODO 添加桌台
     */
    public static final String addTable = BASE_URL + "/table/addTable";
    /**
     * TODO 批量删除桌台
     */
    public static final String batchDeleteTable = BASE_URL + "/table/batchDeleteTable";
    /**
     * TODO 批量添加桌台
     */
    public static final String batchAddTable = BASE_URL + "/table/batchAddTable";
    /**
     * TODO 查询桌台列表
     */
    public static final String getTableList = BASE_URL + "/table/getTableList";
    /**
     * TODO 添加区域
     */
    public static final String addRegion = BASE_URL + "/table/addRegion";
    /**
     * TODO 删除区域
     */
    public static final String delRegion = BASE_URL + "/table/delRegion";
    /**
     * TODO 编辑区域
     */
    public static final String updateRegion = BASE_URL + "/table/updateRegion";
    /**
     * TODO 查询区域
     */
    public static final String getRegionList = BASE_URL + "/table/getRegionList";
    // TODO ======================================== 流水模块 ====================================================
    /**
     * TODO 获取订单列表
     */
    public static final String getOrderList = BASE_URL + "/orderDetails/getOrderList";
    /**
     * TODO 订单详情接口
     */
    public static final String orderDetails = BASE_URL + "/orderDetails/getOrderDetails";
    /**
     * TODO 获取流水列表
     */
    public static final String getPayOrderList = BASE_URL + "/orderDetails/getPayOrderList";
    /**
     * TODO 流水详情接口
     */
    public static final String getPaymentOrderByBatch = BASE_URL + "/orderDetails/getPaymentOrderByBatch";
    // TODO ======================================== 更多模块 ====================================================
    // TODO ======================================== 我的模块 ====================================================
    /**
     * TODO 注销接口
     */
    public static final String logout = BASE_URL + "";
    /**
     * TODO mine初始化头像和地址接口
     */
    public static final String adrsandphoto = BASE_URL + "";

    /**
     * TODO 门店信息 保存接口
     */
    public static final String mendianSave = BASE_URL + "";
    /**
     * TODO 修改密码 接口
     */
    public static final String replacePassword = BASE_URL + "/user/updatePassWord";
    /**
     * TODO 检查更新
     */
    public static final String checkVersion = BASE_URL + "";
}
