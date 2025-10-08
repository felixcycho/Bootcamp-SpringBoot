package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
  // ! @Query -> nativeQuery = true, hardcoded SQL (MySQL)

  // ! @Query -> nativeQuery = false, Hiberate generates SQL compatible to target database product
  // JPQL: Java Persistence Query Language
  // @Query(value = "select p from PostEntity p where p.UserEntity = :userEntity")
  // List<PostEntity> findByUser(@Param(value = "userEntity") UserEntity userEntity);
}
