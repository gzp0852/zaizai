package com.shuaibi.zaizaicommons.entity.user;

import lombok.Data;

/**
 * @author gzp
 * @date 2018/12/6 16:39
 */
@Data
public class Menu {
	private int id;
	private String name;
	private int fatherId;
	private int level;
	private int order;
	private String url;
	private String desc;
}
