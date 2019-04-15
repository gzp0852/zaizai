package com.shuaibi.zaizaicommons.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author gzp
 * @date 2018/11/29 19:42
 */
public class Salt {
	// String hashAlgorithmName = "MD5";
	// // 加密对象(即:密码)
	// Object credentials = "123456";
	// // 盐值(注:一般使用用户名)
	// Object salt = ByteSource.Util.bytes("zhangsan");
	// // 迭代次数
	// int hashIterations = 1024;
	//
	// // 执行加密
	// Object result = new SimpleHash(hashAlgorithmName, credentials, salt,
	/// hashIterations);
	// System.out.println(result);

	public static String  salt(String way, String password, String salt, int hashIterations) {
		Object saltN = ByteSource.Util.bytes(salt);
		Object result  = new SimpleHash(way, password, saltN, hashIterations);
		return result.toString();
	}
}
