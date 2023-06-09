package com.example.gimal.repository;

import com.example.gimal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);
    List<User> findAllByOrderByNameAsc();
    List<User> findAllBy();
}
