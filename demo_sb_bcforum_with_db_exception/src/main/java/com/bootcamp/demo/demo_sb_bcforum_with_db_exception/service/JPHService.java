package com.bootcamp.demo.demo_sb_bcforum_with_db_exception.service;

import java.util.List;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.model.CommentDTO;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.model.PostDTO;
import com.bootcamp.demo.demo_sb_bcforum_with_db_exception.model.UserDTO;

public interface JPHService {
  List<UserDTO> getAllUsers();
  List<PostDTO> getAllPosts();
  List<CommentDTO> getAllComments();
  
  
}
