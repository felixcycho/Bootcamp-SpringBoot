package com.bootcamp.demo.demo_sb_bcforum_with_db.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.demo_sb_bcforum_with_db.dto.FullDataDto;
import com.bootcamp.demo.demo_sb_bcforum_with_db.dto.FullDataDto2;

public interface ForumAppOperation {
  // ! Exercise 2: Task 3a
  @GetMapping(value = "/fulldata")
  List<FullDataDto> getFullData();

  // ! Exercise 2: Task 3b
  @GetMapping(value = "/fulldata2")
  FullDataDto2 getFullData2(@RequestParam(value = "userid") Long userId);
}
