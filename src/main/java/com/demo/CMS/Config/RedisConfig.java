package com.demo.CMS.Config;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, String> redis = new RedisTemplate<>();
        redis.setConnectionFactory(factory);
        redis.setKeySerializer(new StringRedisSerializer());
        redis.setValueSerializer(new StringRedisSerializer());
        return redis;
    }
}
