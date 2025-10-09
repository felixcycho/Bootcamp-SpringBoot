package com.bootcamp.demo.demo_sb_bc_mtr_station.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_bc_mtr_station.controller.ScheduleOperation;
import com.bootcamp.demo.demo_sb_bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.demo_sb_bc_mtr_station.model.dto.ScheduleDTO;
import com.bootcamp.demo.demo_sb_bc_mtr_station.service.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ScheduleController implements ScheduleOperation {
  @Autowired
  private ScheduleService scheduleService;        // Polymorphism

  @Override
  public ScheduleDTO getSchedule(String line, String station) {
    return this.scheduleService.getSchedule(line, station);
  }

  @Override
  public List<LineEntity> getAllLines() throws JsonProcessingException {
    return this.scheduleService.getAllLines();
  }

}
