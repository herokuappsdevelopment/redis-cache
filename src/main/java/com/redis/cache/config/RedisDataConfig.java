package com.redis.cache.config;

import java.net.UnknownHostException;
import java.time.Duration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author VenkatS
 *
 */
@Configuration
@EnableCaching
public class RedisDataConfig {
	
	  protected static final int REDIS_PORT = 6379;
	  protected static final String REDIS_HOST = "localhost";


   public static Logger logger = LoggerFactory.getLogger(RedisDataConfig.class);

   @Bean
   public JedisConnectionFactory redisConnectionFactory() throws UnknownHostException {
        
	   JedisPoolConfig poolConfig = new JedisPoolConfig();	
		    poolConfig.setMaxTotal(128);
		    poolConfig.setMaxIdle(128);
		    poolConfig.setMinIdle(16);
		    poolConfig.setTestOnBorrow(true);
		    poolConfig.setTestOnReturn(true);
		    poolConfig.setTestWhileIdle(true);
		    poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
		    poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
		    poolConfig.setNumTestsPerEvictionRun(3);
		    poolConfig.setBlockWhenExhausted(true);
		    
		    

	JedisConnectionFactory factory = new JedisConnectionFactory(poolConfig);
	factory.setHostName("localhost");
	factory.setUsePool(true);
	factory.setPort(6379);
	return factory;
   }

  
   
   @PostConstruct
   public void afterPropertiesSet() {
     System.out.println("####################3  CUSTOM  ##########################333");
   }

   

   @Bean(name = "redisUserTemplate")
   public RedisTemplate<String, String> redisTemplate() throws UnknownHostException {
	RedisTemplate<String, String> template = new RedisTemplate<>();
	template.setConnectionFactory(redisConnectionFactory());
	template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
	template.setKeySerializer(new StringRedisSerializer());
	template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
	template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
	return template;
   }
   
   
   @Bean
   RedisCacheManager redisCacheManager() throws UnknownHostException {
	   
     RedisCacheManager cacheManager = RedisCacheManager.create(redisConnectionFactory());
     
      return cacheManager;
   }

}
