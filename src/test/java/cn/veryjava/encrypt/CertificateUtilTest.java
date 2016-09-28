package cn.veryjava.encrypt;

import org.junit.Test;

import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 描述: TODO:
 * 包名: cn.veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-28.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class CertificateUtilTest {

  private String password = "testcer";
  private String alias = "testcer";
  private String certificatePath = "/home/barton/testcer.cer";
  private String keyStorePath = "/home/barton/testcer.keystore";// jks.换下后缀名就行
  private String src = "你好,中国!";

  @Test
  public void testCertificate() throws Exception {
    // 1.获得keystore的输入流
    FileInputStream keyStoreInputStream = new FileInputStream(keyStorePath);
    // 2.获得私钥
    PrivateKey privateKey = CertificateUtil.getPrivateKey(keyStoreInputStream, alias, password);
    // 3.将src生成签名
    byte[] sign = CertificateUtil.sign(src.getBytes(), privateKey);

    // 4. 发送给响应方
    // 5.获得证书的输入流
    FileInputStream certificateInputStream = new FileInputStream(certificatePath);
    //6.获得公玥
    PublicKey publicKey = CertificateUtil.getPublicKey(certificateInputStream);
    // 7.使用公玥验证签名
    System.out.println("verify:" + CertificateUtil.verifySign(src.getBytes(), sign, publicKey));
  }
}