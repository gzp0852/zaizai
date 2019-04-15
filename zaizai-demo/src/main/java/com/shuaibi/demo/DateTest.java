package com.shuaibi.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/3/1 16:06
 */
public class DateTest {
	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	}
}
