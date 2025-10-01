package com.bootcamp.demo.demo_sb_bcforum4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.demo_sb_bcforum4.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
  
}
