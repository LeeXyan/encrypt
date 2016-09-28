package cn.veryjava.encrypt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;

/**
 * 数字证书签名/加解密工具类
 * 目前仅支持 SHA1withRSA
 */
public class CertificateUtil {

  /**
   * Java密钥库(Java 密钥库，JKS)KEY_STORE
   */
  public static final String KEY_STORE = "JKS";

  public static final String X509 = "X.509";

  /**
   * 根据密钥库获得私钥
   * @param keyStore 密钥库输入流
   * @param alias 密钥库别名
   * @param password 密钥库密码
   */
  public static PrivateKey getPrivateKey(final InputStream keyStore, final String alias, String password)
   throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException {
    final KeyStore keyStore0 = getKeyStore(keyStore, password);
    if (alias != null) {
      return (PrivateKey) keyStore0.getKey(alias, password.toCharArray());
    } else {
      final Enumeration<String> aliases = keyStore0.aliases();
      return (PrivateKey) keyStore0.getKey(aliases.nextElement(), password.toCharArray());
    }
  }

  /**
   * 根据证书获得公钥
   * @param certificate 证书输入流
   */
  public static PublicKey getPublicKey(InputStream certificate) throws CertificateException {
    return getCertificate(certificate).getPublicKey();
  }

  /**
   * 获得密钥库
   * @param keyStore 密钥库输入流
   * @param password 密钥库密码
   */
  private static KeyStore getKeyStore(final InputStream keyStore, String password)
   throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
    final KeyStore result = KeyStore.getInstance(KEY_STORE);
    result.load(keyStore, password.toCharArray());
    return result;
  }

  /**
   * 获得证书
   * @param certificate 证书输入流
   */
  private static Certificate getCertificate(InputStream certificate) throws CertificateException {
    final CertificateFactory certificateFactory = CertificateFactory.getInstance(X509);
    return certificateFactory.generateCertificate(certificate);
  }

  /**
   * 根据密钥库获得证书
   * @param keyStore 密钥库输入流
   * @param alias 密钥库别名
   * @param password 密钥库密码
   */
  private static Certificate getCertificate(InputStream keyStore, String alias, String password) throws Exception {
    return getKeyStore(keyStore, password).getCertificate(alias);
  }

  /**
   * 使用私钥签名
   * @param keyStoreFilePath keyStore文件目录
   * @param alias 别名
   * @param content 原文内容
   * @param password 密码
   */
  public static byte[] sign(String keyStoreFilePath, String alias, String password, byte[] content)
   throws Exception {
    // 1.获得keystore的输入流
    FileInputStream keyStoreInputStream = new FileInputStream(keyStoreFilePath);
    // 2.获得私钥
    PrivateKey privateKey = CertificateUtil.getPrivateKey(keyStoreInputStream, alias, password);

    return DigitalSign.SHA1withRSASignBytes(content, privateKey);
  }

  /**
   * 使用公玥验证签名
   * @param certificatePath certificate文件目录
   * @param content 原文内容
   * @param sign 签名字节数组
   */
  public static boolean verifySign(String certificatePath, byte[] content, byte[] sign) throws
   Exception {
    // 获得证书的输入流
    FileInputStream certificateInputStream = new FileInputStream(certificatePath);
    // 获得公玥
    PublicKey publicKey = CertificateUtil.getPublicKey(certificateInputStream);

    return DigitalSign.SHA1withRSAVerify(content, sign, publicKey);
  }

  /**
   * 校验证书当前是否有效
   * @param certificate 证书
   */
  public static boolean verifyCertificate(Certificate certificate) {
    return verifyCertificate(new Date(), certificate);
  }

  /**
   * 验证证书是否过期或无效
   * @param date 日期
   * @param certificate 证书
   */
  public static boolean verifyCertificate(Date date, Certificate certificate) {
    try {
      final X509Certificate x509Certificate = (X509Certificate) certificate;
      x509Certificate.checkValidity(date);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 验证数字证书是在给定的日期是否有效
   * @param date 日期
   * @param certificate 证书输入流
   */
  public static boolean verifyCertificate(Date date, InputStream certificate) {
    try {
      return verifyCertificate(getCertificate(certificate));
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 验证数字证书是在给定的日期是否有效
   * @param keyStore 密钥库输入流
   * @param alias 密钥库别名
   * @param password 密钥库密码
   */
  public static boolean verifyCertificate(Date date, InputStream keyStore, String alias, String password) {
    try {
      return verifyCertificate(date, getCertificate(keyStore, alias, password));
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 验证数字证书当前是否有效
   * @param keyStore 密钥库存储路径
   * @param alias 密钥库别名
   * @param password 密钥库密码
   */
  public static boolean verifyCertificate(InputStream keyStore, String alias, String password) {
    return verifyCertificate(new Date(), keyStore, alias, password);
  }

  /**
   * 验证数字证书当前是否有效
   * @param certificate 证书输入流
   */
  public static boolean verifyCertificate(InputStream certificate) {
    return verifyCertificate(new Date(), certificate);
  }

}