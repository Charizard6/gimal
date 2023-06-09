package com.example.gimal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProductDTO {
    private Long number;
    private String name;
    private int price;
    private int stock;

}
