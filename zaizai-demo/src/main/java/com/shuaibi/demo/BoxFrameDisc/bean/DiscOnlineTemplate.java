package com.shuaibi.demo.BoxFrameDisc.bean;

import lombok.Data;

import java.util.List;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/1/30 16:25
 */
@Data
public class DiscOnlineTemplate {
	private String discId;
	private String boxSide;
	private String boxNo;
	private String frameNo;

	public DiscOnlineTemplate(String boxSide, String boxNo, String frameNo) {
		this.boxSide = boxSide;
		this.boxNo = boxNo;
		this.frameNo = frameNo;
	}

	public DiscOnlineTemplate() {
	}
}
