package com.shuaibi.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shuaibi.demo.BoxFrameDisc.dao")
public class ZaizaiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaizaiDemoApplication.class, args);
	}

}

