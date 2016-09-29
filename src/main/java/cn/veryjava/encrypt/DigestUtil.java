package cn.veryjava.encrypt;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 描述: 消息摘要算法工具类.继承自org.apache.commons.codec.digest.DigestUtils.方便扩展
 * 包名: cn.veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-26.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class DigestUtil extends org.apache.commons.codec.digest.DigestUtils {
  /**
   * 私有化构造方法
   */
  private DigestUtil() {

  }

  /**
   * hmacMD5
   */
  public static byte[] hmacMD5(byte[] data) {
    try {
      byte[] key = HexUtil.decodeHex(new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
       'e'});

      // 密钥
      SecretKey restoreSecretKey = new SecretKeySpec(key, EncryptType.HmacMD5.toString());
      // 实例化MAC
      Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
      // 初始化MAC
      mac.init(restoreSecretKey);
      // 执行摘要
      byte[] hmacMD5Bytes = mac.doFinal(data);

      return hmacMD5Bytes;

    } catch (Exception e) {
      throw new RuntimeException("hmacMD5摘要失败");
    }
  }

  /**
   * hmacMD5 hex
   */
  public static String hmacMD5Hex(String data) {
    return HexUtil.encodeHexString(hmacMD5(data.getBytes()));
  }

  /**
   * hmacMD5 base64
   */
  public static String hmacMD5Base64(String data) {
    return Base64Util.encodeBase64String(hmacMD5(data.getBytes()));
  }

  /**
   * hmacSHA1
   */
  public static byte[] hmacSHA1(byte[] data) {
    try {
      byte[] key = HexUtil.decodeHex(new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
       'e'});

      // 密钥
      SecretKey restoreSecretKey = new SecretKeySpec(key, EncryptType.HmacSHA1.toString());
      // 实例化MAC
      Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
      // 初始化MAC
      mac.init(restoreSecretKey);
      // 执行摘要
      byte[] hmacSHA1Bytes = mac.doFinal(data);

      return hmacSHA1Bytes;

    } catch (Exception e) {
      throw new RuntimeException("hmacSHA1摘要失败");
    }
  }

  /**
   * hmacSHA1
   */
  public static String hmacSHA1Hex(String data) {
    return HexUtil.encodeHexString(hmacSHA1(data.getBytes()));
  }

  /**
   * hmacSHA1
   */
  public static String hmacSHA1Base64(String data) {
    return Base64Util.encodeBase64String(hmacSHA1(data.getBytes()));
  }
}
