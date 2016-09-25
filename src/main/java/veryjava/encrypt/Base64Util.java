package veryjava.encrypt;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * 描述: TODO:
 * 包名: veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-23.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class Base64Util {
  /**
   * 用base64包装byte数组
   */
  public static String byte2base64(byte[] bytes) {
    return Base64.encodeBase64String(bytes);
  }

  /**
   * 将byte数组去除base64包装
   */
  public static byte[] base642byte(String base64) throws IOException {
    return Base64.decodeBase64(base64);
  }
}
