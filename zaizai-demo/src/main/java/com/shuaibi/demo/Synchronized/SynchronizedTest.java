package com.shuaibi.demo.Synchronized;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author zepenggao@wistronits.com
 * @date 2019/3/14 21:29
 */
@Component
@Slf4j
public class SynchronizedTest {

	@Synchronized
	public String TestSynchronized(String s) throws InterruptedException {
		log.info("进入锁TestSynchronized-------------" + s);
		System.out.println(Thread.currentThread().getName());
		Thread.sleep(5000);
		return s + s;
	}


	public String TestSynchronized1(String s) throws InterruptedException {
		log.info("进入锁TestSynchronized1111-------------" + s);
		System.out.println(Thread.currentThread().getName());
		Thread.sleep(5000);
		return s + s;
	}
}
