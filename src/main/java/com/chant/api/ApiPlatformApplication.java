package com.chant.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 蓝凌官网后台
 *

 **/

@SpringBootApplication
@EnableCaching
@EnableAsync
public class ApiPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPlatformApplication.class, args);
	}

}
