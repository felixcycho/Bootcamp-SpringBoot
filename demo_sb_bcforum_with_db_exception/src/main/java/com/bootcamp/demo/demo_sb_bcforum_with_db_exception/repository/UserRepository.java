package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  
  // Native Query (Demo Wrong Column name (i.e. names) -> SQL EXception)
  // @Query(
  //   value = "select u.* from users u where names = ?", nativeQuery = true)
  @Query(value = "select u.* from users u where u.names = :name", nativeQuery = true)
  // UserEntity findUsersByName(@Param(value = "name") String name); 
  List<UserEntity> findUsersByName(@Param(value = "name") String name); 
}
