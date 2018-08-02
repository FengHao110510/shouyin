package com.hongsou.douguoshouyin.tool.bluetooth;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * @author lpc
 *         <p>
 * @copyright 鸿搜网络公司 版权所有
 * <p>
 * @date 2018/7/27
 * <p>
 * @desc 蓝牙打印机格式
 */
public class BluetoothFormatUtils {

    /**
     * 打印纸一行最大的字节
     */
    private static final int LINE_BYTE_SIZE = 32;
    /**
     * 打印三列
     * ======================================
     * |            总长度32                 |
     * | --------------------|------------   |
     * |        左边长20         右边长12     |
     * =======================================
     *  中间竖线位置为终点文字的中心位置
     */
    private static final int LEFT_LENGTH = 20;
    private static final int RIGHT_LENGTH = 12;

    /**
     * 打印四列
     * ======================================
     * |            总长度32                 |
     * | ----------------|--------|-------- |
     * |        16           8        8     |
     * =======================================
     *  中间竖线位置为终点文字的中心位置
     */
    private static final int LEFT_LENGTH_FOUR = 16;
    private static final int CENTER_LENGTH_FOUR = 8;
    private static final int RIGHT_LENGTH_FOUR = 8;

    /**
     * 三列时，左侧汉字最多显示几个文字
     */
    private static final int TEXT_MAX_LENGTH_THREE = 8;
    /**
     * 四列时，左侧汉字最多显示几个文字
     */
    private static final int TEXT_MAX_LENGTH_FOUR = 8;

    /**
     * 分割线
     */
    public static final String CUT_LINE = "--------------------------------";

    private static OutputStream outputStream = null;

    public static OutputStream getOutputStream() {
        return outputStream;
    }

    public static void setOutputStream(OutputStream outputStream) {
        BluetoothFormatUtils.outputStream = outputStream;
    }


