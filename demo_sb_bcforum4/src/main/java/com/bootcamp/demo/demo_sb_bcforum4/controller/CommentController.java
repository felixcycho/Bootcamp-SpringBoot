package com.bootcamp.demo.demo_sb_bcforum4.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_bcforum4.model.CommentDTO;
import com.bootcamp.demo.demo_sb_bcforum4.model.ResponseDTO;
import com.bootcamp.demo.demo_sb_bcforum4.service.CommentService;

@RestController
public class CommentController {
  private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public ResponseDTO<CommentDTO> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments();
        return new ResponseDTO<>(comments, "Success");
    }
}
