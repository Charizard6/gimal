package com.example.gimal.service.Impl;

import com.example.gimal.dao.ProductDAO;
import com.example.gimal.dto.ProductDTO;
import com.example.gimal.dto.response.ProductResponseDTO;
import com.example.gimal.entity.Product;
import com.example.gimal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDTO getOneProduct(Long id) {
        Product product = productDAO.selectProduct(id);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(product.getNumber());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saveProduct = productDAO.insertProduct(product);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setNumber(saveProduct.getNumber());
        productResponseDTO.setName(saveProduct.getName());
        productResponseDTO.setPrice(saveProduct.getPrice());
        productResponseDTO.setStock(saveProduct.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO changeProductNamePriceStock(Long number, String name, int price, int stock) throws Exception {
        Product changeproduct = productDAO.updateUserNamePRiceStock(number, name, price, stock);
        return new ProductResponseDTO(changeproduct);
    }

    @Override
    public ProductResponseDTO changeProductStock(Long number, int stock) throws Exception {
        Product changeproduct = productDAO.updateProductStock(number, stock);
        return new ProductResponseDTO(changeproduct);
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }

    @Override
    public List<ProductResponseDTO> allProductOrderByPriceDESC() {
        List<Product> products = productDAO.selectAllByOrderByPriceDESC();
        List<ProductResponseDTO> productResponseDTOS = products.stream().map(item ->
                new ProductResponseDTO(item)).collect(Collectors.toList());
        return productResponseDTOS;
    }

    @Override
    public List<ProductResponseDTO> allProduct() {
        List<Product> products = productDAO.selectAllBy();
        List<ProductResponseDTO> productResponseDTOS = products.stream().map(item ->
                new ProductResponseDTO(item)).collect(Collectors.toList());
        return productResponseDTOS;
    }

    @Override
    public List<ProductResponseDTO> getProductByName(String name) {
        List<Product> product = productDAO.selectProductByName(name);
        List<ProductResponseDTO> productResponseDTO = product.stream().map(item ->
                new ProductResponseDTO(item)).collect(Collectors.toList());
        return productResponseDTO;
    }

    @Override
    public List<ProductResponseDTO> getProductByNumber(Long number) {
        List<Product> product = productDAO.selectProductByNumber(number);
        List<ProductResponseDTO> productResponseDTO = product.stream().map(item ->
                new ProductResponseDTO(item)).collect(Collectors.toList());
        return productResponseDTO;
    }
}
