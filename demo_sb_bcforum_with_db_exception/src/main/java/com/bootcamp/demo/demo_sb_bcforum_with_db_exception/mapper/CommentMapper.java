package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.dto.FullDataDto;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.model.CommentDTO;

@Component
public class CommentMapper {
  public FullDataDto.PostDto.CommentDto map(CommentDTO commentDTO) {
    return FullDataDto.PostDto.CommentDto.builder() //
        .id(commentDTO.getId()) //
        .body(commentDTO.getBody()) //
        .email(commentDTO.getEmail()) //
        .name(commentDTO.getName()) //
        .build();
    }
}
