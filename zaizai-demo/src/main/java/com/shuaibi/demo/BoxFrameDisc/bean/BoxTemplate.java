package com.shuaibi.demo.BoxFrameDisc.bean;

import lombok.Data;

import java.util.List;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/1/28 10:30
 */
@Data
public class BoxTemplate {

	private String boxId;
	private String boxName;
	private String boxRow;
	private String boxCol;
	private String boxTrend;
	private String boxBegin;
	private String boxSide;
	private FrameTemplate frameTemplate;
	private List<DiscOnline> discOnlines;

}
