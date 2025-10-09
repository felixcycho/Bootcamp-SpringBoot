package com.bootcamp.demo.demo_sb_bc_mtr_station_redis.service.impl;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.demo_sb_bc_mtr_station_redis.codelib.RedisManager;
import com.bootcamp.demo.demo_sb_bc_mtr_station_redis.entity.LineEntity;
import com.bootcamp.demo.demo_sb_bc_mtr_station_redis.model.dto.ScheduleDTO;
import com.bootcamp.demo.demo_sb_bc_mtr_station_redis.repository.LineRepository;
import com.bootcamp.demo.demo_sb_bc_mtr_station_redis.service.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

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

  // @Autowired
  // private RedisTemplate<String, String> redisTemplate;

  @Autowired
  private RedisManager redisManager;

  @Autowired
  private LineRepository lineRepository;

  // @Autowired
  // private ObjectMapper objectMapper;

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
    // String jsonForRead = this.redisTemplate.opsForValue().get("mtr_lines");
    LineEntity[] lineEntities = this.redisManager.read("mtr_lines", LineEntity[].class);
    // if (jsonForRead != null) {
      // ObjectMapper objectMapper = new ObjectMapper();
    if (lineEntities != null) {
      // ! 1a: Found --> return result
      // LineEntity[] lineEntities 
      // = this.objectMapper.readValue(jsonForRead, LineEntity[].class);       // checked exception
      return Arrays.asList(lineEntities);
    } else {
      // ! 1b: Not found --> Read Database
      // List<LineEntity> lineEntities = this.lineRepository.findAll();
      List<LineEntity> lineEntityList = this.lineRepository.findAll();
      // ! Step 2: Write back to Redis (Set Expiry Timeframe 1 minute)
      // if (!lineEntities.isEmpty()) {
        // ObjectMapper objectMapper = new ObjectMapper();
        if (!lineEntityList.isEmpty()) {
        // String jsonForWrite = this.objectMapper.writeValueAsString(lineEntities);
        // this.redisTemplate.opsForValue().set("mtr_lines", jsonForWrite, Duration.ofMinutes(1L));
        this.redisManager.write("mtr_lines", lineEntityList, Duration.ofMinutes(1L));
      }
      // ! Step 3: Return result
      // return lineEntities;
      return lineEntityList;
    }
  }
}
