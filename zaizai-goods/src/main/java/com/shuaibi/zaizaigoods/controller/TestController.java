package com.shuaibi.zaizaigoods.controller;

import com.shuaibi.zaizaicommons.entity.goods.Test;
import com.shuaibi.zaizaicommons.util.Result;
import com.shuaibi.zaizaigoods.Service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author gzp
 * @date 2018/12/3 16:12
 */
@RestController
@RequestMapping("/test")//其他的@Api是swagger的东西   如果不用可以和下面一样写
public class TestController {

	@Autowired
	private ITestService iTestService;

	/**
	 * 新增
	 * @param test Test
	 * @return Result
	 */
	@PostMapping("/add")
	public Result addTest(@RequestBody Test test) {//Test在commons里面，作统一返回实体类处理
		Result result =iTestService.addTest(test);
		return result;
	}

	/**
	 * 根据id查
	 * @param testId id
	 * @return Result
	 */
	@GetMapping("/query/{id}")//这里的{id} 和下面的id对应
	public Result queryTest(@PathVariable("id") int testId) {
		Result result = iTestService.queryTest(testId);
		return result;
	}

	/**
	 * 修改
	 * @param test Test
	 * @return Result
	 */
	@PutMapping("/update")	//也可以用PostMapping,规范问题
	public Result updateTest(@RequestBody Test test) {
		Result result = iTestService.updateTest(test);
		return result;
	}

	/**
	 * 批量删除
	 * @param ids id数组
	 * @return Result
	 */
	@DeleteMapping("/delete")
	public Result deleteTest(@RequestBody int[] ids) {	//传过来一个ids的数组
		Result result = iTestService.deleteTest(ids);
		return result;
	}
}
