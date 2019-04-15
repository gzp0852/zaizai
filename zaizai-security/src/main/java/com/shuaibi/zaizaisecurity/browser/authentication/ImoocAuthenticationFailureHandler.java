package com.shuaibi.zaizaisecurity.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuaibi.zaizaisecurity.core.properties.LoginType;
import com.shuaibi.zaizaisecurity.core.properties.SecurityProperties;
import com.shuaibi.zaizaisecurity.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败操作器
 *
 * @author gzp
 * @date 2018/12/19 14:07
 */
@Component("imoocAuthenticationFailureHandler")
public class ImoocAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, AuthenticationException e)
			throws IOException, ServletException {
		logger.info("登陆失败");
		if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());		//500
			httpServletResponse.setContentType("application/json;charset=UTF-8");
			httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
		} else {
			super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
		}

	}
}
