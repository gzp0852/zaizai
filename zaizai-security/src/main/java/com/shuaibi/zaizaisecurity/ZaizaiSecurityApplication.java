package com.shuaibi.zaizaisecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@MapperScan({ "com.shuaibi.zaizaisecurity.browser.mapper" })
@EnableTransactionManagement
public class ZaizaiSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaizaiSecurityApplication.class, args);
	}
}
