package com.bootcamp.demo.demo_sb_bcforum4.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_bcforum4.model.ResponseDTO;
import com.bootcamp.demo.demo_sb_bcforum4.model.UserDTO;
import com.bootcamp.demo.demo_sb_bcforum4.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseDTO<UserDTO> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseDTO<>(users, "Success");
    }
}
