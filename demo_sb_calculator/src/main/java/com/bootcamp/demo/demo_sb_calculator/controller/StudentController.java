package com.bootcamp.demo.demo_sb_calculator.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_calculator.model.Database;
import com.bootcamp.demo.demo_sb_calculator.model.Student;

@RestController
public class StudentController {
  @PostMapping(value = "/student")
  public Student createStudent(@RequestBody Student student) {
    if (Database.studentDatabase.add(student)) {
      return student;
    }
    return null;
  }

  // ! Put -> assume 100% trust API User
  @PutMapping(value = "/student/{id}")
  public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
    for (int i = 0; i < Database.studentDatabase.size(); i++) {
      if (id.equals(Database.studentDatabase.get(i).getId())) {
        return Database.studentDatabase.set(i, student);
      }
    }
    return null;
  }
  
}
