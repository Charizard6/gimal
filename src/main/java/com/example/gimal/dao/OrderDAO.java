package com.example.gimal.dao;

import com.example.gimal.entity.Order;

import java.util.List;

public interface OrderDAO {
    Order insertOrder(Order order);
    Order selectOrder(long Id);
    List<Order> selectAllBy();
    List<Order> selectAllByUserId(String userId);
    List<Order> selectAllByProductId(Long productId);
}
