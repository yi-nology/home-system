package com.murphyyi.homesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.murphyyi.homesystem.dao")
public class HomeSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeSystemApplication.class, args);
	}

}
