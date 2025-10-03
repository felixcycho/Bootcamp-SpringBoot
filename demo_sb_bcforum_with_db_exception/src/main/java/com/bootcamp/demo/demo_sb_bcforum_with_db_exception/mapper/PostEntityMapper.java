package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.entity.PostEntity;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.model.PostDTO;

@Component
public class PostEntityMapper {
  public PostEntity map(PostDTO dto) {
    return PostEntity.builder() //
      .origPostId(dto.getId())
      .title(dto.getTitle()) //
      .body(dto.getBody()) //
      .build();
  }
}
