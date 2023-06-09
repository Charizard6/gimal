package com.example.gimal.repository;

import com.example.gimal.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByPriceDesc();

    List<Product> findAllBy();

    List<Product> findByName(String name);
    List<Product> findByNumber(Long number);
}
