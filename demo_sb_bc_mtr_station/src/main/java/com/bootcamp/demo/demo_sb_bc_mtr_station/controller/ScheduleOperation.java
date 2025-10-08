package com.bootcamp.demo.demo_sb_bc_mtr_station.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.demo_sb_bc_mtr_station.model.dto.ScheduleDTO;

public interface ScheduleOperation {

  @GetMapping(value = "/mtr/schedule")
  ScheduleDTO getSchedule(@RequestParam String line, @RequestParam String station);
}
