package com.shuaibi.zaizaiuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaibi.zaizaicommons.entity.user.User;
import com.shuaibi.zaizaicommons.util.Result;
import com.shuaibi.zaizaiuser.entity.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzp
 * @since 2018-11-27
 */
public interface IUserService {

	Result addUser(User userDto, MultipartFile img) ;

	Result delUser(String[] ids);

	Result modifyUser(User user, MultipartFile img);

	Result queryUserById(String id);
}
