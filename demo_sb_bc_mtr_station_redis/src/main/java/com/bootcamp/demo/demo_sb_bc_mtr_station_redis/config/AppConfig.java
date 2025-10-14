package com.bootcamp.demo.demo_sb_bc_mtr_station_redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.demo_sb_bc_mtr_station_redis.codelib.RedisManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class AppConfig {
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();        // 未必 stateless, so have risk.
  }

  // ! pom.xml -> class
  // RedisTemplate<String, String> --> key(String), value(String)
  // ! Redis is something like HashMap.
  // @Bean
  // RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
  //   RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
  //   redisTemplate.setConnectionFactory(factory);
  //   redisTemplate.setKeySerializer(RedisSerializer.string());
  //   redisTemplate.setValueSerializer(RedisSerializer.json());
  //   redisTemplate.afterPropertiesSet();
  //   return redisTemplate;
  // }

  @Bean
  RedisManager redisManager(RedisConnectionFactory factory, ObjectMapper objectMapper) {
    return new RedisManager(factory, objectMapper);
  }

  // ObjectMapper object is stateless ?!
  // f(x) = y
  @Bean
  ObjectMapper objectMapper() {
    // return new ObjectMapper();           // stateless
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return objectMapper;
  }

}
