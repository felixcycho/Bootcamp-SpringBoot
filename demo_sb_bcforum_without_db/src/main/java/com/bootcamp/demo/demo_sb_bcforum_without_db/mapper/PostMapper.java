package com.bootcamp.demo.demo_sb_bcforum_without_db.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_sb_bcforum_without_db.dto.FullDataDto;
import com.bootcamp.demo.demo_sb_bcforum_without_db.model.PostDTO;

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
