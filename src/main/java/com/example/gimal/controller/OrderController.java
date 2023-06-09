package com.example.gimal.controller;

import com.example.gimal.config.security.JwtTokenProvider;
import com.example.gimal.dto.OrderDTO;
import com.example.gimal.dto.ProductDTO;
import com.example.gimal.dto.UserDTO;
import com.example.gimal.dto.response.BoardResponseDTO;
import com.example.gimal.dto.response.OrderResponseDTO;
import com.example.gimal.dto.response.ProductResponseDTO;
import com.example.gimal.dto.response.UserResponseDTO;
import com.example.gimal.service.BoardService;
import com.example.gimal.service.OrderService;
import com.example.gimal.service.ProductService;
import com.example.gimal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    public OrderController(OrderService orderService, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.orderService = orderService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<OrderResponseDTO> createOrder(HttpServletRequest request, @RequestParam Long productId,
        @RequestParam String productName, @RequestParam int price) {
        String id = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        OrderDTO newOrder = new OrderDTO();
        newOrder.setProductId(productId);
        newOrder.setProductName(productName);
        newOrder.setUserId(userResponseDTO.getUid());
        newOrder.setUserName(userResponseDTO.getName());
        newOrder.setPrice(price);
        OrderResponseDTO orderResponseDTO = orderService.createOrder(newOrder);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrder() {
        List<OrderResponseDTO> orderResponseDTO = orderService.getAllOrder();
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }
    @GetMapping("/listByUserId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrderListByUserId(String userId) {
        List<OrderResponseDTO> orderResponseDTO = orderService.getAllOrderListByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }
    @GetMapping("/listByProductId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrderListByProductId(Long productId) {
        List<OrderResponseDTO> orderResponseDTO = orderService.getAllOrderListByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }
    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderResponseDTO> getOrderById(long Id) {
        OrderResponseDTO orderResponseDTO = orderService.getOrderById(Id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDTO);
    }
}
