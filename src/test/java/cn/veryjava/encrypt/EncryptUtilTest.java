package cn.veryjava.encrypt;

import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 描述: TODO:
 * 包名: cn.veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-26.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class EncryptUtilTest {
  private static String src = "hello,world!你好,中国!";

  @Test
  public void des() throws Exception {

    // 加密
    SecretKey key = EncryptUtil.getKeyDES();

    byte[] desBytes = EncryptUtil.desEncrypt(src.getBytes(), key);

    byte[] keyBytes = key.getEncoded();

    SecretKey secretKey = new SecretKeySpec(keyBytes, EncryptType.DES.toString());

    // 解密
    System.out.println(new String(EncryptUtil.desDecrypt(desBytes, secretKey)));

  }

  @Test
  public void des1() throws Exception {

    // 加密
    SecretKey key = EncryptUtil.getKeyDES();

    String desString = EncryptUtil.desEncrypt(src, key);

    System.out.println("des encrypt string:" + desString);

    // 解密
    System.out.println("des decrypt string:" + EncryptUtil.desDecrypt(desString, key));
  }


  @Test
  public void aes() throws Exception {
    // 加密
    SecretKey key = EncryptUtil.getKeyAES();

    byte[] aesBytes = EncryptUtil.aesEncrypt(src.getBytes(), key);

    // 解密
    System.out.println(new String(EncryptUtil.aesDecrypt(aesBytes, key)));

  }

  @Test
  public void aes1() throws Exception {

    // 加密
    SecretKey key = EncryptUtil.getKeyAES();
    String aesString = EncryptUtil.aesEncrypt(src, key);

    // 解密
    System.out.println(new String(EncryptUtil.aesDecrypt(aesString, key)));

  }

}