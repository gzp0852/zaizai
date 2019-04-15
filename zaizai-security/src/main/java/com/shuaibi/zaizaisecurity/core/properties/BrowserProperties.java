package com.shuaibi.zaizaisecurity.core.properties;

/**
 * @author gzp
 * @date 2018/12/19 10:30
 */
public class BrowserProperties {

	private String loginPage = "/imooc-signIn.html";

	private LoginType loginType = LoginType.JSON;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}
}
