package com.demo.CMS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class CmsApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void testRedisService(){
		System.out.println("Inside the testing redis service");
		redisTemplate.opsForValue().set("email","akhil@gmail.com");
		System.out.println("Testing redis service Completed");
	}

}
