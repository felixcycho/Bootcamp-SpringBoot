package com.bootcamp.demo.demo_sb_calculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class Student {
  private static long counter = 0L;

  private Long id;
  @Setter
  private String name;
  private Integer age;

  public Student() {
    this.id = ++counter;
  }

  public static void main(String[] args) {
    System.out.println(new Student().getId());
    System.out.println(new Student().getId());
    Student s1 = Student.builder().name("John").age(13).build();
  }
  
}
