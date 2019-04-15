package com.shuaibi.zaizaiuser.entity;

import lombok.Data;

import java.util.List;

/**
 * @author gzp
 * @date 2018/12/7 10:08
 */
@Data
public class MenuDto {
	private int id;
	private int level;
	private int order;
	private String url;
	private String desc;
	private String name;
	private List<MenuDto> child;
}
