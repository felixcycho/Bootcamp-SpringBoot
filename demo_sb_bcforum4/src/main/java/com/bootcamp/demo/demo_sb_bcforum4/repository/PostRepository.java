package com.bootcamp.demo.demo_sb_bcforum4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.demo_sb_bcforum4.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
  
}
