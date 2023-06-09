package com.example.gimal.service.Impl;

import com.example.gimal.dao.UserDAO;
import com.example.gimal.dto.response.ProductResponseDTO;
import com.example.gimal.dto.response.UserResponseDTO;
import com.example.gimal.entity.Product;
import com.example.gimal.entity.User;
import com.example.gimal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }
    @Override
    public List<UserResponseDTO> allUserOrderByName() {
        List<User> users = userDAO.selectAllByOrderByPriceAsc();
        List<UserResponseDTO> productResponseDtoList =
                users.stream().map(UserResponseDTO::new).collect(Collectors.toList());;
        return productResponseDtoList;
    }
    @Override
    public List<UserResponseDTO> allUser() {
        List<User> users = userDAO.selectAllBy();
        List<UserResponseDTO> productResponseDtoList =
                users.stream().map(UserResponseDTO::new).collect(Collectors.toList());;
        return productResponseDtoList;
    }

    @Override
    public UserResponseDTO getUserById(String id) {
        User user = userDAO.selectUserByUid(id);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUid(user.getUid());
        userResponseDTO.setName(user.getName());

        return userResponseDTO;
    }
}
