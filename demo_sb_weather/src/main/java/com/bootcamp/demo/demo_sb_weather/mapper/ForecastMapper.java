package com.bootcamp.demo.demo_sb_weather.mapper;

import com.bootcamp.demo.demo_sb_weather.dto.DayForecastDTO;
import com.bootcamp.demo.demo_sb_weather.entity.WeatherForecastEntity;

public class ForecastMapper {
  public DayForecastDTO map(WeatherForecastEntity weatherForecastEntity) {
    return DayForecastDTO.builder()
      .date(weatherForecastEntity.getDate())
      .maxTemp(weatherForecastEntity.getMaxTemp())
      .minTemp(weatherForecastEntity.getMinTemp())
      .week(weatherForecastEntity.getWeek())
      .description(weatherForecastEntity.getDescription())
      .wind(weatherForecastEntity.getWind())
      .build();
  }
}
