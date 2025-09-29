package com.bootcamp.demo.demo_sb_weather.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.bootcamp.demo.demo_sb_weather.dto.DayForecastDTO;

public interface WeatherOperation {

  /* 
   * Get the latest nine days weather predication from database.
   * @return List<DayForecast>
   */
  @GetMapping(value = "/nine_days_weather")
  List<DayForecastDTO> getNineDaysWeather();

  /*
   * Get weather forecasts from external API, and save into database.
   * 
   * @return
   */
  @PostMapping(value = "/weather_forecast")
  List<DayForecastDTO> saveWeatherForecasts();
  
}
