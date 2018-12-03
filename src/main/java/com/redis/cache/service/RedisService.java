package com.redis.cache.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author VenkatS
 *
 */
@Component
public class RedisService {

   @Autowired
   private ObjectMapper objectMapper;

   @Autowired
   @Qualifier("redisUserTemplate")
   private RedisTemplate<String, String> redisUserTemplate;

   public Map<String, Object> saveDataInRedis(String id, Object obj) {
	Map<String, Object> result = new HashMap<>();
	String jsonObj = "";
	try {
		jsonObj = objectMapper.writeValueAsString(obj);
		System.out.println(jsonObj);
	} catch (JsonProcessingException jpe) {

	}
	redisUserTemplate.opsForValue().set(id, jsonObj);
	result.put("isSuccess", true);
	result.put("massage", "Data saved succesfully in redis");
	return result;
   }
}
