package com.bootcamp.demo.demo_sb_bc_mtr_station.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.demo_sb_bc_mtr_station.entity.StationEntity;


public interface StationRepository extends JpaRepository<StationEntity, Long> {
  Optional<StationEntity> findByCode(String code);
  
  
}
