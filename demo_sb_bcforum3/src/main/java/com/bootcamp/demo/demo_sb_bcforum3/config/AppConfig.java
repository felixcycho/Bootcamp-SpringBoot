package com.bootcamp.demo.demo_sb_bcforum3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.demo_sb_bcforum3.mapper.PostMapper;
import com.bootcamp.demo.demo_sb_bcforum3.mapper.UserMapper;

@Configuration
public class AppConfig {
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  UserMapper userMapper() {
    return new UserMapper();
  }

  @Bean
  PostMapper PostMapper() {
    return new PostMapper();
  }
}

