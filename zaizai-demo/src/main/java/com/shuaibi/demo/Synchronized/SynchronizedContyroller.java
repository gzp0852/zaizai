package com.shuaibi.demo.Synchronized;

import com.shuaibi.zaizaicommons.util.Result;
import com.shuaibi.zaizaicommons.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zepenggao@wistronits.com
 * @date 2019/3/14 21:34
 */

@RestController
@RequestMapping("/synchronized")
public class SynchronizedContyroller {

	@Autowired
	private SynchronizedTest synchronizedTest;

	@PostMapping("/test")
	public Result Test(@RequestBody String s) throws InterruptedException, ExecutionException {
//		FutureTask futureTask = new FutureTask(new Callable() {
//			@Override
//			public Object call() throws Exception {
//				String a = synchronizedTest.TestSynchronized(s);
//				return a;
//			}
//		});
//		Thread thread = new Thread(futureTask);
//		thread.start();
//		System.out.println("返回结果----------------" + futureTask.get());
//		return ResultUtils.success(futureTask.get());
		String a = synchronizedTest.TestSynchronized(s);
		System.out.println("返回结果----------------" + a);
		return ResultUtils.success(a);
	}

	@PostMapping("/test1")
	public Result Test1(@RequestBody String s) throws InterruptedException {
		String a = synchronizedTest.TestSynchronized1(s);
		System.out.println("返回结果111----------------" + a);
		return ResultUtils.success(a);
	}
}
