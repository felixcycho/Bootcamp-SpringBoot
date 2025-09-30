package com.bootcamp.demo.demo_sb_weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
public class DemoWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWeatherApplication.class, args);

		// Optional: Test URL construction (for debugging purposes)
    String weatherURL 
		= UriComponentsBuilder.newInstance()
      .scheme("https")
      .host("data.weather.gov.hk")
      .pathSegment("weatherAPI", "opendata", "weather.php")
      .queryParam("dataType", "fnd")
      .queryParam("lang", "tc")
      .build()
      .toUriString();
    System.out.println("Weather API URL: " + weatherURL);
		
	}

}
