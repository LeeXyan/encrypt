package cn.veryjava.encrypt;

import java.util.Map;

/**
 * 描述: 将map按字母顺序排序
 * 包名: cn.veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-28.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class OrderByWordUtil {

  /**
   * 私有化构造方法
   */
  private OrderByWordUtil() {

  }

  public static String order(Map<String, String> map) {
    // 对map的key进行字母排序
    StringBuffer sb = new StringBuffer();
    for (Map.Entry key : map.entrySet()) {
      sb.append(key.getKey() + "=" + key.getValue() + "&");
    }
    final String string = sb.substring(0, sb.length() - 1);

    return string;
  }
}