    /**
     * 打印文字
     *
     * @param text 要打印的文字
     */
    public static void printText(String text) {
        try {
            byte[] data = text.getBytes("gbk");
            outputStream.write(data, 0, data.length);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印换行符
     *
     * @param num 打印换行符的次数
     */
    public static void printNewLine(int num) {
        try {
            for (int i = 0; i < num; i++) {
                String text = "\n";
                byte[] data = text.getBytes("gbk");
                outputStream.write(data, 0, data.length);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印分割线
     */
    public static void printCutLine() {
        try {
            byte[] data = CUT_LINE.getBytes("gbk");
            outputStream.write(data, 0, data.length);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置打印格式
     *
     * @param command 格式指令
     */
    public static void selectCommand(byte[] command) {
        try {
            outputStream.write(command);
            outputStream.flush();
        } catch (IOException e) {
            //Toast.makeText(this.context, "发送失败！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 复位打印机
     */
    public static final byte[] RESET = {0x1b, 0x40};

    /**
     * 左对齐
     */
    public static final byte[] ALIGN_LEFT = {0x1b, 0x61, 0x00};

    /**
     * 中间对齐
     */
    public static final byte[] ALIGN_CENTER = {0x1b, 0x61, 0x01};

    /**
     * 右对齐
     */
    public static final byte[] ALIGN_RIGHT = {0x1b, 0x61, 0x02};

    /**
     * 选择加粗模式
     */
    public static final byte[] BOLD = {0x1b, 0x45, 0x01};

    /**
     * 取消加粗模式
     */
    public static final byte[] BOLD_CANCEL = {0x1b, 0x45, 0x00};

    /**
     * 宽高加倍
     */
    public static final byte[] DOUBLE_HEIGHT_WIDTH = {0x1d, 0x21, 0x11};

    /**
     * 宽加倍
     */
    public static final byte[] DOUBLE_WIDTH = {0x1d, 0x21, 0x10};

    /**
     * 高加倍
     */
    public static final byte[] DOUBLE_HEIGHT = {0x1d, 0x21, 0x01};

    /**
     * 字体不放大
     */
    public static final byte[] NORMAL = {0x1d, 0x21, 0x00};

    /**
     * 设置默认行间距
     */
    public static final byte[] LINE_SPACING_DEFAULT = {0x1b, 0x32};

    /**
     * 设置行间距
     */
//	public static final byte[] LINE_SPACING = {0x1b, 0x32};//{0x1b, 0x33, 0x14};  // 20的行间距（0，255）


//	final byte[][] byteCommands = {
//			{ 0x1b, 0x61, 0x00 }, // 左对齐
//			{ 0x1b, 0x61, 0x01 }, // 中间对齐
//			{ 0x1b, 0x61, 0x02 }, // 右对齐
//			{ 0x1b, 0x40 },// 复位打印机
//			{ 0x1b, 0x4d, 0x00 },// 标准ASCII字体
//			{ 0x1b, 0x4d, 0x01 },// 压缩ASCII字体
//			{ 0x1d, 0x21, 0x00 },// 字体不放大
//			{ 0x1d, 0x21, 0x11 },// 宽高加倍
//			{ 0x1b, 0x45, 0x00 },// 取消加粗模式
//			{ 0x1b, 0x45, 0x01 },// 选择加粗模式
//			{ 0x1b, 0x7b, 0x00 },// 取消倒置打印
//			{ 0x1b, 0x7b, 0x01 },// 选择倒置打印
//			{ 0x1d, 0x42, 0x00 },// 取消黑白反显
//			{ 0x1d, 0x42, 0x01 },// 选择黑白反显
//			{ 0x1b, 0x56, 0x00 },// 取消顺时针旋转90°
//			{ 0x1b, 0x56, 0x01 },// 选择顺时针旋转90°
//	};

    /**
     * 打印两列
     *
     * @param leftText  左侧文字
     * @param rightText 右侧文字
     * @return
     */
    @SuppressLint("NewApi")
    public static String printTwoData(String leftText, String rightText) {
        StringBuilder sb = new StringBuilder();
        int leftTextLength = getBytesLength(leftText);
        int rightTextLength = getBytesLength(rightText);
        sb.append(leftText);

        // 计算两侧文字中间的空格
        int marginBetweenMiddleAndRight = LINE_BYTE_SIZE - leftTextLength - rightTextLength;

        for (int i = 0; i < marginBetweenMiddleAndRight; i++) {
            sb.append(" ");
        }
        sb.append(rightText);
        return sb.toString();
    }

    /**
     * 打印三列
     *
     * @param leftText   左侧文字
     * @param middleText 中间文字
     * @param rightText  右侧文字
     * @return
     */
    @SuppressLint("NewApi")
    public static String printThreeData(String leftText, String middleText, String rightText) {
        StringBuilder sb = new StringBuilder();
        // 左边最多显示 TEXT_MAX_LENGTH_THREE 个汉字， 多出来的换行打印
        String[] leftArray = resetStr(leftText, TEXT_MAX_LENGTH_THREE);

        int leftTextLength = getBytesLength(leftArray[0]);
        int middleTextLength = getBytesLength(middleText);
        int rightTextLength = getBytesLength(rightText);

        sb.append(leftArray[0]);
        // 计算左侧文字和中间文字的空格长度
        int marginBetweenLeftAndMiddle = LEFT_LENGTH - middleTextLength / 2 - leftTextLength ;
        for (int i = 0; i < marginBetweenLeftAndMiddle; i++) {
            sb.append(" ");
        }
        sb.append(middleText);

        // 计算右侧文字和中间文字的空格长度
        int marginBetweenMiddleAndRight = RIGHT_LENGTH - middleTextLength / 2 - rightTextLength;
        for (int i = 0; i < marginBetweenMiddleAndRight; i++) {
            sb.append(" ");
        }
        // 打印的时候发现，最右边的文字总是偏右一个字符，所以需要删除一个空格
        sb.delete(sb.length() - 1, sb.length()).append(rightText);

        // 当最左侧文字超出长度限制，需要换行打印
        if (!TextUtils.isEmpty(leftArray[1])){
            sb.append("\n");
            sb.append(leftArray[1]);
        }
        return sb.toString();
    }

    /**
     * 打印四列
     *
     * @param leftText    左侧文字
     * @param middleText1 中间文字
     * @param middleText2 中间文字
     * @param rightText   右侧文字
     * @return
     */
    @SuppressLint("NewApi")
    public static String printFourData(String leftText, String middleText1, String middleText2, String rightText) {
        StringBuilder sb = new StringBuilder();
        // 左边最多显示 TEXT_MAX_LENGTH_THREE 个汉字， 多出来的换行打印
        String[] leftArray = resetStr(leftText, TEXT_MAX_LENGTH_THREE);

        int leftTextLength = getBytesLength(leftArray[0]);
        int middleTextLength = getBytesLength(middleText1);
        int middleTextLength2 = getBytesLength(middleText2);
        int rightTextLength = getBytesLength(rightText);

        sb.append(leftArray[0]);
        // 计算左侧文字和中间文字的空格长度
        int marginBetweenLeftAndMiddle = LEFT_LENGTH_FOUR - leftTextLength - middleTextLength / 2;
        for (int i = 0; i < marginBetweenLeftAndMiddle; i++) {
            sb.append(" ");
        }
        sb.append(middleText1);

        // 计算中间文字1和中间文字2的空格长度
        int marginBetweenMiddleAndRight = CENTER_LENGTH_FOUR - middleTextLength / 2 - middleTextLength2 / 2 ;
        for (int i = 0; i < marginBetweenMiddleAndRight; i++) {
            sb.append(" ");
        }
        sb.append(middleText2);

        // 计算中间文字2和右侧文字的空格长度
        int marginBetweenMiddleAndRight2 = RIGHT_LENGTH_FOUR - middleTextLength2 / 2 - rightTextLength;
        for (int i = 0; i < marginBetweenMiddleAndRight2; i++) {
            sb.append(" ");
        }
        // 打印的时候发现，最右边的文字总是偏右一个字符，所以需要删除一个空格
        sb.delete(sb.length() - 1, sb.length()).append(rightText);
        // 当最左侧文字超出长度限制，需要换行打印
        if (!TextUtils.isEmpty(leftArray[1])){
            sb.append("\n");
            sb.append(leftArray[1]);
        }
        return sb.toString();
    }

    /**
     * 获取数据长度
     *
     * @param msg
     * @return
     */
    @SuppressLint("NewApi")
    private static int getBytesLength(String msg) {
        return msg.getBytes(Charset.forName("GB2312")).length;
    }

    /**
     * 格式化菜品名称，最多显示MEAL_NAME_MAX_LENGTH个数
     *
     * @param name
     * @return
     */
    public static String formatMealName(String name) {
        if (TextUtils.isEmpty(name)) {
            return name;
        }
        if (name.length() > TEXT_MAX_LENGTH_THREE) {
            return name.substring(0, 8) + "..";
        }
        return name;
    }

    public static String[] resetStr(String val, int maxSize) {
        String[] result = new String[2];
        int length;
        if (TextUtils.isEmpty(val)) {
            result[0] = "";
            return result;
        }
        // 获取原字符串的字节长度
        if (val.length() > maxSize) {
            result[0] = val.substring(0, maxSize);
            result[1] = val.substring(maxSize);
        } else {
            result[0] = val;
        }
        return result;
    }
}
