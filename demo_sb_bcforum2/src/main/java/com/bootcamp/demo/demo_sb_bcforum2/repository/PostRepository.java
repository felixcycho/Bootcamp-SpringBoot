package com.bootcamp.demo.demo_sb_bcforum2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_sb_bcforum2.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
  
}