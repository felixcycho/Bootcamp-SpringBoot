package com.bootcamp.demo.demo_sb_bc_mtr_station.config;

import java.util.List;
import java.util.stream.Collectors;
import com.bootcamp.demo.demo_sb_bc_mtr_station.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_sb_bc_mtr_station.controller.impl.ScheduleController;
import com.bootcamp.demo.demo_sb_bc_mtr_station.entity.LineEntity;
import com.bootcamp.demo.demo_sb_bc_mtr_station.model.dto.ScheduleDTO;
import com.bootcamp.demo.demo_sb_bc_mtr_station.repository.LineRepository;

@Component
public class AppStartRunner implements CommandLineRunner {

  @Autowired
  private ScheduleController scheduleController;

  @Autowired
  private LineRepository lineRepository;
  
  @Autowired
  private StationRepository stationRepository;

  @Override
  public void run(String... args) throws Exception {
    this.lineRepository.deleteAll();
    this.stationRepository.deleteAll();

    List<LineEntity> lineEntities = List.of(
      new LineEntity("TWL", "Tsuen Wan Line"),
      new LineEntity("KTL", "Kwun Tong Line"),
      new LineEntity("AEL", "Airport Express"),
      new LineEntity("TCL", "Tung Chung Line"),
      new LineEntity("TML", "Tuen Ma Line"),
      new LineEntity("TKL", "Tseung Kwan O Line"),
      new LineEntity("EAL", "East Rail Line"),
      new LineEntity("SIL", "South Island Line"),
      new LineEntity("ISL", "Island Line")
    );

    this.lineRepository.saveAll(lineEntities);

 
  }
  
}
