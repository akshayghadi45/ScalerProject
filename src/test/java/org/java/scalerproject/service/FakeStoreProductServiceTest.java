package org.java.scalerproject.service;

import org.java.scalerproject.DTOs.FakeStoreResponseDTO;
import org.java.scalerproject.exceptions.ProductNotFoundException;
import org.java.scalerproject.models.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


class FakeStoreProductServiceTest {


    private RestTemplate restTemplate = Mockito.mock(RestTemplate.class);


    private FakeStoreProductService fakeStoreProductService = new FakeStoreProductService(restTemplate);

    @Test
    void testGetProductByIDReturnsProduct() throws ProductNotFoundException {
        FakeStoreResponseDTO dummyDTO = new FakeStoreResponseDTO();
        dummyDTO.setId(1L);
        dummyDTO.setDescription("description");
        dummyDTO.setTitle("dummy");
        dummyDTO.setPrice("12");

        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/1/",
                FakeStoreResponseDTO.class
        )).thenReturn(dummyDTO);

        Product product = fakeStoreProductService.getProductById(1L);
        assertEquals("description",product.getDescription() );
    }

    @Test
    public void testGetProductByIdThrowsExceptionOnNullProduct() throws ProductNotFoundException {
        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/1/",
                FakeStoreResponseDTO.class
        )).thenReturn(null);

        assertThrows(ProductNotFoundException.class, ()->fakeStoreProductService.getProductById(1L));
    }

    @Test
    public void testCreateProduct(){
        FakeStoreResponseDTO dummyDTO = new FakeStoreResponseDTO();
        dummyDTO.setTitle("title");
        dummyDTO.setId(1L);
        dummyDTO.setDescription("description");
        dummyDTO.setPrice("12");
        dummyDTO.setImage("abc");
        dummyDTO.setCategory("category");

        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreResponseDTO.class)
        )).thenReturn(dummyDTO);

        Product product = fakeStoreProductService.createProduct("title", "description", "abc", "category", 12.0);
        assertEquals("title", product.getName());
        assertEquals("description", product.getDescription());
        assertEquals("abc", product.getImageURl());
        assertEquals("category", product.getCategory().getName());
        assertEquals(12.0, product.getPrice().doubleValue(), 0);

        verify(restTemplate, times(1)).postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeStoreResponseDTO.class)
        );


    }

}