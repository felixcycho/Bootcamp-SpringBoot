package com.bootcamp.demo.demo_sb_bcforum_with_db.model;

import lombok.Getter;

@Getter
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private Long userId;
}
