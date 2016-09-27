package cn.veryjava.encrypt;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 描述: TODO:
 * 包名: veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-23.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class RSAUtilTest {

  @Test
  public void testRSA2() throws Exception {
    // 发送方数据
    Person person = new Person("lidandan", 74);
    String requestString = JSON.toJSONString(person);

    // 获得公玥和私钥
    KeyPair keyPair = RSAUtil.getKeyPair();
    PublicKey publicKey = RSAUtil.string2PublicKey(RSAUtil.getPublicKey(keyPair));
    PrivateKey privateKey = RSAUtil.string2PrivateKey(RSAUtil.getPrivateKey(keyPair));

    // 发送方使用公玥加密
    String encryptString = RSAUtil.encryptBase64String(requestString.getBytes(), publicKey);
    System.out.println("发送方使用公玥加密:" + encryptString);

    // 接收方使用私钥解密
    String dencryptString = RSAUtil.dencryptString(encryptString, privateKey);
    System.out.println("接收方使用私钥解密:" + dencryptString);

    Person person1 = JSON.parseObject(dencryptString, Person.class);
    System.out.println(person1);
    System.out.println(person1.getName());
    System.out.println(person1.getAge());
  }

  @Test
  public void testRSA1() throws Exception {

    String src = "Hello World!";
    // 1.初始化密钥
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(512);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    System.out.println("Public Key : " + Base64Util.encodeBase64String(publicKey.getEncoded()));
    System.out.println("Private Key : " + Base64Util.encodeBase64String(privateKey.getEncoded()));

    // 私钥加密,公玥解密--加密
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, privateKey1);
    byte[] result = cipher.doFinal(src.getBytes());
    System.out.println("私钥加密,公玥解密--加密:" + Base64Util.encodeBase64String(result));

    // 私钥加密,公玥解密--解密
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
    keyFactory = KeyFactory.getInstance("RSA");

    PublicKey publicKey1 = keyFactory.generatePublic(x509EncodedKeySpec);
    cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, publicKey1);

    result = cipher.doFinal(result);

    System.out.println("私钥加密,公玥解密--解密:" + new String(result));

    // 公玥加密,私钥解密--加密
    x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
    keyFactory = KeyFactory.getInstance("RSA");
    publicKey1 = keyFactory.generatePublic(x509EncodedKeySpec);

    cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey1);
    result = cipher.doFinal(src.getBytes());
    System.out.println("公玥加密,私钥解密--加密:" + Base64Util.encodeBase64String(result));

    // 公玥加密,私钥解密--解密
    pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
    keyFactory = KeyFactory.getInstance("RSA");

    privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, privateKey1);
    result = cipher.doFinal(result);

    System.out.println("公玥加密,私钥解密--解密:" + new String(result));
  }
}
