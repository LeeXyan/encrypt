package cn.veryjava.encrypt;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 描述: RSA非对称加密解密算法工具类.
 * 包名: veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-23.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class RSAUtil {

  /**
   * 获取KeyPair,用来产生配对的公钥和私钥
   * key长度默认为512位
   */
  public static KeyPair getKeyPair() {
    KeyPairGenerator keyPairGenerator;
    try {
      keyPairGenerator = KeyPairGenerator.getInstance(EncryptType.RSA.toString());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("获取KeyPair失败");
    }
    keyPairGenerator.initialize(512);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();

    return keyPair;
  }

  /**
   * 由KeyPair生成公玥
   */
  public static String getPublicKey(KeyPair keyPair) {
    PublicKey publicKey = keyPair.getPublic();

    byte[] bytes = publicKey.getEncoded();

    return Base64Util.encodeBase64String(bytes);
  }

  /**
   * 由KeyPair生成私钥
   */
  public static String getPrivateKey(KeyPair keyPair) {
    PrivateKey privateKey = keyPair.getPrivate();
    byte[] bytes = privateKey.getEncoded();
    return Base64Util.encodeBase64String(bytes);
  }

  /**
   * 将字符串转换成PublicKey
   */
  public static PublicKey string2PublicKey(String pubStr)
   throws Exception {
    byte[] keyBytes = Base64Util.decodeBase64(pubStr);
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(EncryptType.RSA.toString());
    PublicKey publicKey = keyFactory.generatePublic(keySpec);
    return publicKey;
  }

  /**
   * 将字符串转换成PrivateKey
   */
  public static PrivateKey string2PrivateKey(String priStr)
   throws Exception {
    byte[] keyBytes = Base64Util.decodeBase64(priStr);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(EncryptType.RSA.toString());
    PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
    return privateKey;
  }

  /**
   * 使用私钥或者公玥加密
   */
  public static byte[] encryptBytes(byte[] src, Key key) {
    try {
      Cipher cipher = Cipher.getInstance(EncryptType.RSA.toString());
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return cipher.doFinal(src);
    } catch (Exception e) {
      throw new RuntimeException("RSA使用私钥或者公玥加密失败");
    }
  }

  /**
   * 使用私钥或者公玥加密
   * @param src 原文内容就不要使用base64.encode进行操作了
   */
  public static byte[] encryptBytes(String src, Key key) {
    try {
      Cipher cipher = Cipher.getInstance(EncryptType.RSA.toString());
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return cipher.doFinal(src.getBytes("utf8"));
    } catch (Exception e) {
      throw new RuntimeException("RSA使用私钥或者公玥加密失败");
    }
  }


  /**
   * 使用私钥或者公玥加密
   * @param src 原文内容就不要使用base64.encode进行操作了
   */
  public static String encryptBase64String(byte[] src, Key key) {
    try {
      Cipher cipher = Cipher.getInstance(EncryptType.RSA.toString());
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return Base64Util.encodeBase64String(cipher.doFinal(src));
    } catch (Exception e) {
      throw new RuntimeException("RSA使用私钥或者公玥加密失败");
    }
  }

  /**
   * 使用私钥或者公玥加密
   * @param src 原文内容就不要使用base64.encode进行操作了
   */
  public static String encryptBase64String(String src, Key key) {
    try {
      Cipher cipher = Cipher.getInstance(EncryptType.RSA.toString());
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return Base64Util.encodeBase64String(cipher.doFinal(src.getBytes("utf8")));
    } catch (Exception e) {
      throw new RuntimeException("RSA使用私钥或者公玥加密失败");
    }
  }

  /**
   * 使用公玥或者私钥解密
   * @param src 这个参数必须是经过base64.decode之后的字节数组
   * @param key 可以为PublicKey 也可以为PrivateKey
   */
  public static byte[] dencryptBytes(byte[] src, Key key) {
    try {
      Cipher cipher = Cipher.getInstance(EncryptType.RSA.toString());
      cipher.init(Cipher.DECRYPT_MODE, key);
      return cipher.doFinal(src);
    } catch (Exception e) {
      throw new RuntimeException("RSA使用私钥或者公玥解密失败");
    }
  }

  /**
   * 使用公玥或者私钥解密
   * @param src 这个参数必须是不经过Base64.decode处理的字符串,方法内部会做Base64.decode的处理
   * @param key 可以为PublicKey 也可以为PrivateKey
   */
  public static byte[] dencryptBytes(String src, Key key) {
    try {
      Cipher cipher = Cipher.getInstance(EncryptType.RSA.toString());
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] input = Base64Util.decodeBase64(src);
      return cipher.doFinal(input);
    } catch (Exception e) {
      throw new RuntimeException("RSA使用私钥或者公玥解密失败");
    }
  }

  /**
   * 使用公玥或者私钥解密
   * @param src 这个参数必须是把字符串经过base64.decode之后的字节数组
   * @param key 可以为PublicKey 也可以为PrivateKey
   */
  public static String dencryptString(byte[] src, Key key) {
    try {
      Cipher cipher = Cipher.getInstance(EncryptType.RSA.toString());
      cipher.init(Cipher.DECRYPT_MODE, key);
      return new String(cipher.doFinal(src));
    } catch (Exception e) {
      throw new RuntimeException("RSA使用私钥或者公玥解密失败");
    }
  }

  /**
   * 使用公玥或者私钥解密
   * @param src 这个参数必须是不经过Base64.decode处理的字符串,方法内部会做Base64.decode的处理
   * @param key 可以为PublicKey 也可以为PrivateKey
   */
  public static String dencryptString(String src, Key key) {
    try {
      Cipher cipher = Cipher.getInstance(EncryptType.RSA.toString());
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] input = Base64Util.decodeBase64(src);
      return new String(cipher.doFinal(input));
    } catch (Exception e) {
      throw new RuntimeException("RSA使用私钥或者公玥解密失败");
    }
  }
}
