package com.bootcamp.demo.demo_sb_database.mapper;

import com.bootcamp.demo.demo_sb_database.entity.UserEntity;
import com.bootcamp.demo.demo_sb_database.model.User;

public class UserMapper {
  public UserEntity map(User user) {
    if (user == null || user.getAddress() == null 
    || user.getAddress().getGeo() == null || user.getCompany() == null) {
      throw new IllegalArgumentException
      ("Invalid user data: Missing required nested fields");
    }
    return UserEntity.builder() 
      .name(user.getName())
      .username(user.getUsername())
      .email(user.getEmail())
      .street(user.getAddress().getStreet())
      .city(user.getAddress().getCity())
      .zipcode(user.getAddress().getZipcode())
      .suite(user.getAddress().getSuite())
      .phone(user.getPhone())
      .website(user.getWebsite())
      .latitude(user.getAddress().getGeo().getLat())
      .longitude(user.getAddress().getGeo().getLng())
      .companyBs(user.getCompany().getBs())
      .companyName(user.getCompany().getName())
      .companyPhrase(user.getCompany().getCatchPhrase())
      .build();
  }

  public User map(UserEntity user) {
    return null;
  }
}