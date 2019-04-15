package com.shuaibi.zaizaisecurity.browser.conf;

import com.shuaibi.zaizaisecurity.browser.authentication.ImoocAuthenticationFailureHandler;
import com.shuaibi.zaizaisecurity.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.shuaibi.zaizaisecurity.core.properties.SecurityProperties;
import com.shuaibi.zaizaisecurity.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author gzp
 * @date 2018/12/13 11:23
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 用自己定义的登录成功
	 */
	@Autowired
	private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

	@Autowired
	private ImoocAuthenticationFailureHandler imoocAuthenticationFailureHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		// 如果有自定义加密 需要自己实现PasswordEncoder
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();

		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
				.formLogin()
				.loginPage("/authentication/require")
				.loginProcessingUrl("/authentication/form")
				.successHandler(imoocAuthenticationSuccessHandler)
				.failureHandler(imoocAuthenticationFailureHandler);

		http.authorizeRequests()
				.antMatchers("/authentication/require",
						securityProperties.getBrowser().getLoginPage(),
						"/code/image").permitAll()
				.anyRequest()
				.authenticated();

		http.csrf().disable();

//		http.csrf().disable();
//		http.authorizeRequests().
//				antMatchers("/static/**").permitAll().anyRequest().authenticated().
//				and().formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler()).
//				and().logout().permitAll().invalidateHttpSession(true).
//				deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler()).
//				and().sessionManagement().maximumSessions(10).expiredUrl("/login");
	}

}
