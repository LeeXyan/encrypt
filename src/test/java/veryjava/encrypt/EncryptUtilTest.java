package veryjava.encrypt;

import org.junit.Test;

import javax.crypto.SecretKey;

/**
 * 描述: TODO:
 * 包名: veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-9-23.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class EncryptUtilTest {

  @Test
  public void testStringMD5() {
    System.out.println(EncryptUtil.stringMD5("hello world!"));
  }

  @Test
  public void testStringSHA1() {
    System.out.println(EncryptUtil.stringSHA1("hello world!"));
  }

  @Test
  public void testDES() throws Exception {
    SecretKey key = EncryptUtil.getKeyDES();

    String content = "sunshineasbefore";

    byte[] res = EncryptUtil.desEncrypt(content.getBytes("utf8"), key);
    System.out.println(new String(EncryptUtil.desDecrypt(res, key)));

  }

  @Test
  public void testStringDES() throws Exception {
    String content = "sunshineasbefore";
    SecretKey key = EncryptUtil.getKeyDES();
    String res = EncryptUtil.desEncrypt(content, key);
    System.out.println(EncryptUtil.desDecrypt(res, key));
  }

  @Test
  public void testAES() throws Exception {
    SecretKey key = EncryptUtil.getKeyAES();

    String content = "sunshineasbefore";

    byte[] res = EncryptUtil.aesEncrypt(content.getBytes("utf8"), key);

    System.out.println(new String(EncryptUtil.aesDecrypt(res, key)));
  }

  @Test
  public void testStringAES() throws Exception {
    String content = "sunshineasbefore";
    SecretKey key = EncryptUtil.getKeyAES();
    String res = EncryptUtil.aesEncrypt(content, key);
    System.out.println(EncryptUtil.aesDecrypt(res, key));
  }
}
