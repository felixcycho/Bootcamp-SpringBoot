package com.bootcamp.demo.demo_sb_bc_mtr_station.service.impl;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.demo_sb_bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.demo_sb_bc_mtr_station.model.dto.ScheduleDTO;
import com.bootcamp.demo.demo_sb_bc_mtr_station.repository.LineRepository;
import com.bootcamp.demo.demo_sb_bc_mtr_station.service.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Autowired
  private LineRepository lineRepository;

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

  // ! Read-Through
  @Override
  public List<LineEntity> getAllLines() throws JsonProcessingException {
    // ! Step 1:  Read Redis
    // ! 1a: Found --> return result
    String jsonForRead = this.redisTemplate.opsForValue().get("mtr_lines");
    if (jsonForRead != null) {
      ObjectMapper objectMapper = new ObjectMapper();
      LineEntity[] lineEntities = objectMapper.readValue(jsonForRead, LineEntity[].class);       // checked exception
      return Arrays.asList(lineEntities);
    } else {
      // ! 1b: Not found --> Read Database
      List<LineEntity> lineEntities = this.lineRepository.findAll();
      // ! Step 2: Write back to Redis (Set Expiry Timeframe 1 minute)
      if (!lineEntities.isEmpty()) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonForWrite = objectMapper.writeValueAsString(lineEntities);
        this.redisTemplate.opsForValue().set("mtr_lines", jsonForWrite, Duration.ofMinutes(1L));
      }
      // ! Step 3: Return result
      return lineEntities;
    }
  }
}
