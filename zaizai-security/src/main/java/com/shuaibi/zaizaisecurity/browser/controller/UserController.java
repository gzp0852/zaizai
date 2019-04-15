package com.shuaibi.zaizaisecurity.browser.controller;

import com.shuaibi.zaizaisecurity.browser.entity.User;
import com.shuaibi.zaizaisecurity.browser.service.IUserService;
import com.shuaibi.zaizaisecurity.util.Result;
import com.shuaibi.zaizaisecurity.util.ResultCode;
import com.shuaibi.zaizaisecurity.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author gzp
 * @date 2018/12/18 15:06
 */
@RestController
@RequestMapping("/security")
public class UserController {

	@Autowired
	private IUserService iUserService;

	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		return user;
	}

	@PostMapping("/add")
	public Result addUser(@RequestParam("userInfo") User user) {
		if (ObjectUtils.isEmpty(user)) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR);
		}
		Result result = iUserService.addUser(user);
		return result;
	}
}
