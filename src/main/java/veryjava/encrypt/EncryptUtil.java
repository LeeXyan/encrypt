package veryjava.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述: 利用java security api 实现 MD5和SHA1方式的消息摘要
 * 包名: veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-23.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class EncryptUtil {

  /**
   * 将字符串input以md5的形式进行消息摘要并返回摘要后的字符串
   * @param input 原文
   * @return 消息摘要
   */
  public static String stringMD5(String input) {

    try {
      // 拿到一个MD5转换器
      MessageDigest messageDigest = MessageDigest.getInstance(EncryptType.MD5.toString());

      // 输入的字符串转换成字节数组
      byte[] inputByteArray = input.getBytes("utf8");

      // inputByteArray是输入字符串转换得到的字节数组
      byte[] result = messageDigest.digest(inputByteArray);

      return Base64Util.byte2base64(result);
    } catch (NoSuchAlgorithmException e) {
      return null;
    } catch (UnsupportedEncodingException e) {
      return null;
    }
  }

  /**
   * 将字符串input以SHA1的形式进行消息摘要并返回摘要后的字符串
   * @param input 原文
   * @return 消息摘要
   */
  public static String stringSHA1(String input) {

    try {
      // 拿到一个SHA1转换器
      MessageDigest messageDigest = MessageDigest.getInstance(EncryptType.SHA1.toString());

      // 输入的字符串转换成字节数组
      byte[] inputByteArray = input.getBytes("utf8");

      // inputByteArray是输入字符串转换得到的字节数组
      byte[] result = messageDigest.digest(inputByteArray);

      return Base64Util.byte2base64(result);
    } catch (NoSuchAlgorithmException e) {
      return null;
    } catch (UnsupportedEncodingException e) {
      return null;
    }
  }

  /**
   * 获取一个des key
   */
  public static SecretKey getKeyDES() throws Exception {
    KeyGenerator keyGen = KeyGenerator.getInstance(EncryptType.DES.toString());
    keyGen.init(56);
    SecretKey key = keyGen.generateKey();
    return key;
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
    byte[] temp = Base64Util.base642byte(input);
    byte[] res = desEncrypt(temp, key);
    // 为了防止解密时报javax.crypto.IllegalBlockSizeException: Input length must be multiple of 8 when decrypting with padded cipher异常，
    // 不能把加密后的字节数组直接转换成字符串
    return Base64Util.byte2base64(res);
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
    byte[] temp = Base64Util.base642byte(input);
    byte[] res = desDecrypt(temp, key);
    return Base64Util.byte2base64(res);
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
    byte[] temp = Base64Util.base642byte(input);
    byte[] res = aesEncrypt(temp, key);
    // 为了防止解密时报javax.crypto.IllegalBlockSizeException: Input length must be multiple of 8 when decrypting with padded cipher异常，
    // 不能把加密后的字节数组直接转换成字符串
    return Base64Util.byte2base64(res);
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
    byte[] temp = Base64Util.base642byte(input);
    byte[] res = aesDecrypt(temp, key);
    return Base64Util.byte2base64(res);
  }

}
