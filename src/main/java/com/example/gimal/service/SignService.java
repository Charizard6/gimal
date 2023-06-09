package com.example.gimal.service;


import com.example.gimal.dto.SignInResultDTO;
import com.example.gimal.dto.SignUpResultDTO;

public interface SignService {
    SignUpResultDTO signUp(String id, String password, String name, String email, String role);
    SignInResultDTO signIn(String id, String password) throws RuntimeException;
}
