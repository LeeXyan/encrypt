package cn.veryjava.encrypt;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.MessageDigest;
import java.security.PublicKey;

/**
 * 描述: 数字签名算法工具类.
 * 包名: cn.veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-27.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class DigitalSign {

  /**
   * 使用密钥进行加密
   */
  public static byte[] sign(byte[] content, Key key) throws Exception {
    MessageDigest md = MessageDigest.getInstance(EncryptType.SHA1.toString());
    byte[] bytes = md.digest(content);
    Cipher cipher = Cipher.getInstance(EncryptType.RSA.toString());
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encryptBytes = cipher.doFinal(bytes);
    return encryptBytes;
  }

  /**
   * 使用公玥进行解密并验证
   */
  public static boolean verify(byte[] content, byte[] sign, PublicKey publicKey) throws Exception {
    // 对原文内容进行加密
    MessageDigest md = MessageDigest.getInstance(EncryptType.SHA1.toString());
    byte[] bytes = md.digest(content);

    //对签名进行解密
    Cipher cipher = Cipher.getInstance(EncryptType.RSA.toString());
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    byte[] decryptBytes = cipher.doFinal(sign);

    // 验证
    if (Base64Util.encodeBase64String(decryptBytes).equals(Base64Util.encodeBase64String(bytes))) {
      return true;
    }
    return false;
  }
}
