package com.shuaibi.demo.BoxFrameDisc.bean;

import lombok.Data;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/1/28 10:34
 */
@Data
public class FrameTemplate {
	private String frameId;
	private String frameName;
	private String frameRow;
	private String frameCol;
	private String frameTrend;
	private String frameBegin;
	private Disc discTemplate;
}
