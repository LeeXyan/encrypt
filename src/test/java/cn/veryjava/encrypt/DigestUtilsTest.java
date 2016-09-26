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
public class DigestUtilsTest {

  private String src = "你好,世界! Hello World!";

  @Test
  public void testMd5() throws Exception {
    System.out.println(DigestUtil.md5Hex(src.getBytes()));
  }

  @Test
  public void testMd51() throws Exception {
    System.out.println(DigestUtil.md5Hex(src));
  }

  @Test
  public void sha1Hex() throws Exception {
    System.out.println(DigestUtil.sha1Hex(src.getBytes()));
  }

  @Test
  public void sha1Hex1() throws Exception {
    System.out.println(DigestUtil.sha1Hex(src));
  }

  @Test
  public void hmacMd5() throws Exception {
    System.out.println(HexUtil.encodeHexString(DigestUtil.hmacMD5(src.getBytes())));
    System.out.println(DigestUtil.hmacMD5Hex(src));
    System.out.println(DigestUtil.hmacMD5Base64(src));
  }

  @Test
  public void hmacSHA1() throws Exception {
    System.out.println(DigestUtil.hmacSHA1Hex(src));
    System.out.println(HexUtil.encodeHexString(DigestUtil.hmacSHA1(src.getBytes())));
    System.out.println(DigestUtil.hmacSHA1Base64(src));
  }
}