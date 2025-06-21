package org.java.scalerproject.controller;

import org.java.scalerproject.DTOs.ProductResponseDTO;
import org.java.scalerproject.controllers.ProductsController;
import org.java.scalerproject.exceptions.ProductNotFoundException;
import org.java.scalerproject.models.Category;
import org.java.scalerproject.models.Product;
import org.java.scalerproject.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductsControllerTest {

    @Autowired
    private ProductsController productsController;

    @MockitoBean
    @Qualifier("productSQLDbService")
    private ProductService productService;


    @Test
    public void testGetProductByIdReturnsDto() throws ProductNotFoundException {
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setName("product 1");
        dummyProduct.setDescription("product 1");
        dummyProduct.setPrice(12.5);
        dummyProduct.setImageURl("imageURL");

        Category category = new Category();
        category.setName("category 1");
        category.setId(1L);

        dummyProduct.setCategory(category);


        when(productService.getProductById(anyLong())).thenReturn(dummyProduct);

        //AAA
        //Arrange

        //Act
        ProductResponseDTO productResponseDTO = productsController.getProductById(1L);
        //Assert
        assertEquals(1L, productResponseDTO.getId());
        assertEquals("product 1", productResponseDTO.getTitle());
        assertEquals("imageURL", productResponseDTO.getImage());
        assertEquals("product 1", productResponseDTO.getDescription());
        assertEquals("12.5", productResponseDTO.getPrice());
        assertEquals("category 1", productResponseDTO.getCategory());

    }
    @Test
    void getProductFromIdreturnsNull() throws ProductNotFoundException {
        when(productService.getProductById(1L)).thenReturn(null);

        ProductResponseDTO productResponseDTO= productsController.getProductById(1L);
        assertEquals(null,productResponseDTO);

    }
}