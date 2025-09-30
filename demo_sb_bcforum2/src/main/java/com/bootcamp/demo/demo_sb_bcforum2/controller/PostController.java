package com.bootcamp.demo.demo_sb_bcforum2.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.demo_sb_bcforum2.entity.PostEntity;
import com.bootcamp.demo.demo_sb_bcforum2.entity.UserEntity;
import com.bootcamp.demo.demo_sb_bcforum2.mapper.UserMapper;
import com.bootcamp.demo.demo_sb_bcforum2.model.Post;
import com.bootcamp.demo.demo_sb_bcforum2.model.User;
import com.bootcamp.demo.demo_sb_bcforum2.repository.PostRepository;
import com.bootcamp.demo.demo_sb_bcforum2.repository.UserRepository;


@RestController
public class PostController {
  private static final String postURL 
  = "https://jsonplaceholder.typicode.com/posts";
  private static final String userURL
  = "https://jsonplaceholder.typicode.com/users";

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private UserMapper userMapper;

  public static void main(String[] args) {
    String postURL 
    = UriComponentsBuilder.newInstance()
      .scheme("https")
      .host("jsonplaceholder.typicode.com")
      // .pathSegment("/v1")
      .path("/posts")
      .toUriString();
    System.out.println(postURL);
  }

  @PostMapping(value = "/posts")
  public List<PostEntity> createPosts() {
    // ! Clear all data in posts, users
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
    // ! insert into table
    this.userRepository.saveAll(userEntities);
    return this.postRepository.saveAll(postEntities);

    // List<PostEntity> postEntities 
    // = posts.stream() //
    //   .map(e -> {
    //    Optional<UserEntity> oUserEntity
    //    = this.userRepository.findById(e.getUserId());
    //      if (oUserEntity.isPresent()) {
    //        PostEntity postEntity 
    //        = PostEntity.builder() //
    //          .title(e.getTitle()) //
    //          .body(e.getBody()) //
    //          .userEntity(oUserEntity.get()).build();
    //      return postEntity;
    //      }
    //    return null;
    //    }).collect(Collectors.toList());
  }
  
}
