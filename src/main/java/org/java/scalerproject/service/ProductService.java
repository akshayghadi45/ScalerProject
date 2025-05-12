package org.java.scalerproject.service;

import org.java.scalerproject.exceptions.ProductNotFoundException;
import org.java.scalerproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(String name, String description, String imageURl, String category, Double price);
    Product replaceProduct(Long id, String name, String description, String imageURl, String category, Double price);
}
