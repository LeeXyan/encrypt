package veryjava.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
    BASE64Encoder base64Encoder = new BASE64Encoder();
    return base64Encoder.encode(bytes);
  }

  /**
   * 将byte数组去除base64包装
   */
  public static byte[] base642byte(String base64) throws IOException {
    BASE64Decoder base64Decoder = new BASE64Decoder();
    return base64Decoder.decodeBuffer(base64);
  }
}
