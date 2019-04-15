package com.shuaibi.zaizaisecurity.browser.mapper;

import com.shuaibi.zaizaisecurity.browser.entity.User;

/**
 * @author gzp
 * @date 2018/12/18 14:31
 */
public interface IUserMapper {
	User queryPwdByName(String name);

	void addUser(User user);
}
