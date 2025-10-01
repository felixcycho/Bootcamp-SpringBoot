package com.bootcamp.demo.demo_sb_bcforum4.mapper;

import org.mapstruct.Mapper;
import com.bootcamp.demo.demo_sb_bcforum4.entity.PostEntity;
import com.bootcamp.demo.demo_sb_bcforum4.model.PostDTO;

@Mapper(componentModel = "spring")
public interface PostMapper {
  PostDTO toDTO(PostEntity postEntity);
  PostEntity toEntity(PostDTO postDTO);
}
