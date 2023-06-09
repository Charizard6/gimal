package com.example.gimal.dao.Impl;

import com.example.gimal.dao.ProductDAO;
import com.example.gimal.entity.Product;
import com.example.gimal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;
    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product selectProduct(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public Product insertProduct(Product product) {
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }

    @Override
    public Product updateUserNamePRiceStock(Long number, String name, int price, int stock) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);
        Product updateProduct;
        if(selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        } else throw new Exception();
        return updateProduct;
    }

    @Override
    public Product updateProductStock(Long number, int stock) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);
        Product updateProduct;
        if(selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            product.setStock(stock-1);
            product.setUpdatedAt(LocalDateTime.now());
            updateProduct = productRepository.save(product);
        } else throw new Exception();
        return updateProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);
        if(selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            productRepository.delete(product);
        } else throw new Exception();
    }

    @Override
    public List<Product> selectAllByOrderByPriceDESC() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public List<Product> selectAllBy() {
        return productRepository.findAllBy();
    }

    @Override
    public List<Product> selectProductByName(String name) {
        List<Product> selectProduct = productRepository.findByName(name);
        return selectProduct;
    }

    @Override
    public List<Product> selectProductByNumber(Long number) {
        List<Product> selectProduct = productRepository.findByNumber(number);
        return selectProduct;
    }
}
