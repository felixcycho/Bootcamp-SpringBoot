package com.bootcamp.demo.demo_sb_bc_mtr_station.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.demo_sb_bc_mtr_station.model.dto.ScheduleDTO;
import com.bootcamp.demo.demo_sb_bc_mtr_station.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {
  @Autowired
  private RestTemplate restTemplate;

  // public ScheduleServiceImpl (RestTemplate restTemplate) {
  //   this.restTemplate = restTemplate;
  // }

  @Value(value = "${mtr-service.host}")
  private String mtrHost;

  @Value(value = "${mtr-service.version}")
  private String mtrVersion;

  @Value(value = "${mtr-service.endpoints.schedule}")
  private String scheduleEndpoint;

  @Override
  public ScheduleDTO getSchedule(String line, String station) {
    String scheduleUrl = UriComponentsBuilder.newInstance() //
        .host(mtrHost) //
        .scheme("https") //
        .pathSegment(mtrVersion) //
        .path(scheduleEndpoint) //
        .queryParam("line", line) //
        .queryParam("sta", station) //
        .build() //
        .toUriString();
    System.out.println("scheduleUrl = " + scheduleUrl);
    return this.restTemplate.getForObject(scheduleUrl, ScheduleDTO.class);
  }

}
