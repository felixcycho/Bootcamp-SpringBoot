package com.bootcamp.demo.demo_sb_bcforum4.mapper;

import org.mapstruct.Mapper;
import com.bootcamp.demo.demo_sb_bcforum4.entity.CommentEntity;
import com.bootcamp.demo.demo_sb_bcforum4.model.CommentDTO;

@Mapper(componentModel = "spring")
public interface CommentMapper {
  CommentDTO toDTO(CommentEntity commentEntity);
  CommentEntity toEntity(CommentDTO commentDTO);
}
