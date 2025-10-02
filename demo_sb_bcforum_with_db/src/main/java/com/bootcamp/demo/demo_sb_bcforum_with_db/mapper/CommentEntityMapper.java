package com.bootcamp.demo.demo_sb_bcforum_with_db.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_sb_bcforum_with_db.entity.CommentEntity;
import com.bootcamp.demo.demo_sb_bcforum_with_db.model.CommentDTO;

@Component
public class CommentEntityMapper {
  public CommentEntity map(CommentDTO dto) {
    return CommentEntity.builder()
      .name(dto.getName())
      .body(dto.getBody())
      .email(dto.getEmail())
      .build();
  }
}
