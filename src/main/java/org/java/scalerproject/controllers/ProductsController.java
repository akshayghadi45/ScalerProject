package org.java.scalerproject.controllers;

import org.java.scalerproject.DTOs.ProductResponseDTO;
import org.java.scalerproject.models.Product;
import org.java.scalerproject.service.FakeStoreProductService;
import org.java.scalerproject.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    ProductService productService;

        ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") long id) {

        return  new ProductResponseDTO(productService.getProductById(id));

    }
}
