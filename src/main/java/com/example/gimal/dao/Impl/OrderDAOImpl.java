package com.example.gimal.dao.Impl;

import com.example.gimal.dao.OrderDAO;
import com.example.gimal.entity.Order;
import com.example.gimal.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class OrderDAOImpl implements OrderDAO {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderDAOImpl(OrderRepository orderedRepository) {
        this.orderRepository = orderedRepository;
    }

    @Override
    public Order insertOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order selectOrder(long Id) {
        Order selectOrder = orderRepository.getReferenceById(Id);
        return selectOrder;
    }

    @Override
    public List<Order> selectAllBy() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> selectAllByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> selectAllByProductId(Long productId) {
        return orderRepository.findByProductId(productId);
    }

}
