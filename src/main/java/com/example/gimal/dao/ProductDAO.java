package com.example.gimal.dao;

import com.example.gimal.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product selectProduct(Long id);
    Product insertProduct(Product product);
    Product updateUserNamePRiceStock(Long number, String name, int price, int stock) throws Exception;

    void deleteProduct(Long number) throws Exception;

    List<Product> selectAllByOrderByPriceDESC();
    List<Product> selectAllBy();

    List<Product> selectProductByName(String name);
    List<Product> selectProductByNumber(Long number);
}

