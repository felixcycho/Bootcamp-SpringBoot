package com.bootcamp.demo.demo_sb_bcforum_without_db.service;

import java.util.List;
import com.bootcamp.demo.demo_sb_bcforum_without_db.model.CommentDTO;
import com.bootcamp.demo.demo_sb_bcforum_without_db.model.UserDTO;
import com.bootcamp.demo.demo_sb_bcforum_without_db.model.PostDTO;

public interface JPHService {
  List<UserDTO> getAllUsers();
  List<PostDTO> getAllPosts();
  List<CommentDTO> getAllComments();
  
  
}
