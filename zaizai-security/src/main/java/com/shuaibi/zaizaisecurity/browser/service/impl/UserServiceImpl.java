package com.shuaibi.zaizaisecurity.browser.service.impl;

import com.shuaibi.zaizaisecurity.browser.entity.User;
import com.shuaibi.zaizaisecurity.browser.mapper.IUserMapper;
import com.shuaibi.zaizaisecurity.browser.service.IUserService;
import com.shuaibi.zaizaisecurity.util.Result;
import com.shuaibi.zaizaisecurity.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gzp
 * @date 2018/12/18 15:06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserMapper iUserMapper;

	@Override
	public Result addUser(User user) {
		iUserMapper.addUser(user);
		return ResultUtils.success(null);
	}
}
