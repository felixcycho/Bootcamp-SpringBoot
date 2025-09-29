package com.bootcamp.demo.demo_sb_database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_sb_database.entity.EmployeeEntity;

// ! interface, extends JpaRepository
// ! @Repository
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
  
}
