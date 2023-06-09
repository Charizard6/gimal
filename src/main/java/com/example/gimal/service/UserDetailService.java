package com.example.gimal.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface UserDetailService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
