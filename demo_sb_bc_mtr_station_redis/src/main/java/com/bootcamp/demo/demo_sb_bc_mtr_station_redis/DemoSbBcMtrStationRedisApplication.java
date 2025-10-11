package com.bootcamp.demo.demo_sb_bc_mtr_station_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoSbBcMtrStationRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSbBcMtrStationRedisApplication.class, args);
	}

}
