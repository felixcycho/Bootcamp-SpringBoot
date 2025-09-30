package com.bootcamp.demo.demo_sb_weather.service;

import java.util.List;
import com.bootcamp.demo.demo_sb_weather.entity.WeatherForecastEntity;
import com.bootcamp.demo.demo_sb_weather.model.dto.WeatherDTO;

public interface ObservatoryService {
  
  /*
   * Call external API
   * @param weatherDTO
   * @return
   */
  WeatherDTO getNineDaysWeather();

  /*
   * Save into database
   * @param weatherDTO
   * @return
   */
  List<WeatherForecastEntity> saveForecastWeather(WeatherDTO weatherDTO);

  /*
   * Find latest weather forecast from database
   * @return
   */
  List<WeatherForecastEntity> findLatestForecast();
  
}
