package com.bootcamp.demo.demo_sb_bcforum_without_db.model;

import lombok.Getter;

@Getter
public class CommentDTO {
  private Long id;
  private String name;
  private String email;
  private String body;
  private Long postId;
}
