package cn.veryjava.encrypt;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * 描述: TODO:
 * 包名: cn.veryjava.encrypt.
 * 作者: barton.
 * 日期: 16-10-27.
 * 项目名称: encrypt
 * 版本: 1.0
 * JDK: since 1.8
 */
public class OrderByWordUtilTest {

  @Test
  public void testOrder(){
    Map<String,String> map = new HashMap<>();

    map.put("result_code","1");
    map.put("result_type","成功");
    map.put("payType","XS");
    map.put("orderNo","20151217181419708");
    map.put("amount","1855.15");

    System.out.println(OrderByWordUtil.order(map));

  }
}