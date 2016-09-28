package cn.veryjava.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
  public static SecretKey getKeyDES() throws Exception {

    byte[] key = HexUtil.decodeHex(new char[]{'1', 'a', '1', '4', '5', 'f', '7', 'e', '9', 'a',
     'b', 'c', 'd', 'e', 'f', '0'});

    SecretKey secretKey = new SecretKeySpec(key, EncryptType.DES.toString());

    return secretKey;
  }

  /**
   * 获取一个aes key
   */
  public static SecretKey getKeyAES() throws Exception {
    KeyGenerator keyGen = KeyGenerator.getInstance(EncryptType.AES.toString());
    keyGen.init(128);
    return keyGen.generateKey();
  }

  /**
   * DES加密
   */
  public static byte[] desEncrypt(byte[] source, SecretKey key) throws Exception {
    Cipher cipher = Cipher.getInstance(EncryptType.DES.toString());
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] bytes = cipher.doFinal(source);
    return bytes;
  }

  /**
   * DES加密
   */
  public static String desEncrypt(String input, SecretKey key) throws Exception {
    byte[] res = desEncrypt(input.getBytes(), key);
    // 为了防止解密时报javax.crypto.IllegalBlockSizeException: Input length must be multiple of 8 when decrypting with padded cipher异常，
    // 不能把加密后的字节数组直接转换成字符串
    return Base64Util.encodeBase64String(res);
  }

  /**
   * DES解密
   */
  public static byte[] desDecrypt(byte[] source, SecretKey key) throws Exception {
    Cipher cipher = Cipher.getInstance(EncryptType.DES.toString());
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] bytes = cipher.doFinal(source);
    return bytes;
  }

  /**
   * DES解密
   */
  public static String desDecrypt(String input, SecretKey key) throws Exception {
    byte[] temp = Base64Util.decodeBase64(input);
    byte[] res = desDecrypt(temp, key);
    return new String(res);
  }

  /**
   * AES加密
   */
  public static byte[] aesEncrypt(byte[] input, SecretKey key) throws Exception {
    Cipher cipher = Cipher.getInstance(EncryptType.AES.toString());
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] bytes = cipher.doFinal(input);
    return bytes;
  }

  /**
   * AES加密
   */
  public static String aesEncrypt(String input, SecretKey key) throws Exception {
    byte[] res = aesEncrypt(input.getBytes(), key);
    // 为了防止解密时报javax.crypto.IllegalBlockSizeException: Input length must be multiple of 8 when decrypting with padded cipher异常，
    // 不能把加密后的字节数组直接转换成字符串
    return Base64Util.encodeBase64String(res);
  }

  /**
   * AES解密
   */
  public static byte[] aesDecrypt(byte[] input, SecretKey key) throws Exception {
    Cipher cipher = Cipher.getInstance(EncryptType.AES.toString());
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] bytes = cipher.doFinal(input);
    return bytes;
  }

  /**
   * AES解密
   */
  public static String aesDecrypt(String input, SecretKey key) throws Exception {
    byte[] temp = Base64Util.decodeBase64(input);
    byte[] res = aesDecrypt(temp, key);
    return new String(res);
  }

}
