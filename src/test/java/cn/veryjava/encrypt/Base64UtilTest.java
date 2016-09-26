package cn.veryjava.encrypt;

import org.junit.Test;

/**
 * 描述: TODO:
 * 包名: cn.veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-26.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class Base64UtilTest {
  @Test
  public void testBase64() {
    String src = "hello world!你好,世界!";

    byte[] encodeBytes = Base64Util.encodeBase64(src.getBytes());
    System.out.println(new String(encodeBytes));

    byte[] dencodeBytes = Base64Util.decodeBase64(encodeBytes);

    System.out.println(new String(dencodeBytes));

  }
}