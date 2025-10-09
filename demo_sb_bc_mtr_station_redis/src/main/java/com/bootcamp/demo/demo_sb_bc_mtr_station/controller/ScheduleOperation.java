package com.bootcamp.demo.demo_sb_bc_mtr_station.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.demo_sb_bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.demo_sb_bc_mtr_station.model.dto.ScheduleDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ScheduleOperation {

  @GetMapping(value = "/mtr/schedule")
  ScheduleDTO getSchedule(@RequestParam String line, @RequestParam String station);

  @GetMapping(value = "/mtr/lines")
  List<LineEntity> getAllLines() throws JsonProcessingException;


}
