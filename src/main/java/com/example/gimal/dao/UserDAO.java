package com.example.gimal.dao;

import com.example.gimal.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> selectAllByOrderByPriceAsc();
    List<User> selectAllBy();

    User selectUserByUid(String uid);
}
