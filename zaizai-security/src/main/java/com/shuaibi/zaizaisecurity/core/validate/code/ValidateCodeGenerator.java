package com.shuaibi.zaizaisecurity.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author gzp
 * @date 2018/12/19 20:37
 */
public interface ValidateCodeGenerator {
	
	ImageCode generate(ServletWebRequest request);
}
