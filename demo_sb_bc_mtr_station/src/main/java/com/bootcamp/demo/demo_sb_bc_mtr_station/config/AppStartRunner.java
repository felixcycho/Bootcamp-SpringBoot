package com.bootcamp.demo.demo_sb_bc_mtr_station.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_sb_bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.demo_sb_bc_mtr_station.repository.LineRepository;

@Component
public class AppStartRunner implements CommandLineRunner {
  @Autowired
  private LineRepository lineRepository;

  @Override
  public void run(String... args) throws Exception {
    List<LineEntity> lineEntities = List.of(
      new LineEntity("TWL", "Tsuen Wan Line"),
      new LineEntity("KTL", "Kwun Tong Line")
    );
    this.lineRepository.saveAll(lineEntities);
  }
  
}
