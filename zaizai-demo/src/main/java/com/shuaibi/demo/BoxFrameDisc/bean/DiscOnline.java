package com.shuaibi.demo.BoxFrameDisc.bean;

import lombok.Data;

import java.util.List;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/1/30 15:58
 */
@Data
public class DiscOnline {
	private String id;
	private String deviceId;
	private String discId;
	private String boxSide;
	private String boxNo;
	private String frameNo;
	private List<Port> ports;

	public DiscOnline(String boxSide, String boxNo, String frameNo) {
		this.boxSide = boxSide;
		this.boxNo = boxNo;
		this.frameNo = frameNo;
	}

	public DiscOnline() {
	}
}
