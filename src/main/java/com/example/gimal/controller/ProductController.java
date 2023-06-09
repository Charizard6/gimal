package com.example.gimal.controller;

import com.example.gimal.dto.ChangeProductDTO;
import com.example.gimal.dto.ProductDTO;
import com.example.gimal.dto.response.BoardResponseDTO;
import com.example.gimal.dto.response.ProductResponseDTO;
import com.example.gimal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PutMapping()//상품 수정 어드민만
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDTO> changeProduct(@RequestBody ChangeProductDTO changeProductDTO) throws Exception {
        ProductResponseDTO productResponseDTO =
                productService.changeProductNamePriceStock(changeProductDTO.getNumber(), changeProductDTO.getName(), changeProductDTO.getPrice(), changeProductDTO.getStock());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }
    @PostMapping() //상품 등록 어드민만
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestParam String name, @RequestParam int price, @RequestParam int stock) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setPrice(price);
        productDTO.setStock(stock);
        ProductResponseDTO productResponseDTO = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }
    @DeleteMapping()//상품 삭제 어드민만
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("삭제됨");
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct() {
        List<ProductResponseDTO> productResponseDTOS = productService.allProduct();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOS);
    }

    @GetMapping("/listOrderByPrice")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductOrderByPrice() {
        List<ProductResponseDTO> productResponseDTOS = productService.allProductOrderByPriceDESC();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOS);
    }

    @GetMapping("/byName")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByName(String name) {
        List<ProductResponseDTO> productResponseDTO = productService.getProductByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    
    //하나 가져오는거임
    @GetMapping()
    public ResponseEntity<ProductResponseDTO> getProductById(Long Id) {
        ProductResponseDTO productResponseDTO = productService.getOneProduct(Id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }
}
