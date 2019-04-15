package com.shuaibi.zaizaieureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 10376
 */
@SpringBootApplication
@EnableEurekaServer
public class ZaizaiEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaizaiEurekaApplication.class, args);
	}
}
