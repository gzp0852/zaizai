package com.shuaibi.demo.BoxFrameDisc.bean;

import lombok.Data;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/1/26 16:20
 */
@Data
public class Port {
	private String portId;
	private String discOnlineId;
	private String discOnlineSide;
	private String discOnlineNo;
	private String portCode;

	public Port(String discOnlineId, String discOnlineSide, String discOnlineNo,
			String portCode) {
		this.discOnlineId = discOnlineId;
		this.discOnlineSide = discOnlineSide;
		this.discOnlineNo = discOnlineNo;
		this.portCode = portCode;
	}

	public Port() {
	}
}
