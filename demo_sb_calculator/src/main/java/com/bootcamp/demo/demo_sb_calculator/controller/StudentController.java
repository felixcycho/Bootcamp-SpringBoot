package com.bootcamp.demo.demo_sb_calculator.controller;

import org.springframework.web.bind.annotation.PatchMapping;
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

  // ! Patch --> 
  @PatchMapping(value = "/student/id/{id}/name/{name}")
  public Student patchStudentName(@PathVariable Long id, @PathVariable String name) {
    for (int i = 0; i < Database.studentDatabase.size(); i++) {
      if (id.equals(Database.studentDatabase.get(i).getId())) {
        Student student = Database.studentDatabase.get(i);
        student.setName(name);
        return Database.studentDatabase.set(i, student);
      }
    }
    return null;
  }
}
