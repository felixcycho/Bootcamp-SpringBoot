package com.bootcamp.demo.demo_sb_bcforum4.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.demo_sb_bcforum4.mapper.CommentMapper;
import com.bootcamp.demo.demo_sb_bcforum4.model.CommentDTO;
import com.bootcamp.demo.demo_sb_bcforum4.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
