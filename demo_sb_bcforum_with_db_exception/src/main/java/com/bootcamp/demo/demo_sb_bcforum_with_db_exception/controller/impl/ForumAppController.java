package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib.GeneralResponse;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib.NotFoundException;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.codelib.SysCode;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.controller.ForumAppOperation;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.dto.FullDataDto;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.dto.FullDataDto2;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.entity.CommentEntity;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.entity.UserEntity;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.mapper.CommentMapper;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.mapper.PostMapper;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.mapper.UserMapper;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.model.CommentDTO;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.model.PostDTO;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.model.UserDTO;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.repository.UserRepository;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.service.JPHService;

@RestController
public class ForumAppController implements ForumAppOperation {

    private final UserRepository userRepository;
  @Autowired
  private JPHService jphService;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private PostMapper postMapper;

  @Autowired
  private CommentMapper commentMapper;

    ForumAppController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
  public GeneralResponse<FullDataDto2> getFullData2(String uid) {

    // ! happy flow (try-catch is not required)
    Long userId = Long.valueOf(uid);               // throw NumberFormatException

    List<UserDTO> userDTOs = this.jphService.getAllUsers();
    List<PostDTO> postDTOs = this.jphService.getAllPosts();
    List<CommentDTO> commentDTOs = this.jphService.getAllComments();
    
    FullDataDto2 fullDataDto 
    = userDTOs.stream()
      .filter(u -> u.getId().equals(userId))
      .map(u -> {
        FullDataDto2 fullDataDto2 = FullDataDto2.builder() //
          .id(u.getId()) // 
          .username(u.getUsername()) //
          .build();
        List<FullDataDto2.CommentDto> commentDtos 
        = commentDTOs.stream() //
          .filter(c -> {
            return postDTOs.stream() //
              .filter(p -> p.getId().equals(c.getPostId()) 
                && p.getUserId().equals(u.getId())) //
              .findAny().isPresent();
            })
            .map(c -> {
              return FullDataDto2.CommentDto.builder() //
                .name(c.getName())
                .email(c.getEmail())
                .body(c.getBody())
                .build();
             })
             .collect(Collectors.toList());
        fullDataDto2.setComments(commentDtos);
        return fullDataDto2;
      })
      .findFirst() //
      .orElseThrow(() -> new NotFoundException(SysCode.ID_NOT_FOUND));
    return GeneralResponse.<FullDataDto2>builder() //
        // .code(0)
        // .message("OK.")
        .ok()
        .data(fullDataDto)
        .build();
  }

  @Override
  public GeneralResponse<List<CommentEntity>> getCommentsByPostId(Long id) {
    List<CommentEntity> commentEntities 
    = this.jphService.getCommentsByPostId(id);
    return GeneralResponse.<List<CommentEntity>>builder()
      .ok()
      .data(commentEntities)
      .build();
  }

  @Override
  public List<UserEntity> getUsersByName(String name) {
    return this.userRepository.findUsersByName(name);
  }
}
