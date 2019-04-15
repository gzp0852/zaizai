package com.shuaibi.zaizaicommons.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author gzp
 * @since 2018-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 用户昵称
	 */
	private String userName;

	/**
	 * 登录账号名
	 */
	private String loginName;

	/**
	 * 用户类型
	 */
	private Role userRole;

	/**
	 * 用户邮箱
	 */
	private String userEmail;

	/**
	 * 手机号码
	 */
	private String userPhone;

	/**
	 * 用户性别（-1女 0未知 1男）
	 */
	private String userSex;

	/**
	 * 头像路径
	 */
	private String userImg;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 盐加密
	 */
	private String salt;

	/**
	 * 账号状态（0正常 1停用）
	 */
	private String userStatus;

	/**
	 * 是否删除（0存在 1删除 ）
	 */
	private String deleted;

	/**
	 * 最后登录ip
	 */
	private byte[] loginIp;

	/**
	 * 最后登录时间
	 */
	private String loginDate;

	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 修改人
	 */
	private String updateBy;

	/**
	 * 修改时间
	 */
	private String updateTime;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 用户名校验
	 * @return boolean
	 */
	public boolean checkUserName(){
		String usernameRegex = "^[a-zA-Z0-9_\\-\\u4e00-\\u9fa5\\\" \"]{6,32}$";
		return this.userName.matches(usernameRegex);
	}

	/**
	 * 登录名校验
	 * @return boolean
	 */
	public boolean checkLoginName(){
		String usernameRegex = "^[a-zA-Z0-9_\\-\\u4e00-\\u9fa5\\\" \"]{6,32}$";
		return this.loginName.matches(usernameRegex);
	}

	/**
	 * 用户描述校验
	 *
	 * @return boolean
	 */
	public boolean checkRemark() {
		int maxDesc = 200;
		return (StringUtils.isEmpty(this.remark) || remark.length() <= maxDesc);
	}

	/**
	 * 手机号校验
	 *
	 * @return boolean
	 */
	public boolean checkPhonenumber() {	//"^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";
		String phoneRegex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		return (StringUtils.isEmpty(this.userPhone) || this.userPhone.matches(phoneRegex));
	}

	/**
	 * 邮箱校验
	 *
	 * @return boolean
	 */
	public boolean checkEmail() { //"^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
		String emailRegix = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return (StringUtils.isEmpty(this.userEmail) || this.userEmail.matches(emailRegix));
	}

	/**
	 * 用户状态校验
	 *
	 * @return boolean
	 */
	public boolean checkUserStatus() {
		String open = "1";
		String close = "0";
		return (this.userStatus.equals(open) || this.userStatus.equals(close));
	}

	/**
	 * 密码格式校验
	 *
	 * @return boolean
	 */
	public boolean checkPassword() {
		String pwRegex = "(?!.*[\\u4E00-\\u9FA5\\s])(?!^[a-zA-Z]+$)(?!^[\\d]+$)(?!^[^a-zA-Z\\d]+$)^.{8,16}$";
		return this.password.matches(pwRegex);
	}
}
