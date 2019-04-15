package com.shuaibi.zaizaisecurity.support;

/**
 * @author gzp
 * @date 2018/12/19 10:20
 */
public class SimpleResponse {

	public SimpleResponse(Object content) {
		this.content = content;
	}

	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
