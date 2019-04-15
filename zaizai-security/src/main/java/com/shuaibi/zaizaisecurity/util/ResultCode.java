package com.shuaibi.zaizaisecurity.util;

/**
 * ResultCode
 * 
 * @author gzp
 */
public enum ResultCode {

	/**
	 * 请求成功
	 */
	SUCCESS(0, "请求成功"),

	/**
	 * 请求失败
	 */
	WEAK_NET_WORK(-1, "网络异常，请稍后重试"),
	LOGIN_ERROR(10001, "用户名或密码错误"),
	FAIL(20101, "名称已存在"),
	PARAM_ERROR(20102, "参数错误"),
	INSERT_ERROR(20103, "插入失败"),
	IMG_NOT_EMPTY(20201, "图片为空"),
	IMG_FORMAT_ERROR(20202, "图片类型错误"),
	INSERT_PIC_ERROR(20203, "插入图片失败"),
	PASSWORD_ERROR(20301, "密码不符合规范"),
	USERNAME_ERROR(20302, "用户名不符合规范"),
	LOGINNAME_ERROR(20303, "登录名不符合规范");

	/**
	 * 请求代码
	 */
	private int code;
	private String msg;

	ResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}