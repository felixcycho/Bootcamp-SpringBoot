package com.bootcamp.demo.demo_sb_bc_mtr_station.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_bc_mtr_station.controller.ScheduleOperation;
import com.bootcamp.demo.demo_sb_bc_mtr_station.model.dto.ScheduleDTO;
import com.bootcamp.demo.demo_sb_bc_mtr_station.service.ScheduleService;

@RestController
public class ScheduleController implements ScheduleOperation {
  @Autowired
  private ScheduleService scheduleService;        // Polymorphism

  @Override
  public ScheduleDTO getSchedule(String line, String station) {
    return this.scheduleService.getSchedule(line, station);
  }

}
