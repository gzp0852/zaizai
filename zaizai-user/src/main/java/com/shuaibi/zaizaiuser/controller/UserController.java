package com.shuaibi.zaizaiuser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuaibi.zaizaicommons.entity.goods.Good;
import com.shuaibi.zaizaicommons.entity.user.User;
import com.shuaibi.zaizaicommons.util.Result;
import com.shuaibi.zaizaicommons.util.ResultCode;
import com.shuaibi.zaizaicommons.util.ResultUtils;
import com.shuaibi.zaizaiuser.entity.UserDto;
import com.shuaibi.zaizaiuser.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gzp
 * @since 2018-11-27
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private IUserService userService;

	@ApiOperation(value = "新增用户", notes = "新增一个新的用户")
	@PostMapping("/addUser")
	public Result addUser(@RequestParam("userInfo") String params, @RequestParam("img") MultipartFile img)
			throws IOException {
		if (ObjectUtils.isEmpty(params)) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR);
		}
		User user = mapper.readValue(params, User.class);
		Result result = userService.addUser(user, img);
		return result;
	}

	@PostMapping("delUser")
	public Result delUser(@RequestParam("ids") String[] ids) {
		Result result = userService.delUser(ids);
		return result;
	}

	@PostMapping("modifyUser")
	public Result modifyUser(@RequestParam("userInfo") String params, @RequestParam("img") MultipartFile img)
			throws IOException {
		if (ObjectUtils.isEmpty(params)) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR);
		}
		User user = mapper.readValue(params, User.class);
		Result result = userService.modifyUser(user, img);
		return result;
	}

	@GetMapping("/queryUser/{id}")
	public Result queryUser(@PathVariable("id") String id) {
		Result result = userService.queryUserById(id);
		return result;
	}
}
