package com.bootcamp.demo.demo_sb_bcforum4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
  private Long id;
  private String title;
  private String body;
  private Long userId; // Reference to user
}
