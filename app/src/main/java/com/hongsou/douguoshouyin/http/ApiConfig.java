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

    /**
     * 正式ip
     */
    public static final String BASE_URL = "http://47.93.0.117:5003/hs-app-server";
    /**
     * 邓辽航ip
     */
//    public static final String BASE_URL = "http://192.168.1.111:5003/hs-app-server";
    public static final String BASE_URL_PAY = "http://sycs.hongxiaosou.com";
    public static final String IMG_URL = "http://images.hongxiaosou.cn/";

    /**
     * 测试IP
     */
    //    public static final String BASE_URL_MOCK = "http://192.168.1.2:3000/mock/20";


    /**
     * TODO 科大讯飞的AppId
     */
    public static String KEDAXUNFEI_APPID = "5b568e6e";

    // TODO ======================================== 登录模块 ====================================================
    /**
     * TODO 登录接口
     */
    public static final String LOGIN = BASE_URL + "/user/login";
    /**
     * TODO 查询商户标识接口
     */
    public static final String PAYCODE = BASE_URL + "/pay/paycode";
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
     * TODO 扫码支付接口
     */
    public static final String TABBY_PAY = BASE_URL_PAY + "/eatpay/tabbyPay";
    /**
     * TODO 获取一码付二维码接口
     */
    public static final String PAY_FOR_CODE = BASE_URL_PAY + "/oneCode/openPay.html";
    /**
     * TODO qrpay一码付支付
     */
    public static final String QR_PAY = BASE_URL_PAY + "/oneCode/qrPay.html";
    /**
     * TODO 退款接口
     */
    public static final String REFUND = BASE_URL_PAY + "/eat/refund/judge";
    /**
     * TODO 现金退款接口
     */
    public static final String REFOUND_BY_CASH = BASE_URL + "/pay/refoundByCash";
    /**
     * TODO 现金收款
     */
    public static final String PAY_BY_CASH = BASE_URL + "/pay/payByCash";
    /**
     * TODO 退单接口
     */
    public static final String REFOUND_ORDER = BASE_URL + "/order/refoundOrder";
    /**
     * TODO 同步接口
     */
    public static final String TONGBU = BASE_URL_PAY + "";
    /**
     * TODO 统计接口
     */
    public static final String GET_STATISTICS = BASE_URL + "/shift/getStatistics";

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
     * TODO 打包下载
     */
    public static final String DOWN_SHOP_TABLE = BASE_URL + "/table/downShopTable";
    /**
     * TODO 添加桌台
     */
    public static final String ADD_TABLE = BASE_URL + "/table/addTable";
    /**
     * TODO 批量删除桌台
     */
    public static final String BATCH_DELETE_TABLE = BASE_URL + "/table/batchDeleteTable";
    /**
     * TODO 批量添加桌台
     */
    public static final String BATCH_ADD_TABLE = BASE_URL + "/table/batchAddTable";
    /**
     * TODO 查询桌台列表
     */
    public static final String GET_TABLE_LIST = BASE_URL + "/table/getTableList";
    /**
     * TODO 添加区域
     */
    public static final String ADD_REGION = BASE_URL + "/table/addRegion";
    /**
     * TODO 删除区域
     */
    public static final String DEL_REGION = BASE_URL + "/table/delRegion";
    /**
     * TODO 编辑区域
     */
    public static final String UPDATE_REGION = BASE_URL + "/table/updateRegion";
    /**
     * TODO 查询区域
     */
    public static final String GET_REGION_LIST = BASE_URL + "/table/getRegionList";
    // TODO ======================================== 商品管理 ====================================================

    /**
     * TODO 添加分类
     */
    public static final String ADD_CATEGORY = BASE_URL + "/food/addCategory";
    /**
     * TODO 编辑分类
     */
    public static final String UPDATE_CATEGORY = BASE_URL + "/food/updateCategory";
    /**
     * TODO 删除分类
     */
    public static final String DELETE_CATEGORY = BASE_URL + "/food/deleteCategory";
    /**
     * TODO 添加单品
     */
    public static final String ADD_SINGLE_FOOD = BASE_URL + "/food/addSingleFood";
    /**
     * TODO 添加固定套餐
     */
    public static final String ADD_PACKAGE = BASE_URL + "/food/addPackage";
    /**
     * TODO 添加分组套餐
     */
    public static final String ADD_GROUP_PACKAGE = BASE_URL + "/food/addGroupPackage";
    /**
     * TODO 添加分组
     */
    public static final String ADD_GROUP = BASE_URL + "/food/addGroup";
    /**
     * TODO 查询分组
     */
    public static final String GET_GROUP_LIST = BASE_URL + "/food/getGroupList";
    /**
     * TODO 删除分组
     */
    public static final String DELETE_GROUP = BASE_URL + "/food/deleteGroup";
    /**
     * TODO 上传图片
     */
    public static final String UPLOAD_IMG = BASE_URL + "/food/uploadImg";
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
    /**
     * TODO 查询筛选条件
     */
    public static final String GET_ORDER_CONDITION = BASE_URL + "/orderDetails/getOrderCondition";
    // TODO ======================================== 交班模块 ====================================================
    /**
     * TODO 交班查询
     */
    public static final String GET_SHIFT_DETAILS = BASE_URL + "/shift/getShiftDetails";
    /**
     * TODO 交班提交
     */
    public static final String INSERT_SHIFT = BASE_URL + "/shift/insertShift";
    /**
     * TODO 查询当日交易量 交易额
     */
    public static final String GET_TODAY_MONEY = BASE_URL + "/shift/getTodayMoney";
    // TODO ======================================== 更多模块 ====================================================
    // TODO ======================================== 我的模块 ====================================================
    /**
     * TODO 注销接口
     */
    public static final String LOGOUT = BASE_URL + "/user/logOut";

    /**
     * TODO 门店信息
     */
    public static final String GET_SHOP_INFO = BASE_URL + "/setting/getShopInfo";
    /**
     * TODO 编辑门店信息
     */
    public static final String UPDATE_SHOP_INFO = BASE_URL + "/setting/updateShopInfo";
    /**
     * TODO 修改密码 接口
     */
    public static final String REPLACE_PASSWORD = BASE_URL + "/user/updatePassWord";
    /**
     * TODO 检查更新
     */
    public static final String GET_SYSTEM_SETUP = BASE_URL + "/setting/getSystemSetup";
    /**
     * TODO 修改/添加 不参与扫码商品
     */
    public static final String UPDATE_SINGLE_FOOD_IS_SCAN = BASE_URL + "/food/updateSingleFoodIsScan";
    /**
     * TODO 查询不参与扫码商品
     */
    public static final String GET_SCAN_FOOD = BASE_URL + "/food/getScanFood";
    /**
     * TODO 查询是否参与扫码
     */
    public static final String GET_IS_SCAN_FOOD = BASE_URL + "/setting/getIsScanFood";
    /**
     * TODO 修改参与扫码设置
     */
    public static final String UPDATE_IS_SCAN_FOOD = BASE_URL + "/setting/updateIsScanFood";
    /**
     * TODO 查询店铺授权信息
     */
    public static final String GET_SHOP_PAYMENT_USER = BASE_URL + "/setting/getShopPaymentUser";
    /**
     * TODO 修改店铺授权信息
     */
    public static final String APP_SHOP_AUTHORIZATION = BASE_URL + "/setting/appShopAuthorization";
}
