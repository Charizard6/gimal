package com.example.gimal.dto.response;

import com.example.gimal.entity.Order;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private long id;
    private Long productId;
    private String productName;
    private String userId;
    private String userName;
    private int price;
    private Order order;

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.productId = order.getProductId();
        this.productName = order.getProductName();
        this.userId = order.getUserId();
        this.userName = order.getUserName();
        this.price = order.getPrice();
    }
    public OrderResponseDTO(long id, Long productId, String productName, String userId, String userName, int price) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.userId = userId;
        this.userName = userName;
        this.price = price;
    }
}
