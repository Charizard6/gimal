package com.example.gimal.service;

import com.example.gimal.dto.ProductDTO;
import com.example.gimal.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO getOneProduct(Long id);
    ProductResponseDTO createProduct(ProductDTO productDTO);
    ProductResponseDTO changeProductNamePriceStock(Long number, String name, int price, int stock) throws Exception;

    ProductResponseDTO changeProductStock(Long number, int stock) throws Exception;
    void deleteProduct(Long number) throws Exception;

    List<ProductResponseDTO> allProductOrderByPriceDESC();

    List<ProductResponseDTO> allProduct();

    List<ProductResponseDTO> getProductByName(String name);

    List<ProductResponseDTO> getProductByNumber(Long number);

}
