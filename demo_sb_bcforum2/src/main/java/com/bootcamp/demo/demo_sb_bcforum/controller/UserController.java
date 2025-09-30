package com.bootcamp.demo.demo_sb_bcforum.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.demo_sb_bcforum.entity.UserEntity;
import com.bootcamp.demo.demo_sb_bcforum.mapper.UserMapper;
import com.bootcamp.demo.demo_sb_bcforum.model.User;
import com.bootcamp.demo.demo_sb_bcforum.repository.UserRepository;

@RestController
public class UserController {
  private static final String userURL =
    "https://jsonplaceholder.typicode.com/users";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping(value = "/users")
  public List<User> getUsers() {
    try {
      RestTemplate restTemplate = new RestTemplate();
      // ! getForObject() -> auto-deserialization
      // User[] users = new restTemplate.getForObject(userURL, User[].class);
      User[] users = restTemplate.getForObject(userURL, User[].class);
      // Log fetched users
      System.out.println("Fetched Users: " + Arrays.toString(users));
      // Array -> ArrayList
      return Arrays.asList(users);
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        return Collections.emptyList();
    }
    // Array -> ArrayList
    // return Arrays.asList(users);
    
  }

  @PostMapping(value = "/users")
  public List<UserEntity> createUsers() {
    this.userRepository.deleteAll();
    try {
      // List<User> users = getUsers();
      List<User> users 
      // = Arrays.asList(new RestTemplate().getForObject(userURL, User[].class));
      = Arrays.asList(this.restTemplate.getForObject(userURL, User[].class));
      // Convert List<User> to List<UserEntity>
      List<UserEntity> userEntities = users.stream() //
        .map(u -> {
          // Log each user before mapping
          System.out.println("Mapping User: " + u);
          return new UserMapper().map(u);
        }).collect(Collectors.toList());
        // .map(u -> new UserMapper().map(u)) //
        // .collect(Collectors.toList());
    // saveAll
    return this.userRepository.saveAll(userEntities);
    } catch (Exception e) {
      throw new RuntimeException
      ("Failed to create users in DB: " + e.getMessage(), e);
    }
  }

}