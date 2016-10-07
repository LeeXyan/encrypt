package cn.veryjava.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 描述: 数字签名算法工具类.
 * 1. 数字签名遵循私钥签名,公玥验证的规则.
 * 2. 数字签名是一种带有密钥(公玥,私钥)的消息摘要算法.
 * 3. 用来验证数据完整性,认证数据来源和抗否认.
 * 包名: cn.veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-27.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class DigitalSign {
  /**
   * 私有化构造方法
   */
  private DigitalSign() {

  }

  /**
   * MD5withRSA私钥签名
   */
  public static byte[] MD5withRSASignBytes(byte[] content, PrivateKey key) {

    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key.getEncoded());
    KeyFactory keyFactory;
    try {
      keyFactory = KeyFactory.getInstance(EncryptType.RSA.toString());
      PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

      Signature signature = Signature.getInstance(EncryptType.MD5withRSA.toString());
      signature.initSign(privateKey);
      signature.update(content);

      return signature.sign();
    } catch (Exception e) {
      throw new RuntimeException("MD5withRSA私钥签名失败");
    }
  }

  /**
   * MD5withRSA私钥签名
   * @param content 原文内容,不要经过base64.encode处理
   * @param key 私钥
   */
  public static byte[] MD5withRSASignBytes(String content, PrivateKey key) {
    try {
      return MD5withRSASignBytes(content.getBytes("utf8"), key);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("不支持的encode");
    }
  }

  /**
   * MD5withRSA私钥签名
   */
  public static String MD5withRSASignString(byte[] content, PrivateKey key) {
    return Base64Util.encodeBase64String(MD5withRSASignBytes(content, key));
  }

  /**
   * MD5withRSA私钥签名
   */
  public static String MD5withRSASignString(String content, PrivateKey key) {
    return Base64Util.encodeBase64String(MD5withRSASignBytes(content, key));
  }

  /**
   * MD5withRSA使用公玥验证
   */
  public static boolean MD5withRSAVerify(byte[] content, byte[] sign, PublicKey key) {

    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key.getEncoded());
    KeyFactory keyFactory;
    try {
      keyFactory = KeyFactory.getInstance(EncryptType.RSA.toString());
      PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

      Signature signature = Signature.getInstance(EncryptType.MD5withRSA.toString());
      signature.initVerify(publicKey);
      signature.update(content);
      return signature.verify(sign);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * SHA1withRSA使用公玥验证
   */
  public static boolean SHA1withRSAVerify(byte[] content, byte[] sign, PublicKey key) {

    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key.getEncoded());
    KeyFactory keyFactory;
    try {
      keyFactory = KeyFactory.getInstance(EncryptType.RSA.toString());
      PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

      Signature signature = Signature.getInstance(EncryptType.SHA1withRSA.toString());
      signature.initVerify(publicKey);
      signature.update(content);

      return signature.verify(sign);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * SHA1withRSA私钥签名
   */
  public static byte[] SHA1withRSASignBytes(byte[] content, PrivateKey key) {
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key.getEncoded());
    KeyFactory keyFactory;
    Signature signature;
    try {
      keyFactory = KeyFactory.getInstance(EncryptType.RSA.toString());
      PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

      signature = Signature.getInstance(EncryptType.SHA1withRSA.toString());
      signature.initSign(privateKey);
      signature.update(content);
      return signature.sign();
    } catch (Exception e) {
      throw new RuntimeException("SHA1withRSA私钥签名失败");
    }
  }

  /**
   * SHA1withRSA私钥签名
   */
  public static byte[] SHA1withRSASignBytes(String content, PrivateKey key) {
    try {
      return SHA1withRSASignBytes(content.getBytes("utf8"), key);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("不支持的encode");
    }
  }

  /**
   * SHA1withRSA私钥签名
   */
  public static String SHA1withRSASignString(byte[] content, PrivateKey key) {
    return Base64Util.encodeBase64String(SHA1withRSASignBytes(content, key));
  }

  /**
   * SHA1withRSA私钥签名
   */
  public static String SHA1withRSASignString(String content, PrivateKey key) {
    return Base64Util.encodeBase64String(SHA1withRSASignBytes(content, key));
  }
}
