package com.bootcamp.demo.demo_sb_bc_mtr_station.service;

import com.bootcamp.demo.demo_sb_bc_mtr_station.model.dto.ScheduleDTO;

public interface ScheduleService {
  ScheduleDTO getSchedule(String line, String station);
  
  
}
