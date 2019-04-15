package com.shuaibi.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/2/13 15:40
 */
public class MapTest {
	public static void main(String[] args) {
		Map map = new HashMap();
		Map map1 = new HashMap();
		map.put("aa", map1);
	}
}
