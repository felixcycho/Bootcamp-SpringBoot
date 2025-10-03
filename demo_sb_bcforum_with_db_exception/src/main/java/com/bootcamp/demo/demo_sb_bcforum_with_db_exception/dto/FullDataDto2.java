package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class FullDataDto2 {
  private Long id;
  private String username;
  @Setter
  private List<CommentDto> comments;
  
  @Getter
  @Builder
  public static class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;
  }
}
