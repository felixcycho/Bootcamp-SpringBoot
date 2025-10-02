package com.bootcamp.demo.demo_sb_bcforum_without_db.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_bcforum_without_db.controller.ForumAppOperation;
import com.bootcamp.demo.demo_sb_bcforum_without_db.dto.FullDataDto;
import com.bootcamp.demo.demo_sb_bcforum_without_db.dto.FullDataDto2;
import com.bootcamp.demo.demo_sb_bcforum_without_db.mapper.CommentMapper;
import com.bootcamp.demo.demo_sb_bcforum_without_db.mapper.PostMapper;
import com.bootcamp.demo.demo_sb_bcforum_without_db.mapper.UserMapper;
import com.bootcamp.demo.demo_sb_bcforum_without_db.model.CommentDTO;
import com.bootcamp.demo.demo_sb_bcforum_without_db.model.PostDTO;
import com.bootcamp.demo.demo_sb_bcforum_without_db.model.UserDTO;
import com.bootcamp.demo.demo_sb_bcforum_without_db.service.JPHService;

@RestController
public class ForumAppController implements ForumAppOperation {
  @Autowired
  private JPHService jphService;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private PostMapper postMapper;

  @Autowired
  private CommentMapper commentMapper;

  @Override
  public List<FullDataDto> getFullData() {
    List<UserDTO> userDTOs = this.jphService.getAllUsers();
    List<PostDTO> postDTOs = this.jphService.getAllPosts();
    List<CommentDTO> commentDTOs = this.jphService.getAllComments();

    List<FullDataDto> fullDataDtos = new ArrayList<>();
    for (UserDTO userDTO : userDTOs) {
      FullDataDto fullDataDto = this.userMapper.map(userDTO);
      // ! Prepare Post List
      List<FullDataDto.PostDto> postDtos = new ArrayList<>();
      for (PostDTO postDTO : postDTOs) {
        if (userDTO.getId().equals(postDTO.getUserId())) {
          FullDataDto.PostDto postDto = this.postMapper.map(postDTO);
          // ! Prepare Comment List
          List<FullDataDto.PostDto.CommentDto> commentDtos = new ArrayList<>();
          for (CommentDTO commentDTO : commentDTOs) {
            if (postDTO.getId().equals(commentDTO.getPostId())) {
              FullDataDto.PostDto.CommentDto commentDto =
                  this.commentMapper.map(commentDTO);
              commentDtos.add(commentDto);
            }
          }
          // ! Set Comment List to post
          postDto.setComments(commentDtos);
          // ! Add Post to Post List
          postDtos.add(postDto);
        }
      }
      // ! Set Post List to FullData
      fullDataDto.setPosts(postDtos);
      fullDataDtos.add(fullDataDto);
    }
    return fullDataDtos;
  }

  @Override
  public FullDataDto2 getFullData2(Long userId) {
    List<UserDTO> userDTOs = this.jphService.getAllUsers();
    List<PostDTO> postDTOs = this.jphService.getAllPosts();
    List<CommentDTO> commentDTOs = this.jphService.getAllComments();
    
    return userDTOs.stream() //
      .filter(u -> u.getId().equals(userId))
      .map(u -> {
        FullDataDto2 fullDataDto2 = FullDataDto2.builder() //
          .id(u.getId()) // 
          .username(u.getUsername()) //
          .build();
          List<FullDataDto2.CommentDto> commentDtos = commentDTOs.stream() //
            .filter(c -> {
              return postDTOs.stream() //
                .filter(p -> p.getId().equals(c.getPostId()) && p.getUserId().equals(u.getId())) //
                .findAny().isPresent();
            }).map(c -> {
              return FullDataDto2.CommentDto.builder() //
                .name(c.getName())
                .email(c.getEmail())
                .body(c.getBody())
                .build();
            }).collect(Collectors.toList());
          fullDataDto2.setComments(commentDtos);
          return fullDataDto2;
      })
      .findFirst() //
      .orElse(null);
  }
}
