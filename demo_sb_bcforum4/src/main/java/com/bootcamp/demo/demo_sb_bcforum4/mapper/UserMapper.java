package com.bootcamp.demo.demo_sb_bcforum4.mapper;

import org.mapstruct.Mapper;
import com.bootcamp.demo.demo_sb_bcforum4.entity.UserEntity;
import com.bootcamp.demo.demo_sb_bcforum4.model.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDTO toDTO(UserEntity userEntity);
  UserEntity toEntity(UserDTO userDTO);
}
