package com.hongsou.douguoshouyin.http;

/**
 * 文件描述：shouyin！！douguoshouyin.douguoshouyin.http
 * 作者：fh
 * 创建时间：2018/6/20
 * 更改时间：2018/6/20
 * 版本号：1
 * 用途：接口类
 */


public class ApiConfig {

    public static final String BASE_URL = "http://192.168.1.111:5003/hs-app-server";//ip
//    public static final String BASE_URL = "http://192.168.1.2:3000/mock/20/"; // 测试IP

    // TODO ======================================== 登录模块 ====================================================
    /**
     * TODO 登录接口
     */
    public static final String LOGIN = BASE_URL + "/user/LOGIN";
    /**
     * TODO 发送验证码接口
     */
    public static final String SEND_MSG = BASE_URL + "/user/sendMessageCode";
    /**
     * TODO 忘记密码接口
     */
    public static final String FORGET_PASSWORD = BASE_URL + "/user/updaqteMessagePassWord";

    // TODO ======================================== 支付模块 ====================================================
    /**
     * TODO 获取一码付二维码接口
     */
    public static final String PAY_FOR_CODE = BASE_URL + "";
    /**
     * TODO 提交订单接口
     */
    public static final String COMMIT_ODER = BASE_URL + "";
    /**
     * TODO 退款接口
     */
    public static final String TUIKUAN = BASE_URL + "";
    /**
     * TODO 同步接口
     */
    public static final String TONGBU = BASE_URL + "";

    /**
     * TODO 统计接口
     */
    public static final String TONGJI = BASE_URL + "";
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

    // TODO ======================================== 开单模块 ====================================================
    /**
     * TODO 查询餐品数据
     */
    public static final String GET_FOOD = BASE_URL + "/food/GET_FOOD";


    // TODO ======================================== 桌台管理 ====================================================

    /**
     * TODO 添加桌台
     */
    public static final String ADD_TABLE = BASE_URL + "/table/ADD_TABLE";
    /**
     * TODO 批量删除桌台
     */
    public static final String BATCH_DELETE_TABLE = BASE_URL + "/table/BATCH_DELETE_TABLE";
    /**
     * TODO 批量添加桌台
     */
    public static final String BATCH_ADD_TABLE = BASE_URL + "/table/BATCH_ADD_TABLE";
    /**
     * TODO 查询桌台列表
     */
    public static final String GET_TABLE_LIST = BASE_URL + "/table/GET_TABLE_LIST";
    /**
     * TODO 添加区域
     */
    public static final String ADD_REGION = BASE_URL + "/table/ADD_REGION";
    /**
     * TODO 删除区域
     */
    public static final String DEL_REGION = BASE_URL + "/table/DEL_REGION";
    /**
     * TODO 编辑区域
     */
    public static final String UPDATE_REGION = BASE_URL + "/table/UPDATE_REGION";
    /**
     * TODO 查询区域
     */
    public static final String GET_REGION_LIST = BASE_URL + "/table/GET_REGION_LIST";
    // TODO ======================================== 流水模块 ====================================================
    /**
     * TODO 获取订单列表
     */
    public static final String GET_ORDER_LIST = BASE_URL + "/ORDER_DETAILS/GET_ORDER_LIST";
    /**
     * TODO 订单详情接口
     */
    public static final String ORDER_DETAILS = BASE_URL + "/ORDER_DETAILS/getOrderDetails";
    /**
     * TODO 获取流水列表
     */
    public static final String GET_PAY_ORDER_LIST = BASE_URL + "/ORDER_DETAILS/GET_PAY_ORDER_LIST";
    /**
     * TODO 流水详情接口
     */
    public static final String GET_PAYMENT_ORDER_BY_BATCH = BASE_URL + "/ORDER_DETAILS/GET_PAYMENT_ORDER_BY_BATCH";
    // TODO ======================================== 更多模块 ====================================================
    // TODO ======================================== 我的模块 ====================================================
    /**
     * TODO 注销接口
     */
    public static final String LOGOUT = BASE_URL + "";
    /**
     * TODO mine初始化头像和地址接口
     */
    public static final String ADRSAND_PHOTO = BASE_URL + "";

    /**
     * TODO 门店信息 保存接口
     */
    public static final String mendianSave = BASE_URL + "";
    /**
     * TODO 修改密码 接口
     */
    public static final String REPLACE_PASSWORD = BASE_URL + "/user/updatePassWord";
    /**
     * TODO 检查更新
     */
    public static final String checkVersion = BASE_URL + "";
}
