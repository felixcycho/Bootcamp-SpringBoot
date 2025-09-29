package com.bootcamp.demo.demo_sb_weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_sb_weather.entity.WeatherRequestEntity;

@Repository
public interface WeatherRequestRepository 
  extends JpaRepository<WeatherRequestEntity, Long> {

    
}
