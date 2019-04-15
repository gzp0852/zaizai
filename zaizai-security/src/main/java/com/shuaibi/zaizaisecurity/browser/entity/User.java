package com.shuaibi.zaizaisecurity.browser.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @author gzp
 * @date 2018/12/13 9:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User {
	public int userId;
	public String userCode;
	public String userName;
	public String userNickname;
	public String userStatus;
	public String address;
	public String phonenumber;
	public String email;
	public String password;
	public Timestamp passwordValidityTime;
	public String userdesc;
	public Timestamp lastLoginTime;
	public Timestamp lastLoginIp;
	public String deleted;
	public String cuser;
	public Timestamp cdate;
	public String uuser;
	public Timestamp udate;
}
