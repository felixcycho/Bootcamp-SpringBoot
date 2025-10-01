package com.bootcamp.demo.demo_sb_bcforum4.service;

import java.util.List;
import java.util.stream.Collectors;
import com.bootcamp.demo.demo_sb_bcforum4.mapper.UserMapper;
import com.bootcamp.demo.demo_sb_bcforum4.model.UserDTO;
import com.bootcamp.demo.demo_sb_bcforum4.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
