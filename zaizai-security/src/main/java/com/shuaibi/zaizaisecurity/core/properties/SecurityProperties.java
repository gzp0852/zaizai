package com.shuaibi.zaizaisecurity.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gzp
 * @date 2018/12/19 10:29
 */
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();

	private  ValidateCodeProperties code = new ValidateCodeProperties();

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
}
