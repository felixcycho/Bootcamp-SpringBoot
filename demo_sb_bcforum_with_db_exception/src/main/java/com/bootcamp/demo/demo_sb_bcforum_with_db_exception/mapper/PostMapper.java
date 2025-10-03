package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.dto.FullDataDto;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.model.PostDTO;

@Component
public class PostMapper {
  public FullDataDto.PostDto map(PostDTO postDTO) {
  return FullDataDto.PostDto.builder() //
        .id(postDTO.getId()) //
        .title(postDTO.getTitle()) //
        .body(postDTO.getBody()) //
        .build();
  }
}
