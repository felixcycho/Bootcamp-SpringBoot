package com.bootcamp.demo.demo_sb_weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bootcamp.demo.demo_sb_weather.mapper.ForecastMapper;

@Configuration             // ! Component Scan
public class AppConfig {

  @Bean
  ForecastMapper forecastMapper() {
    return new ForecastMapper();
  }
  @Bean
  String tutor() {
    return "Vincent";
  }

}
