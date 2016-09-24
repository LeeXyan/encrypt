package veryjava.encrypt;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 描述: RSA非对称加密解密算法
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
   */
  public static KeyPair getKeyPair() throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptType.RSA.toString());

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

    return Base64Util.byte2base64(bytes);
  }

  /**
   * 由KeyPair生成私钥
   */
  public static String getPrivateKey(KeyPair keyPair) {
    PrivateKey privateKey = keyPair.getPrivate();
    byte[] bytes = privateKey.getEncoded();
    return Base64Util.byte2base64(bytes);
  }

  /**
   * 将字符串转换成PublicKey
   */
  public static PublicKey string2PublicKey(String pubStr)
   throws Exception {
    byte[] keyBytes = Base64Util.base642byte(pubStr);
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
    byte[] keyBytes = Base64Util.base642byte(priStr);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(EncryptType.RSA.toString());
    PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
    return privateKey;
  }

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
    if (Base64Util.byte2base64(decryptBytes).equals(Base64Util.byte2base64(bytes))) {
      return true;
    }
    return false;
  }
}
