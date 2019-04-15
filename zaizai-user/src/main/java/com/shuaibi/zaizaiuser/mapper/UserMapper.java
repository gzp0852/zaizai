package com.shuaibi.zaizaiuser.mapper;


import com.shuaibi.zaizaicommons.entity.user.User;

import java.util.List; /**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzp
 * @since 2018-11-27
 */
public interface UserMapper {

	int  addUser(User user);

	User queryUserByLoginName(String loginName);

	void delUser(List<Integer> idList);

	User queryUserById(int idI);

	boolean modifyUser(User userDto);
}
