package com.bootcamp.demo.demo_sb_bcforum4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String name;
    private String email;
    private String body;
    private Long postId; // Reference to post
}
