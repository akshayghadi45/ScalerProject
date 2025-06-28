package org.java.scalerproject.controllers;

import org.java.scalerproject.DTOs.CreateProductRequestDTO;
import org.java.scalerproject.DTOs.ProductResponseDTO;

import org.java.scalerproject.exceptions.ProductNotFoundException;
import org.java.scalerproject.models.Product;
import org.java.scalerproject.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {
    ProductService productService;


    //instead of adding @Qualifier we can also add @primary in the service 
        ProductsController(@Qualifier("productSQLDbService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        return   ProductResponseDTO.from(productService.getProductById(id));
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ErrorDTO handleNullPointerException(){
//        ErrorDTO errorDTO = new ErrorDTO();
//        errorDTO.setMessage("Product cannot be null");
//        errorDTO.setStatus("Failure");
//        return errorDTO;
//    }

    @GetMapping("/products")
    public List<ProductResponseDTO> getAllProducts() {
      List<Product> products =  productService.getAllProducts();
      List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
      for (Product product : products) {
          productResponseDTOs.add(ProductResponseDTO.from(product));
      }
      return productResponseDTOs;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO createFakeStoreProductRequestDTO){
            Product product = productService.createProduct(createFakeStoreProductRequestDTO.getTitle(),
                    createFakeStoreProductRequestDTO.getDescription(),
                    createFakeStoreProductRequestDTO.getImage(),
                    createFakeStoreProductRequestDTO.getCategory(),
                    createFakeStoreProductRequestDTO.getPrice()
                    );

            return new ResponseEntity<>(ProductResponseDTO.from(product), HttpStatus.CREATED);

    }

    @PutMapping("/products/{id}")
    public ProductResponseDTO replaceProduct(@PathVariable("id") Long id, @RequestBody CreateProductRequestDTO createFakeStoreProductRequestDTO){
        Product product = productService.replaceProduct(
                id,
                createFakeStoreProductRequestDTO.getTitle(),
                createFakeStoreProductRequestDTO.getDescription(),
                createFakeStoreProductRequestDTO.getImage(),
                createFakeStoreProductRequestDTO.getCategory(),
                createFakeStoreProductRequestDTO.getPrice()
        );
        return product.toProductResponseDTO();

    }
}
