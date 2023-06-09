package com.example.gimal.controller;


import com.example.gimal.dto.response.UserResponseDTO;
import com.example.gimal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping("/listOrderByName")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllOrderByName() {
        List<UserResponseDTO> userResponseDTOS = userService.allUserOrderByName();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTOS);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> userResponseDTOS = userService.allUser();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTOS);
    }
}
