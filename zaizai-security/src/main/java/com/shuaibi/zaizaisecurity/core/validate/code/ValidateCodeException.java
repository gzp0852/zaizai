package com.shuaibi.zaizaisecurity.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author gzp
 * @date 2018/12/19 17:26
 */
public class ValidateCodeException extends AuthenticationException {

	public ValidateCodeException(String msg) {
		super(msg);
	}
}
