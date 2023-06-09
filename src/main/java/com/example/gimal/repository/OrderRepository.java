package com.example.gimal.repository;

import com.example.gimal.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(String userId);
    List<Order> findByProductId(Long productId);
}
