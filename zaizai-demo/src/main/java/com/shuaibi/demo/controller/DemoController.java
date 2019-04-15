package com.shuaibi.demo.controller;

import com.shuaibi.demo.AopDemo.DemoAnnotation;
import org.springframework.web.bind.annotation.*;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/1/23 15:19
 */
@RestController
public class DemoController {

	@GetMapping("/second/{user}")
	@DemoAnnotation(desc = "second")
	public Object second(@PathVariable String user) {
		return user;
	}
}
