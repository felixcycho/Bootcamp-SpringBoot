package com.bootcamp.demo.demo_sb_bcforum4.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.demo_sb_bcforum4.mapper.PostMapper;
import com.bootcamp.demo.demo_sb_bcforum4.model.PostDTO;
import com.bootcamp.demo.demo_sb_bcforum4.repository.PostRepository;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }
}
