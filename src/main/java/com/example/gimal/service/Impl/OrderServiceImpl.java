package com.example.gimal.service.Impl;

import com.example.gimal.dao.OrderDAO;
import com.example.gimal.dto.OrderDTO;
import com.example.gimal.dto.response.OrderResponseDTO;
import com.example.gimal.dto.response.ProductResponseDTO;
import com.example.gimal.entity.Order;
import com.example.gimal.entity.Product;
import com.example.gimal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    @Autowired
    public OrderServiceImpl(OrderDAO orderedDAO) {
        this.orderDAO = orderedDAO;
    }

    @Override
    public OrderResponseDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order(orderDTO.getProductId(), orderDTO.getProductName(), orderDTO.getUserId()
        , orderDTO.getUserName(), orderDTO.getPrice(), LocalDateTime.now(), LocalDateTime.now());
        Order saveOrder = orderDAO.insertOrder(order);

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(saveOrder.getId());
        orderResponseDTO.setProductId(saveOrder.getProductId());
        orderResponseDTO.setProductName(saveOrder.getProductName());
        orderResponseDTO.setUserId(saveOrder.getUserId());
        orderResponseDTO.setUserName(saveOrder.getUserName());
        orderResponseDTO.setPrice(saveOrder.getPrice());
        return orderResponseDTO;
    }

    @Override
    public List<OrderResponseDTO> getAllOrder() {
        List<Order> orders = orderDAO.selectAllBy();
        List<OrderResponseDTO> orderResponseDTOS = orders.stream().map(item ->
                new OrderResponseDTO(item)).collect(Collectors.toList());
        return orderResponseDTOS;
    }

    @Override
    public List<OrderResponseDTO> getAllOrderListByUserId(String userId) {
        List<Order> orders = orderDAO.selectAllByUserId(userId);
        List<OrderResponseDTO> orderResponseDTOS = orders.stream().map(item ->
                new OrderResponseDTO(item)).collect(Collectors.toList());
        return orderResponseDTOS;
    }

    @Override
    public List<OrderResponseDTO> getAllOrderListByProductId(Long productId) {
        List<Order> orders = orderDAO.selectAllByProductId(productId);
        List<OrderResponseDTO> orderResponseDTOS = orders.stream().map(item ->
                new OrderResponseDTO(item)).collect(Collectors.toList());
        return orderResponseDTOS;
    }

    @Override
    public OrderResponseDTO getOrderById(long Id) {
        Order order = orderDAO.selectOrder(Id);
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(order.getId());
        orderResponseDTO.setProductId(order.getProductId());
        orderResponseDTO.setProductName(order.getProductName());
        orderResponseDTO.setUserId(order.getUserId());
        orderResponseDTO.setUserName(order.getUserName());
        orderResponseDTO.setPrice(order.getPrice());
        return orderResponseDTO;
    }
}
