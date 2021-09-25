package com.shopping.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(value="com.shopping.content.mapper")
public class AppContentMs {

	public static void main(String[] args) {
		SpringApplication.run(AppContentMs.class, args);
	}


	//首先先在这里添加一个这个装配
	//@Bean
	public RedisTemplate getBean(){
		return new RedisTemplate();
	}
}
