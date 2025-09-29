package com.bootcamp.demo.demo_sb_database.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// ! Hibernate: 
// create table employees (
//  id bigint not null auto_increment
//  , e_age integer
//  , e_join_date date, e_name varchar(20)
//  , e_salary decimal(10,2)
//  , primary key (id)
// )

@Entity
@Table(name = "employees")
@Getter
@Setter
public class EmployeeEntity {
  @Id // ! Primary Key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // ! auto_increment
  private Long id;
  @Column(name = "e_name", length = 30) // by default 255
  private String name;
  @Column(name = "e_age") // by default 255
  private Integer age;
  @Column(name = "e_salary", precision = 10, scale = 2)
  private BigDecimal salary;
  @Column(name = "e_join_date")
  private LocalDate joinDate;
}