package com.bootcamp.demo.demo_sb_bcforum_with_db.Config;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_sb_bcforum_with_db.entity.CommentEntity;
import com.bootcamp.demo.demo_sb_bcforum_with_db.entity.PostEntity;
import com.bootcamp.demo.demo_sb_bcforum_with_db.entity.UserEntity;
import com.bootcamp.demo.demo_sb_bcforum_with_db.mapper.CommentEntityMapper;
import com.bootcamp.demo.demo_sb_bcforum_with_db.mapper.PostEntityMapper;
import com.bootcamp.demo.demo_sb_bcforum_with_db.mapper.UserEntityMapper;
import com.bootcamp.demo.demo_sb_bcforum_with_db.model.CommentDTO;
import com.bootcamp.demo.demo_sb_bcforum_with_db.model.PostDTO;
import com.bootcamp.demo.demo_sb_bcforum_with_db.model.UserDTO;
import com.bootcamp.demo.demo_sb_bcforum_with_db.repository.CommentRepository;
import com.bootcamp.demo.demo_sb_bcforum_with_db.repository.PostRepository;
import com.bootcamp.demo.demo_sb_bcforum_with_db.repository.UserRepository;
import com.bootcamp.demo.demo_sb_bcforum_with_db.service.JPHService;

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

    List<PostEntity> postEntities
    = this.jphService.getAllUsers().stream()
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
