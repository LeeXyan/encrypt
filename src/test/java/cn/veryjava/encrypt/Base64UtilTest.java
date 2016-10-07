package cn.veryjava.encrypt;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
  public void testBase64() throws UnsupportedEncodingException {
    Map map = new HashMap<>();
    map.put("a", "a");
    map.put("b", "b");

    OrderByWordUtil.order(map);

    System.out.println(new JSONObject(map).toJSONString());
    String mapEncodeString = Base64Util.encodeBase64String(new JSONObject(map).toJSONString()
     .getBytes("utf8"));

    System.out.println(new String(Base64Util.decodeBase64(mapEncodeString)));

    String src = "hello world!你好,世界!";

    byte[] encodeBytes = Base64Util.encodeBase64(src.getBytes());
    System.out.println(new String(encodeBytes));

    byte[] dencodeBytes = Base64Util.decodeBase64(encodeBytes);

    System.out.println(new String(dencodeBytes));
  }

  @Test
  public void testTimestamp() throws Exception {
    String encode = Base64Util.encodeBase64String("201609291133473021475137223526328020".getBytes
     ());
    System.out.println(new String(Base64.decodeBase64(encode)));
  }
}