package com.example.gimal.service;

import com.example.gimal.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> allUserOrderByName();
    List<UserResponseDTO> allUser();
    UserResponseDTO getUserById(String id);
}
