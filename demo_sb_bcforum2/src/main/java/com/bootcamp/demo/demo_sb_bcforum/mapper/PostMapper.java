package com.bootcamp.demo.demo_sb_bcforum.mapper;

import com.bootcamp.demo.demo_sb_bcforum.entity.PostEntity;
import com.bootcamp.demo.demo_sb_bcforum.model.Post;

public class PostMapper {
  public PostEntity map(Post post) {
    if (post == null) {
      throw new IllegalArgumentException
      ("Invalid user data: Missing required nested fields");
    }
    return PostEntity.builder() 
      .origPostId(post.getId())
      .userId(post.getUserId())
      .title(post.getTitle())
      .body(post.getBody())
      .build();
  }
}
