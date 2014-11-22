/*
 * @author LuShuWei E-mail:albertxiaoyu@163.com 创建时间 2014-9-24
 */

package com.androidlibrary.lib.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueUtil {

  private static String ValidValuePattern = "^\\d*\\.\\d{2}";
  private static String pattern996 = "^996";

  private static String ValueWithoutDecimalPattern = "^\\d*";

  /**
   * 
   * 对一个数字进行格式化，只取到该数字小数点后的两位
   * 
   * @param floatValue 是浮点数
   * 
   */
  public static String getValueWithTwoDigit(String floatValue) {
    Matcher matcher = Pattern.compile(ValidValuePattern).matcher(floatValue);
    if (matcher.find()) {
      return matcher.group();
    } else {
      return floatValue;
    }

  }

  public static boolean is996Value(String tid) {
    Matcher matcher = Pattern.compile(pattern996).matcher(tid);
    if (matcher.find()) {

      return true;
    } else {
      return false;
    }
  }


  public static String getValueWithoutDecimal(String floatValue) {
    Matcher matcher = Pattern.compile(ValueWithoutDecimalPattern).matcher(floatValue);
    if (matcher.find()) {
      return matcher.group();
    } else {
      return floatValue;
    }
  }

}
