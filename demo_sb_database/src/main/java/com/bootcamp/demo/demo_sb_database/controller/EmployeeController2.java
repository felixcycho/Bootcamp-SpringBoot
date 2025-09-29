package com.bootcamp.demo.demo_sb_database.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_database.entity.EmployeeEntity;
import com.bootcamp.demo.demo_sb_database.repository.EmployeeRepository;

@RestController
public class EmployeeController2 {
  @Autowired
  private EmployeeRepository employeeRepository;

  @PostMapping(value = "/employee2")
  public EmployeeEntity create(@RequestBody EmployeeEntity employeeEntity) {
    return this.employeeRepository.save(employeeEntity);
  }

  @GetMapping(value = "/employees2")
  public List<EmployeeEntity> getAll() {
    return this.employeeRepository.findAll();
  }

  @GetMapping(value = "/employee2/{id}")
  public EmployeeEntity getById(@PathVariable Long id) {
    Optional<EmployeeEntity> employee = this.employeeRepository.findById(id);
    if (employee.isPresent()) {
      return employee.get();
    }
    return null;
  }

  @PutMapping(value = "/employee2/{id}")
  public EmployeeEntity updateById(@PathVariable Long id,
      @RequestBody EmployeeEntity employeeEntity) {
    if (id == null || !id.equals(employeeEntity.getId())) {
      return null;
    }
    return this.employeeRepository.findById(id).map(e -> {
      e.setAge(employeeEntity.getAge());
      e.setJoinDate(employeeEntity.getJoinDate());
      e.setName(employeeEntity.getName());
      e.setSalary(employeeEntity.getSalary());
      EmployeeEntity saved = this.employeeRepository.save(e);
      return saved;
    }).orElse(null);
  }

  @PatchMapping(value = "/employee2/{id}")
  public EmployeeEntity patchSalary(@PathVariable Long id, @RequestParam Double salary) {
    if (id == null)
      return null;
    return this.employeeRepository.findById(id).map(e -> {
      e.setSalary(BigDecimal.valueOf(salary));
      EmployeeEntity saved = this.employeeRepository.save(e);
      return saved;
    }).orElse(null);
  }
  
  @DeleteMapping(value = "/employee2/{id}")
  public void deleteById(@PathVariable Long id) {
    if (id == null)
      return;
    this.employeeRepository.deleteById(id);
  }
}