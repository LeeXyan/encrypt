package veryjava.encrypt;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

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
  public void testRSA() throws Exception {

    long begin = System.currentTimeMillis();

    // 获取KeyPair
    KeyPair keyPair = RSAUtil.getKeyPair();

    // 获取PublicKey
    PublicKey publicKey = RSAUtil.string2PublicKey(RSAUtil.getPublicKey(keyPair));

    // 获取PrivateKey
    PrivateKey privateKey = RSAUtil.string2PrivateKey(RSAUtil.getPrivateKey(keyPair));

    // 模拟数据(原文内容)
    JSONObject json = new JSONObject();
    json.put("person", new Person("barton", 27));
    String content = json.toJSONString();

    // 使用私钥对数据进行加密.
    byte[] data = RSAUtil.sign(content.getBytes("utf8"), privateKey);

    // 将加密后的数据用base64包装后转换成字符串
    String dataStr = Base64Util.byte2base64(data);
    System.out.println("dataStr: " + dataStr);

    // 通过http的方式发送请求时,会将content(原文内容),签名(data-dataStr),公玥(publicKey)一起发送.
    // 调用verify方法进行解密验证
    boolean verify = RSAUtil.verify(content.getBytes(), Base64Util.base642byte(dataStr), publicKey);

    System.out.println("verify: " + verify);

    // 验证成功后,content就可以直接使用了.
    System.out.println("server端接收的content:" + content);

    Person p = (Person) json.get("person");
    System.out.println(p.getName());
    System.out.println(p.getAge());

    long end = System.currentTimeMillis();

    System.out.println("消耗时间:" + (end - begin));
  }

  class Person {
    private String name;
    private int age;

    Person() {}

    Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }
}
