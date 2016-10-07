package cn.veryjava.encrypt;

import org.apache.commons.codec.DecoderException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 描述: 对称加密算法的DES AES实现
 * 包名: cn.veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-26.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class EncryptUtil {
  /**
   * 私有化构造方法
   */
  private EncryptUtil() {

  }

  /**
   * 获取一个des key
   */
  public static SecretKey getKeyDES() {

    byte[] key;
    try {
      key = HexUtil.decodeHex(new char[]{'1', 'a', '1', '4', '5', 'f', '7', 'e', '9', 'a',
       'b', 'c', 'd', 'e', 'f', '0'});
    } catch (DecoderException e) {
      throw new RuntimeException("DES密钥生成失败");
    }

    SecretKey secretKey = new SecretKeySpec(key, EncryptType.DES.toString());

    return secretKey;
  }

  /**
   * 获取一个aes key
   */
  public static SecretKey getKeyAES() {
    KeyGenerator keyGen;
    try {
      keyGen = KeyGenerator.getInstance(EncryptType.AES.toString());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("AES密钥生成失败");
    }
    keyGen.init(128);
    return keyGen.generateKey();
  }

  /**
   * DES加密
   */
  public static byte[] desEncrypt(byte[] source, SecretKey key) {

    try {
      byte[] bytes;
      Cipher cipher = Cipher.getInstance(EncryptType.DES.toString());
      cipher.init(Cipher.ENCRYPT_MODE, key);
      bytes = cipher.doFinal(source);
      return bytes;
    } catch (Exception e) {
      throw new RuntimeException("DES加密失败");
    }
  }

  /**
   * DES加密
   */
  public static String desEncrypt(String input, SecretKey key) {
    byte[] res;
    try {
      res = desEncrypt(input.getBytes("utf8"), key);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("不支持的encode");
    }
    // 为了防止解密时报javax.crypto.IllegalBlockSizeException: Input length must be multiple of 8 when decrypting with padded cipher异常，
    // 不能把加密后的字节数组直接转换成字符串
    return Base64Util.encodeBase64String(res);
  }

  /**
   * DES解密
   */
  public static byte[] desDecrypt(byte[] source, SecretKey key) {

    try {
      Cipher cipher = Cipher.getInstance(EncryptType.DES.toString());
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] bytes;
      bytes = cipher.doFinal(source);
      return bytes;
    } catch (Exception e) {
      throw new RuntimeException("DES解密失败");
    }
  }

  /**
   * DES解密
   */
  public static String desDecrypt(String input, SecretKey key) {
    byte[] temp = Base64Util.decodeBase64(input);
    byte[] res = desDecrypt(temp, key);
    return new String(res);
  }

  /**
   * AES加密
   */
  public static byte[] aesEncrypt(byte[] input, SecretKey key) {
    try {
      Cipher cipher = Cipher.getInstance(EncryptType.AES.toString());
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] bytes;
      bytes = cipher.doFinal(input);
      return bytes;
    } catch (Exception e) {
      throw new RuntimeException("AES加密失败");
    }
  }

  /**
   * AES加密
   */
  public static String aesEncrypt(String input, SecretKey key) {
    byte[] res;
    try {
      res = aesEncrypt(input.getBytes("utf8"), key);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("不支持的encode");
    }
    // 为了防止解密时报javax.crypto.IllegalBlockSizeException: Input length must be multiple of 8 when decrypting with padded cipher异常，
    // 不能把加密后的字节数组直接转换成字符串
    return Base64Util.encodeBase64String(res);
  }

  /**
   * AES解密
   */
  public static byte[] aesDecrypt(byte[] input, SecretKey key) {
    try {
      Cipher cipher = Cipher.getInstance(EncryptType.AES.toString());
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] bytes;
      bytes = cipher.doFinal(input);
      return bytes;
    } catch (Exception e) {
      throw new RuntimeException("AES解密失败");
    }
  }

  /**
   * AES解密
   */
  public static String aesDecrypt(String input, SecretKey key) {
    byte[] temp = Base64Util.decodeBase64(input);
    byte[] res = aesDecrypt(temp, key);
    return new String(res);
  }

}
