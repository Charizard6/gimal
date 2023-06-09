package com.example.gimal.dao.Impl;

import com.example.gimal.dao.UserDAO;
import com.example.gimal.entity.User;
import com.example.gimal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;
    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository =userRepository;
    }
    @Override
    public List<User> selectAllByOrderByPriceAsc() {
        return userRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<User> selectAllBy() {
        return userRepository.findAllBy();
    }

    @Override
    public User selectUserByUid(String uid) {
        return userRepository.getByUid(uid);
    }
}
