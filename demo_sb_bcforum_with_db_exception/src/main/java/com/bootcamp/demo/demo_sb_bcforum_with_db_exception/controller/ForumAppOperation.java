package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib.GeneralResponse;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.dto.FullDataDto;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.dto.FullDataDto2;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.entity.CommentEntity;

public interface ForumAppOperation {
  // ! Exercise 2: Task 3a
  @GetMapping(value = "/fulldata")
  List<FullDataDto> getFullData();

  // ! Exercise 2: Task 3b
  @GetMapping(value = "/fulldata2")
  GeneralResponse<FullDataDto2> getFullData2(
    @RequestParam(value = "userid") String uid);

  // ! Exercise 3: Task 3
  @GetMapping(value = "/comments")
  GeneralResponse<List<CommentEntity>> getCommentsByPostId (
    @RequestParam(value = "postid") Long id);


}
