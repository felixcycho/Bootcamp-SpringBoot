package com.bootcamp.demo.demo_sb_bcforum_with_db.service;

import java.util.List;
import com.bootcamp.demo.demo_sb_bcforum_with_db.model.CommentDTO;
import com.bootcamp.demo.demo_sb_bcforum_with_db.model.PostDTO;
import com.bootcamp.demo.demo_sb_bcforum_with_db.model.UserDTO;

public interface JPHService {
  List<UserDTO> getAllUsers();
  List<PostDTO> getAllPosts();
  List<CommentDTO> getAllComments();
  
  
}
