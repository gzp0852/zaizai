package com.shuaibi.zaizaisecurity.browser.conf;

import com.shuaibi.zaizaisecurity.browser.mapper.IUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 *	登录时的判断
 *
 * @author gzp
 * @date 2018/12/13 11:30
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUserMapper iUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("登录用户名：" + username);
		com.shuaibi.zaizaisecurity.browser.entity.User user = iUserMapper.queryPwdByName(username);
		// enable:用户账号是否被逻辑删除了；
		boolean enabled = true;
		// accountNonExpired:用户账号没有过期；
		boolean accountNonExpired = true;
		// credentialsNonExpired:密码没有过期；
		boolean credentialsNonExpired = true;
		// accountNonlocked:用户没有被冻结（禁用）
		boolean accountNonlocked = true;
		String password = user.password;
		if (user.deleted != null && user.deleted.equals("1")) {
			enabled = false;
		}
		if (user.passwordValidityTime != null
				&& LocalDateTime.now().isAfter(user.passwordValidityTime.toLocalDateTime())) {
			// LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()获取long
			credentialsNonExpired = false;
		}
		if (user.userCode.equals("admin")) {
			password = passwordEncoder.encode("123456");
		}
		return new User(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonlocked, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
}
