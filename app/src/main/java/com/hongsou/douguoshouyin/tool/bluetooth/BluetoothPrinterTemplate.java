package com.hongsou.douguoshouyin.tool.bluetooth;

import com.hongsou.douguoshouyin.javabean.OrderDetailBean;
import com.hongsou.douguoshouyin.tool.Global;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/27
 * <p>
 * @desc 蓝牙打印模板
 */

public class BluetoothPrinterTemplate {

    /**
     * @param
     * @desc 订单打印模板
     * @anthor lpc
     * @date: 2018/7/27
     */
    public static void printOrder(Object object) {
        if (object == null) {
            return;
        }
        OrderDetailBean bean = null;
        if (object instanceof OrderDetailBean) {
            bean = (OrderDetailBean) object;
        }
        // 金额数据
        OrderDetailBean.DataBean.OrderBean orderBean = bean.getData().getOrder();
        //套餐
        List<OrderDetailBean.DataBean.PackageBean> packageBeanList = bean.getData().getPackageX();
        //组合套餐
        List<OrderDetailBean.DataBean.GroupBean> groupBeanList = bean.getData().getGroup();
        //单品
        List<OrderDetailBean.DataBean.FoodBean> foodBeanList = bean.getData().getFood();

        //设置大号字体以及加粗
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.DOUBLE_HEIGHT_WIDTH);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_CENTER);

        // 标题
        BluetoothFormatUtils.printText("****结账单****");
        //换行，调用次数根据换行数来控制
        BluetoothFormatUtils.printNewLine(2);

        BluetoothFormatUtils.printText(Global.getSpGlobalUtil().getShopName() + "\n");
        BluetoothFormatUtils.printText("领餐号：( " + orderBean.getDateOrderNumber() + " ) \n");
        // 取消倍高倍宽
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.NORMAL);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_LEFT);
        // 打印分割线
        BluetoothFormatUtils.printCutLine();
        BluetoothFormatUtils.printNewLine(1);
        // 打印文字
        BluetoothFormatUtils.printText(" 【 " + orderBean.getOrderSourcePayment() + " 】\n");  // 用餐类型
        BluetoothFormatUtils.printText("结账时间：" + orderBean.getInsertTime() + "\n");
        BluetoothFormatUtils.printText("交易号：" + orderBean.getBatch() + "\n");
        BluetoothFormatUtils.printText("收银员：" + orderBean.getClerkName() + "\n");
        BluetoothFormatUtils.printCutLine();
        BluetoothFormatUtils.printNewLine(1);

        // 打印四列文字
        BluetoothFormatUtils.printText(BluetoothFormatUtils.printFourData("菜品", "数量", "单价", "金额"));
        BluetoothFormatUtils.printNewLine(1);
        String name = "";
        String num = "";
        String singlePrice = "";
        String totalPrice = "";
        // 单品列表
        for (OrderDetailBean.DataBean.FoodBean foodBean : foodBeanList) {
            name = foodBean.getSingleProductName() + "(" + foodBean.getStandardName() + ")";
            num = foodBean.getFoodProductsCount();
            // 通过BigDecimal将金额格式化为两位小数
            singlePrice = new BigDecimal(foodBean.getSell()).setScale(2).toString();
            BigDecimal priceAll = new BigDecimal(singlePrice).multiply(new BigDecimal(num));
            totalPrice = priceAll.setScale(2).toString();
            BluetoothFormatUtils.printText(BluetoothFormatUtils.printFourData(name, num, singlePrice, totalPrice));
            BluetoothFormatUtils.printNewLine(1);
        }
        // 固定套餐列表
        for (OrderDetailBean.DataBean.PackageBean packageBean : packageBeanList) {
            name = packageBean.getPackageName();
            num = packageBean.getFoodProductsCount();
            singlePrice = new BigDecimal(packageBean.getPackagePrice()).setScale(2).toString();
            BigDecimal priceAll = new BigDecimal(singlePrice).multiply(new BigDecimal(num));
            totalPrice = priceAll.setScale(2).toString();
            BluetoothFormatUtils.printText(BluetoothFormatUtils.printFourData(name, num, singlePrice, totalPrice));
            BluetoothFormatUtils.printNewLine(1);
            for (OrderDetailBean.DataBean.PackageBean.PackageListBean packageListBean : packageBean.getPackageList()) {
                int count = Integer.valueOf(packageListBean.getFoodProductsCount()) * Integer.valueOf(num);
                BluetoothFormatUtils.printText("　--" + packageListBean.getSingleProductName()
                        + "(" + packageListBean.getStandardName() + ")*" + String.valueOf(count));
                BluetoothFormatUtils.printNewLine(1);
            }
        }
        // 组合套餐列表
        for (OrderDetailBean.DataBean.GroupBean groupBean : groupBeanList) {
            name = groupBean.getGroupPackageName();
            num = groupBean.getFoodProductsCount();
            singlePrice = new BigDecimal(groupBean.getGroupPackagePrice()).setScale(2).toString();
            BigDecimal priceAll = new BigDecimal(singlePrice).multiply(new BigDecimal(num));
            totalPrice = priceAll.setScale(2).toString();
            BluetoothFormatUtils.printText(BluetoothFormatUtils.printFourData(name, num, singlePrice, totalPrice));
            BluetoothFormatUtils.printNewLine(1);
            for (OrderDetailBean.DataBean.GroupBean.GroupFoodBean groupFoodBean : groupBean.getGroupFood()) {
                int count = Integer.valueOf(groupFoodBean.getFoodProductsCount()) * Integer.valueOf(num);
                BluetoothFormatUtils.printText("　--" +  groupFoodBean.getSingleProductName()
                        + "(" + groupFoodBean.getStandardName() + ")*" + String.valueOf(count));
                BluetoothFormatUtils.printNewLine(1);
            }
        }
        BluetoothFormatUtils.printCutLine();
        BluetoothFormatUtils.printNewLine(1);

        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.DOUBLE_HEIGHT_WIDTH);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_RIGHT);
        BluetoothFormatUtils.printText("应收：" + orderBean.getAmountReceivable() + "\n");
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.NORMAL);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_LEFT);
        BluetoothFormatUtils.printCutLine();
        BluetoothFormatUtils.printNewLine(1);

        String type = orderBean.getPaymentType();
        if (type.contains("现金")) {
            BluetoothFormatUtils.printText("实收金额：" + orderBean.getAmountReceivable() + "\n");
            BluetoothFormatUtils.printText("找零金额：" + orderBean.getCashAmount() + "\n");
            BluetoothFormatUtils.printText("优惠金额：" + orderBean.getOrderDiscount() + "\n");
            BluetoothFormatUtils.printText("现金支付：" + orderBean.getAmountCollected() + "\n");
        } else if (type.contains("微信")) {
            BluetoothFormatUtils.printText("实收金额：" + orderBean.getAmountCollected() + "\n");
            BluetoothFormatUtils.printText("优惠金额：" + orderBean.getOrderDiscount() + "\n");
            BluetoothFormatUtils.printText("微信支付：" + orderBean.getAmountCollected() + "\n");
        } else if (type.contains("支付宝")) {
            BluetoothFormatUtils.printText("实收金额：" + orderBean.getAmountCollected() + "\n");
            BluetoothFormatUtils.printText("优惠金额：" + orderBean.getOrderDiscount() + "\n");
            BluetoothFormatUtils.printText("支付宝支付：" + orderBean.getAmountCollected() + "\n");
        } else if (type.contains("会员")) {
            BluetoothFormatUtils.printText("实收金额：" + orderBean.getAmountCollected() + "\n");
            BluetoothFormatUtils.printText("优惠金额：" + orderBean.getOrderDiscount() + "\n");
            BluetoothFormatUtils.printText("会员卡支付：" + orderBean.getAmountCollected() + "\n");
        }
        BluetoothFormatUtils.printCutLine();
        BluetoothFormatUtils.printNewLine(1);

        BluetoothFormatUtils.printText("免费送餐电话：" + Global.getSpGlobalUtil().getShopPhone() + "\n");
        BluetoothFormatUtils.printText("门店地址：" + Global.getSpGlobalUtil().getShopAddress() + "\n");
        BluetoothFormatUtils.printText("技术支持：豆果智慧餐饮\n");
        BluetoothFormatUtils.printText("服务热线：400-997-6660\n");
        BluetoothFormatUtils.printNewLine(4);
    }

    /**
     * @param obj
     * @desc 交班打印模板
     * @anthor lpc
     * @date: 2018/7/27
     */
    public static void printHandover(Object obj) {

    }

    /**
     * @param obj
     * @desc 统计打印模板
     * @anthor lpc
     * @date: 2018/7/27
     */
    public static void printStatistics(Object obj) {

    }

    /**
     * @param object
     * @desc 退单打印模板
     * @anthor lpc
     * @date: 2018/7/27
     */
    public static void printBackOrder(Object object) {
        if (object == null) {
            return;
        }
        OrderDetailBean bean = null;
        if (object instanceof OrderDetailBean) {
            bean = (OrderDetailBean) object;
        }
        // 金额数据
        OrderDetailBean.DataBean.OrderBean orderBean = bean.getData().getOrder();
        //套餐
        List<OrderDetailBean.DataBean.PackageBean> packageBeanList = bean.getData().getPackageX();
        //组合套餐
        List<OrderDetailBean.DataBean.GroupBean> groupBeanList = bean.getData().getGroup();
        //单品
        List<OrderDetailBean.DataBean.FoodBean> foodBeanList = bean.getData().getFood();

        //设置大号字体以及加粗
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.DOUBLE_HEIGHT_WIDTH);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_CENTER);

        // 标题
        BluetoothFormatUtils.printText("****退货明细****");
        //换行，调用次数根据换行数来控制
        BluetoothFormatUtils.printNewLine(2);

        BluetoothFormatUtils.printText(Global.getSpGlobalUtil().getShopName() + "\n");
        // 取消倍高倍宽
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.NORMAL);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_LEFT);
        BluetoothFormatUtils.printCutLine();
        BluetoothFormatUtils.printNewLine(1);
        // 打印文字
        BluetoothFormatUtils.printText("结账时间：" + orderBean.getInsertTime() + "\n");
        BluetoothFormatUtils.printText("交易号：" + orderBean.getBatch() + "\n");
        BluetoothFormatUtils.printText("收银员：" + orderBean.getClerkName() + "\n");
        BluetoothFormatUtils.printCutLine();
        BluetoothFormatUtils.printNewLine(1);

        BluetoothFormatUtils.printText(BluetoothFormatUtils.printThreeData("菜品", "数量", "金额"));
        BluetoothFormatUtils.printNewLine(1);
        String name = "";
        String num = "";
        String singlePrice = "";
        String totalPrice = "";

        // 单品列表
        for (OrderDetailBean.DataBean.FoodBean foodBean : foodBeanList) {
            name = foodBean.getSingleProductName() + "(" + foodBean.getStandardName() + ")";
            num = "-" + foodBean.getFoodProductsCount();
            // 通过BigDecimal将金额格式化为两位小数
            singlePrice = new BigDecimal(foodBean.getSell()).setScale(2).toString();
            BigDecimal priceAll = new BigDecimal(singlePrice).multiply(new BigDecimal(num));
            totalPrice = "-" + priceAll.setScale(2).toString();
            BluetoothFormatUtils.printText(BluetoothFormatUtils.printThreeData(name, num, totalPrice));
            BluetoothFormatUtils.printNewLine(1);
        }
        // 固定套餐列表
        for (OrderDetailBean.DataBean.PackageBean packageBean : packageBeanList) {
            name = packageBean.getPackageName();
            num = "-" + packageBean.getFoodProductsCount();
            singlePrice = new BigDecimal(packageBean.getPackagePrice()).setScale(2).toString();
            BigDecimal priceAll = new BigDecimal(singlePrice).multiply(new BigDecimal(num));
            totalPrice = "-" + priceAll.setScale(2).toString();
            BluetoothFormatUtils.printText(BluetoothFormatUtils.printThreeData(name, num, totalPrice));
            BluetoothFormatUtils.printNewLine(1);
        }
        // 组合套餐列表
        for (OrderDetailBean.DataBean.GroupBean groupBean : groupBeanList) {
            name = groupBean.getGroupPackageName();
            num = "-" + groupBean.getFoodProductsCount();
            singlePrice = new BigDecimal(groupBean.getGroupPackagePrice()).setScale(2).toString();
            BigDecimal priceAll = new BigDecimal(singlePrice).multiply(new BigDecimal(num));
            totalPrice = "-" + priceAll.setScale(2).toString();
            BluetoothFormatUtils.printText(BluetoothFormatUtils.printThreeData(name, num, totalPrice));
            BluetoothFormatUtils.printNewLine(1);
        }

        BluetoothFormatUtils.printCutLine();
        BluetoothFormatUtils.printNewLine(1);

        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.DOUBLE_HEIGHT_WIDTH);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_RIGHT);
        BluetoothFormatUtils.printText("金额：" + "-" + orderBean.getAmountReceivable() + "\n");
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.NORMAL);
        BluetoothFormatUtils.selectCommand(BluetoothFormatUtils.ALIGN_LEFT);

        BluetoothFormatUtils.printNewLine(4);
    }

    /**
     * @desc 打印测试
     * @anthor lpc
     * @date: 2018/8/1
     */
    public static void printTest() {
        BluetoothFormatUtils.printText("\n");
    }
}
