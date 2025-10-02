package com.bootcamp.demo.demo_sb_bcforum_with_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.demo_sb_bcforum_with_db.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  
}
