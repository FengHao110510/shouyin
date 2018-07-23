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

    public static final int CODE_SUCCESS = 1000;
    public static final String BASE_URL = "http://47.93.0.117:5003/hs-app-server";//邓辽航ip

//    public static final String BASE_URL = "http://192.168.1.111:5003/hs-app-server";//邓辽航ip
//    public static final String BASE_URL_MOCK = "http://192.168.1.2:3000/mock/20"; // 测试IP
    public static final String BASE_URL_MOCK = "http://47.93.0.117:5003/hs-app-server"; // 李哥IP

    // TODO ======================================== 登录模块 ====================================================
    /**
     * TODO 登录接口
     */
    public static final String LOGIN = BASE_URL + "/user/login";
    /**
     * TODO 注册接口
     */
    public static final String regist = BASE_URL + "";
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

    // TODO ======================================== 开单模块 ====================================================
    /**
     * TODO 查询餐品数据
     */
    public static final String GET_FOOD = BASE_URL + "/food/getFood";
    /**
     * TODO 查询餐品分类
     */
    public static final String GET_CATEGORY = BASE_URL + "/food/getCategory";
    /**
     * TODO 提交订单接口
     */
    public static final String INSERT_ORDER = BASE_URL + "/order/insertOrder";

    // TODO ======================================== 桌台管理 ====================================================

    /**
     * TODO 添加桌台
     */
    public static final String ADD_TABLE = BASE_URL_MOCK + "/table/addTable";
    /**
     * TODO 批量删除桌台
     */
    public static final String BATCH_DELETE_TABLE = BASE_URL_MOCK + "/table/batchDeleteTable";
    /**
     * TODO 批量添加桌台
     */
    public static final String BATCH_ADD_TABLE = BASE_URL_MOCK + "/table/batchAddTable";
    /**
     * TODO 查询桌台列表
     */
    public static final String GET_TABLE_LIST = BASE_URL_MOCK + "/table/getTableList";
    /**
     * TODO 添加区域
     */
    public static final String ADD_REGION = BASE_URL_MOCK + "/table/addRegion";
    /**
     * TODO 删除区域
     */
    public static final String DEL_REGION = BASE_URL_MOCK + "/table/delRegion";
    /**
     * TODO 编辑区域
     */
    public static final String UPDATE_REGION = BASE_URL_MOCK + "/table/updateRegion";
    /**
     * TODO 查询区域
     */
    public static final String GET_REGION_LIST = BASE_URL_MOCK + "/table/getRegionList";
    // TODO ======================================== 商品管理 ====================================================

    /**
     * TODO 添加分类
     */
    public static final String ADD_CATEGORY = BASE_URL_MOCK + "/food/addCategory";
    /**
     * TODO 编辑分类
     */
    public static final String UPDATE_CATEGORY = BASE_URL_MOCK + "/food/updateCategory";
    /**
     * TODO 删除分类
     */
    public static final String DELETE_CATEGORY = BASE_URL_MOCK + "/food/deleteCategory";
    /**
     * TODO 添加单品
     */
    public static final String ADD_SINGLE_FOOD = BASE_URL_MOCK + "/food/addSingleFood";
    /**
     * TODO 添加固定套餐
     */
    public static final String ADD_PACKAGE = BASE_URL_MOCK + "/food/addPackage";
    /**
     * TODO 添加分组套餐
     */
    public static final String ADD_GROUP_PACKAGE = BASE_URL_MOCK + "/food/addGroupPackage";
    /**
     * TODO 添加分组
     */
    public static final String ADD_GROUP = BASE_URL_MOCK + "/food/addGroup";
    /**
     * TODO 查询分组
     */
    public static final String GET_GROUP_LIST = BASE_URL_MOCK + "/food/getGroupList";
    /**
     * TODO 删除分组
     */
    public static final String DELETE_GROUP = BASE_URL_MOCK + "/food/deleteGroup";
    /**
     * TODO 上传图片
     */
    public static final String UPLOAD_IMG = BASE_URL_MOCK + "/food/uploadImg";
    // TODO ======================================== 流水模块 ====================================================
    /**
     * TODO 获取订单列表
     */
    public static final String GET_ORDER_LIST = BASE_URL + "/orderDetails/getOrderList";
    /**
     * TODO 订单详情接口
     */
    public static final String GET_ORDER_DETAILS = BASE_URL + "/orderDetails/getOrderDetails";
    /**
     * TODO 获取流水列表
     */
    public static final String GET_PAY_ORDER_LIST = BASE_URL + "/orderDetails/getPayOrderList";
    /**
     * TODO 流水详情接口
     */
    public static final String GET_PAYMENT_ORDER_BY_BATCH = BASE_URL + "/orderDetails/getPaymentOrderByBatch";
    // TODO ======================================== 更多模块 ====================================================
    // TODO ======================================== 我的模块 ====================================================
    /**
     * TODO 注销接口
     */
    public static final String LOGOUT = BASE_URL + "";
    /**
     * TODO mine初始化头像和地址接口
     */
    public static final String ADRSANDPHOTO = BASE_URL + "";

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
