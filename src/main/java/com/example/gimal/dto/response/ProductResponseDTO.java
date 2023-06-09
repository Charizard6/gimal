package com.example.gimal.dto.response;

import com.example.gimal.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductResponseDTO {
    private Long number;
    private String name;
    private int price;
    private int stock;
    private Product product;

    public ProductResponseDTO(Product product) {
        this.number = product.getNumber();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }
}
