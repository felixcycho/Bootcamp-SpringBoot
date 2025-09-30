package com.bootcamp.demo.demo_sb_bcforum2.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.demo_sb_bcforum2.entity.CommentEntity;
import com.bootcamp.demo.demo_sb_bcforum2.entity.PostEntity;
import com.bootcamp.demo.demo_sb_bcforum2.entity.UserEntity;
import com.bootcamp.demo.demo_sb_bcforum2.mapper.PostMapper;
import com.bootcamp.demo.demo_sb_bcforum2.mapper.UserMapper;
import com.bootcamp.demo.demo_sb_bcforum2.model.Comment;
import com.bootcamp.demo.demo_sb_bcforum2.model.Post;
import com.bootcamp.demo.demo_sb_bcforum2.model.User;
import com.bootcamp.demo.demo_sb_bcforum2.repository.CommentRepository;
import com.bootcamp.demo.demo_sb_bcforum2.repository.PostRepository;
import com.bootcamp.demo.demo_sb_bcforum2.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CommentController {
  private static final String commentURL
  = "https://jsonplaceholder.typicode.com/comments";
  private static final String postURL 
  = "https://jsonplaceholder.typicode.com/posts";
  private static final String userURL
  = "https://jsonplaceholder.typicode.com/users";

  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private PostMapper postMapper;
  @Autowired
  private UserMapper userMapper;

  public static void main(String[] args) {
    String commentURL 
    = UriComponentsBuilder.newInstance()
      .scheme("https")
      .host("jsonplaceholder.typicode.com")
      // .pathSegment("/v1")
      .path("/comments")
      .toUriString();
    System.out.println(commentURL);
  }

  @PostMapping("/comments")
  public List<CommentEntity> createComments() {
    // ! Clear all data in posts, users
    this.commentRepository.deleteAll();
    this.postRepository.deleteAll();
    this.userRepository.deleteAll();

    // ! Call API for the data
    List<User> users
    // = Arrays.asList(new RestTemplate().getForObject(userURL, User[].class));
    = Arrays.asList(this.restTemplate.getForObject(userURL, User[].class));
    // System.out.println(users);
    List<Post> posts 
    // = Arrays.asList(new RestTemplate().getForObject(postURL, Post[].class));
    = Arrays.asList(this.restTemplate.getForObject(postURL, Post[].class));
    // System.out.println(posts);
    // stream() -> map -> List<Post> -> List<PostEntity> repository.saveAll
    List<Comment> comments
    // = Arrays.asList(new RestTemplate().getForObject(postURL, Post[].class));
    = Arrays.asList(this.restTemplate.getForObject(commentURL, Comment[].class));
    // System.out.println(comments);
    // stream() -> map -> List<Comment> -> List<CommentEntity> repository.saveAll

    // ! Convert to List<UserEntity>
    List<UserEntity> userEntities 
    = users.stream()
      // .map(u -> new UserMapper().map(u))
      .map(u -> this.userMapper.map(u))
      .collect(Collectors.toList());

    // ! Convert to List<PostEntity>
    List<PostEntity> postEntities = new ArrayList<>();
    for (Post post : posts) {
      Optional<UserEntity> oUserEntity 
       = userEntities.stream()
         .filter(e -> e.getOrigUserId().equals(post.getUserId()))
         .findFirst();
      if (oUserEntity.isPresent()) {
        PostEntity postEntity 
        = PostEntity.builder()
          .title(post.getTitle())
          .body(post.getBody())
          .userEntity(oUserEntity.get())     // ! important (foreign key)
          .build();
        postEntities.add(postEntity);
      }
    }
    // ! Convert to List<CommentEntity>
    List<CommentEntity> commentEntities = new ArrayList<>();
    for (Comment comment : comments) {
      Optional<PostEntity> oPostEntity 
       = postEntities.stream()
         .filter(e -> e.getOrigPostId().equals(comment.getPostId()))
         .findFirst();
      if (oPostEntity.isPresent()) {
        CommentEntity commentEntity 
        = CommentEntity.builder()
          .name(comment.getName())
          .email(comment.getEmail())
          .body(comment.getBody())
          .postEntity(oPostEntity.get())     // ! important (foreign key)
          .build();
        commentEntities.add(commentEntity);
      }
    }
    // ! insert into table
    this.userRepository.saveAll(userEntities);
    this.postRepository.saveAll(postEntities);
    return this.commentRepository.saveAll(commentEntities);

  }

}
