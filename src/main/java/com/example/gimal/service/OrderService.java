package com.example.gimal.service;

import com.example.gimal.dto.OrderDTO;
import com.example.gimal.dto.response.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO createOrder(OrderDTO orderDTO);
    List<OrderResponseDTO> getAllOrder();
    List<OrderResponseDTO> getAllOrderListByUserId(String userId);
    List<OrderResponseDTO> getAllOrderListByProductId(Long productId);
    OrderResponseDTO getOrderById(long Id);
}
