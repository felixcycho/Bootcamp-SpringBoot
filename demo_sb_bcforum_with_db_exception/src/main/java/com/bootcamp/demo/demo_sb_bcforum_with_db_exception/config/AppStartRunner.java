package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.config;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.entity.CommentEntity;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.entity.PostEntity;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.entity.UserEntity;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.mapper.CommentEntityMapper;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.mapper.PostEntityMapper;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.mapper.UserEntityMapper;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.repository.CommentRepository;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.repository.PostRepository;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.repository.UserRepository;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.service.JPHService;

// ! Exercise 3 Task 2 - CommandLineRunner
@Component
public class AppStartRunner implements CommandLineRunner {

  @Autowired
  private JPHService jphService;
  @Autowired
  private UserEntityMapper userEntityMapper;
  @Autowired
  private PostEntityMapper postEntityMapper;
  @Autowired
  private CommentEntityMapper commentEntityMapper;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CommentRepository commentRepository;

  @Override
  public void run(String... args) throws Exception {
    this.commentRepository.deleteAll();
    this.postRepository.deleteAll();
    this.userRepository.deleteAll();

    // call API, save users, posts, comments into database
    List<UserEntity> userEntities  
    = this.jphService.getAllUsers().stream()
      .map(u -> this.userEntityMapper.map(u))
      .collect(Collectors.toList());

    // ! Convert from List<PostDTO> to List<PostEntity>
    List<PostEntity> postEntities
    = this.jphService.getAllPosts().stream()
      .map(p -> {
         PostEntity postEntity = this.postEntityMapper.map(p);
         UserEntity userEntity = userEntities.stream()
           .filter(u -> u.getOrigUserId().equals(p.getUserId()))
           .findAny()
           .orElse(null);
         postEntity.setUserEntity(userEntity);
         return postEntity;
       }).collect(Collectors.toList());

    List<CommentEntity> commentEntities
    = this.jphService.getAllComments().stream()
      .map(c -> {
        CommentEntity commentEntity = this.commentEntityMapper.map(c);
        PostEntity postEntity = postEntities.stream()
          .filter(p -> p.getOrigPostId().equals(c.getPostId())).findAny()
          .orElse(null);
        commentEntity.setPostEntity(postEntity);
        return commentEntity;
      }).collect(Collectors.toList());
    
    this.userRepository.saveAll(userEntities);
    this.postRepository.saveAll(postEntities);
    this.commentRepository.saveAll(commentEntities);
  }
  
}
