package org.java.scalerproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java.scalerproject.DTOs.CreateProductRequestDTO;
import org.java.scalerproject.DTOs.ProductResponseDTO;
import org.java.scalerproject.controllers.ProductsController;
import org.java.scalerproject.models.Category;
import org.java.scalerproject.models.Product;
import org.java.scalerproject.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductsController.class)
public class ProductControllerMvcTest {

    @MockitoBean
    @Qualifier("productSQLDbService")
    ProductService productService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    private Product getProductForTest(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(10.0);
        product.setImageURl("Image");
        Category category = new Category();
        category.setId(1L);
        category.setName("Category Name");
        product.setCategory(category);
        return product;
    }

    private CreateProductRequestDTO getProductRequestDTOForTest(Long id) {
        CreateProductRequestDTO productRequestDTO = new CreateProductRequestDTO();
        productRequestDTO.setTitle("Product Name");
        productRequestDTO.setDescription("Product Description");
        productRequestDTO.setPrice(10.0);
        productRequestDTO.setImage("Image");
        productRequestDTO.setCategory("Category Name");
        return productRequestDTO;
    }

    @Test
    public void getAllProductsRunsSuccessfully() throws Exception {

        //Arrange
        Product product1 = getProductForTest(1L);
        Product product2 = getProductForTest(2L);
        List<Product> dummyProducts = List.of(product1, product2);

        when(productService.getAllProducts()).thenReturn(dummyProducts);

        List<ProductResponseDTO> dummyProductResponseDTOList = List.of(ProductResponseDTO.from(product1), ProductResponseDTO.from(product2));

        //Act & Assert
        mockMvc.perform(get("/products")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(content().string(jacksonObjectMapper.writeValueAsString(dummyProductResponseDTOList)));
    }

    @Test
    public void testCreateProductRunsSuccessfully() throws Exception {

        CreateProductRequestDTO productRequestDTO = getProductRequestDTOForTest(1L);
        Product product = getProductForTest(1L);
        ProductResponseDTO productResponseDTO = ProductResponseDTO.from(product);

        when(productService.createProduct(productRequestDTO.getTitle(),
                productRequestDTO.getDescription(),
                productRequestDTO.getImage(),
                productRequestDTO.getCategory(),
                productRequestDTO.getPrice())).thenReturn(product);


        //Act and Assert
        mockMvc.perform(post("/products").
                        contentType(MediaType.APPLICATION_JSON).
                        content(jacksonObjectMapper.writeValueAsString(productRequestDTO))
                ).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(content().string(jacksonObjectMapper.writeValueAsString(productResponseDTO)));
    }
}
