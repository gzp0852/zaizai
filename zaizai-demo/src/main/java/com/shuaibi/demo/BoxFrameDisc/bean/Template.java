package com.shuaibi.demo.BoxFrameDisc.bean;

import lombok.Data;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/1/28 17:39
 */
@Data
public class Template {
	private String boxId;
	private String boxRow;
	private String boxCol;
	private String boxTrend;
	private String boxBegin;
	private String boxSide;
	private String[] framePositionArr;
	private FrameTemplate frameTemplate;

}
