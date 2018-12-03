package com.redis.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author VenkatS
 *
 */
@SpringBootApplication
@MapperScan("com.redis.cache.mapper")
public class RedisCacheApplication{
    public static void main(String[] args) {	
		SpringApplication.run(RedisCacheApplication.class, args);
	}

}
