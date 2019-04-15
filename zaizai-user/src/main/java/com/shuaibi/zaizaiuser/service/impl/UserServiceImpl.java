package com.shuaibi.zaizaiuser.service.impl;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.shuaibi.zaizaicommons.entity.user.User;
import com.shuaibi.zaizaicommons.entity.util.FileEntity;
import com.shuaibi.zaizaicommons.util.*;
import com.shuaibi.zaizaiuser.mapper.UserMapper;
import com.shuaibi.zaizaiuser.service.IUserService;
import com.shuaibi.zaizaiuser.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gzp
 * @since 2018-11-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

	@Autowired
	private UploadUtil uploadUtil;

	@Autowired
	private FastFileStorageClient storageClient;

	@Autowired
	private UserMapper userMapper;

	@Override
	public Result addUser(User userDto, MultipartFile img) {
		if (StringUtils.isEmpty(userDto.getUserName()) || StringUtils.isEmpty(userDto.getPassword())
				|| StringUtils.isEmpty(userDto.getUserRole().getRoleId())) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR);
		}
		// 校验密码
		if (!userDto.checkPassword()) {
			return ResultUtils.warn(ResultCode.PASSWORD_ERROR);
		}
		// 校验用户名
		if (!userDto.checkUserName()) {
			return ResultUtils.warn(ResultCode.USERNAME_ERROR);
		}
		// 校验登录名
		if (!userDto.checkLoginName()) {
			return ResultUtils.warn(ResultCode.LOGINNAME_ERROR);
		}
		// 新增校验登录名
		if (checkLoginName(userDto.getLoginName(), 0)) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "登录名重复");
		}
		// 校验邮箱
		if (!userDto.checkEmail()) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "邮箱不符合规范");
		}
		// 校验手机号
		if (!userDto.checkPhonenumber()) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "手机号不符合规范");
		}
		// 校验备注
		if (!userDto.checkRemark()) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "备注不符合规范");
		}
		// 校验用户头像
		 if (ObjectUtils.isEmpty(img) ) {
			 return ResultUtils.warn(ResultCode.PARAM_ERROR, "头像不能为空");
		 }

		// 校验没有问题，开始上传图片文件，上传失败报错
		try {
			FileEntity upload = uploadUtil.upload(img);
			userDto.setUserImg(upload.getFullPath());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.warn(ResultCode.INSERT_ERROR);
		}

		String salt = RandomUtils.random6();
		userDto.setPassword(Salt.salt("MD5", userDto.getPassword(), salt, 1024));
		userDto.setCreateTime(TimeUtils.nowTimeFormat());
		userDto.setSalt(salt);
		// 创建人可以从前台获取，如果是空值，为自己注册，写自己的名字
		if (StringUtils.isEmpty(userDto.getCreateBy())) {
			userDto.setCreateBy(userDto.getUserName());
		} else {
			userDto.setCreateBy("admin");
		}
		userDto.setUserStatus("1");
		userDto.setDeleted("0");

		if (userMapper.addUser(userDto) < 1) {
			return ResultUtils.warn(ResultCode.INSERT_ERROR, "注册或新增用户失败，稍后重试");
		}
		return ResultUtils.success("注册或新增用户成功");
	}

	@Override
	public Result delUser(String[] ids) {
		List<Integer> idList = new ArrayList<>();
		for (String id : ids) {
			int idI = Integer.parseInt(id);
			idList.add(idI);
			try {
				User user = userMapper.queryUserById(idI);
				storageClient.deleteFile(user.getUserImg());
			} catch (Exception e) {
				e.printStackTrace();
				return ResultUtils.warn(ResultCode.INSERT_ERROR, "删除图片失败");
			}
		}
		userMapper.delUser(idList);
		return ResultUtils.success("删除成功");
	}

	@Override
	public Result modifyUser(User userDto, MultipartFile img) {
		if (StringUtils.isEmpty(userDto.getUserName()) || StringUtils.isEmpty(userDto.getUserId())
				|| StringUtils.isEmpty(userDto.getUserRole().getRoleId())) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR);
		}
		// 校验密码
		// if (!userDto.checkPassword()) {
		// return ResultUtils.warn(ResultCode.PASSWORD_ERROR);
		// }
		// 校验用户名
		if (!userDto.checkUserName()) {
			return ResultUtils.warn(ResultCode.USERNAME_ERROR);
		}
		// 校验登录名
		if (!userDto.checkLoginName()) {
			return ResultUtils.warn(ResultCode.LOGINNAME_ERROR);
		}
		// 修改时校验登录名
		if (checkLoginName(userDto.getLoginName(), userDto.getUserId())) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "登录名重复");
		}
		// 校验邮箱
		if (!userDto.checkEmail()) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "邮箱不符合规范");
		}
		// 校验手机号
		if (!userDto.checkPhonenumber()) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "手机号不符合规范");
		}
		// 校验备注
		if (!userDto.checkRemark()) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "备注不符合规范");
		}
		// 校验用户状态 只能为0, 1
		if (!userDto.checkUserStatus()) {
			return ResultUtils.warn(ResultCode.PARAM_ERROR, "用户状态不符合规范");
		}

		// 校验没有问题，开始删除图片，重新上传文件
		try {
			storageClient.deleteFile(userDto.getUserImg());
			FileEntity upload = uploadUtil.upload(img);
			userDto.setUserImg(upload.getFullPath());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 创建人可以从前台获取，如果是空值，为自己注册，写自己的名字
		if (StringUtils.isEmpty(userDto.getUpdateBy())) {
			userDto.setUpdateBy(userDto.getUserName());
		} else {
			userDto.setUpdateBy("admin");
		}
		userDto.setUpdateTime(TimeUtils.nowTimeFormat());

		userMapper.modifyUser(userDto);
		return ResultUtils.success("修改用户成功");
	}

	@Override
	public Result queryUserById(String id) {
		User user = userMapper.queryUserById(Integer.parseInt(id));
		return ResultUtils.success(user);
	}

	private boolean checkLoginName(String loginName, int userId) {
		User user = userMapper.queryUserByLoginName(loginName);
		if (!ObjectUtils.isEmpty(user)) {
			if (userId == 0) { // 0就是新增的校验
				return true;
			} else {
				if (userId != user.getUserId()) {
					return true;
				}
			}
		}
		return false;
	}

}
