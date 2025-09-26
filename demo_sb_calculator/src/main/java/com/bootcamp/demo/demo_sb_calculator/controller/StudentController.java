package com.bootcamp.demo.demo_sb_calculator.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_calculator.model.Database;
import com.bootcamp.demo.demo_sb_calculator.model.Student;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class StudentController {
  @GetMapping("/students")
  public List<Student> getAllStudents() {
    return Database.studentDatabase;
  }
  
  public String getMethodName(@RequestParam String param) {
      return new String();
  }
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
    if (!id.equals(student.getId()))
      return null;
    for (int i = 0; i < Database.studentDatabase.size(); i++) {
      if (id.equals(Database.studentDatabase.get(i).getId())) {
        return Database.studentDatabase.set(i, student);
      }
    }
    return null;
  }

  // ! Patch --> 

  // URL with parameters --> Two Designs
  // 1. Path Variable: http://localhost:8080/student/id/1/name/John
    // @PatchMapping(value = "/student/id/{id}/name/{name}")
    // 當 caller 知道 material 的 location, 指令 system 去到該 location 去 get material.
  // 2. Request Parameter: http://localhost:8080/student?id=1&name=John
    // 當 caller 希望進行 transaction, (e.g. 訂票, 報講座, etc.), 
      // 或者進行 visit 
  

  // @PatchMapping(value = "/student/id/{id}/name/{name}")
  @PatchMapping(value = "/student")
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
