package com.bootcamp.demo.demo_sb_bc_mtr_station_redis.service;

import java.util.List;
import com.bootcamp.demo.demo_sb_bc_mtr_station_redis.entity.LineEntity;
import com.bootcamp.demo.demo_sb_bc_mtr_station_redis.model.dto.ScheduleDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ScheduleService {
  ScheduleDTO getSchedule(String line, String station);
  List<LineEntity> getAllLines() throws JsonProcessingException;
  
}
