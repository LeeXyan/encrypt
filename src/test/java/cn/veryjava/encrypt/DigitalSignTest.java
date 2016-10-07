package cn.veryjava.encrypt;

import org.junit.Test;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述: TODO:
 * 包名: cn.veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-27.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class DigitalSignTest {

  @Test
  public void test() throws Exception {
    String src = "Hello World!";
    //1.初始化密钥
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(512);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();

    RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

    //2.执行签名
    // 用私钥进行加密
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

    Signature signature = Signature.getInstance("MD5withRSA");
    signature.initSign(privateKey);
    signature.update(src.getBytes());
    byte[] sign = signature.sign();

    System.out.println("jdk rsa sign :" + Base64Util.encodeBase64String(sign));

    // 3. 签名验证
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
    keyFactory = KeyFactory.getInstance("RSA");
    PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

    signature = Signature.getInstance("MD5withRSA");
    signature.initVerify(publicKey);
    signature.update(src.getBytes());
    boolean verify = signature.verify(sign);
    System.out.println("jdk rsa verify:" + verify);
  }

  @Test
  public void testMD5withRSA() throws Exception {
    String src = "你好,中国!";
    //1.初始化密钥
    KeyPair keyPair = RSAUtil.getKeyPair();

    RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

    // 加密
    byte[] encryptBytes = DigitalSign.MD5withRSASignBytes(src.getBytes(), rsaPrivateKey);
    String signString = Base64Util.encodeBase64String(encryptBytes);
    System.out.println("md5 with rsa sign: " + signString);

    // 传递src&signString.
    // 验证签名
    boolean verify = DigitalSign.MD5withRSAVerify(src.getBytes(), Base64Util.decodeBase64
     (signString), rsaPublicKey);
    System.out.println("verify: " + verify);
  }

  @Test
  public void testSHA1withRSA() throws Exception {

    String src = "你好,中国!";

    // 初始化密钥
    KeyPair keyPair = RSAUtil.getKeyPair();

    RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

    // 加密
    byte[] encryptBytes = DigitalSign.SHA1withRSASignBytes(src.getBytes(), rsaPrivateKey);
    String signString = Base64Util.encodeBase64String(encryptBytes);

    System.out.println("sha1 with rsa sign:" + signString);

    // 传递src&signString
    // 验签
    boolean verify = DigitalSign.SHA1withRSAVerify(src.getBytes(), Base64Util.decodeBase64
     (signString), rsaPublicKey);

    System.out.println("verify:" + verify);
  }

}