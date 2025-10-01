package com.bootcamp.demo.demo_sb_bcforum4.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_bcforum4.model.PostDTO;
import com.bootcamp.demo.demo_sb_bcforum4.model.ResponseDTO;
import com.bootcamp.demo.demo_sb_bcforum4.service.PostService;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseDTO<PostDTO> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return new ResponseDTO<>(posts, "Success");
    }
}
