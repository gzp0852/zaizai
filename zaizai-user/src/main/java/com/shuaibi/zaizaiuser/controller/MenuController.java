package com.shuaibi.zaizaiuser.controller;

import com.shuaibi.zaizaicommons.util.Result;
import com.shuaibi.zaizaiuser.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gzp
 * @date 2018/12/6 16:29
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private IMenuService iMenuService;

	@GetMapping("getMenu")
	public Result getMenu() {
		Result result = iMenuService.queryMenu();
		return result;
	}
}
