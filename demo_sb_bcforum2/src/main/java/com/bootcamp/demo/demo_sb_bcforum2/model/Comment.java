package com.bootcamp.demo.demo_sb_bcforum2.model;

import lombok.Getter;

@Getter
public class Comment {
  private Long id;
  private String name;
  private String email;
  private String body;
  private Long postId;
}
