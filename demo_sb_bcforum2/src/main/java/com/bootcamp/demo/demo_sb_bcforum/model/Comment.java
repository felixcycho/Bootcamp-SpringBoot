package com.bootcamp.demo.demo_sb_bcforum.model;

import lombok.Getter;

@Getter
public class Comment {
  private Long id;
  private String name;
  private String email;
  private String body;
  private Long postId;
}
