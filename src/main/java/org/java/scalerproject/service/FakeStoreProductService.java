package org.java.scalerproject.service;

import org.java.scalerproject.DTOs.FakeStoreResponseDTO;
import org.java.scalerproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.java.scalerproject.models.Category;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate  = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {


        System.out.println("dkjnkjsd");
        FakeStoreResponseDTO response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/1/",
                FakeStoreResponseDTO.class
        );

        System.out.println("dkjnkjsd");


//        String url = "http://fakestoreapi.com/products/" + id;
//        FakeStoreResponseDTO response = restTemplate.getForObject(url, FakeStoreResponseDTO.class);
//        System.out.println("khjbjbbhbh");
        return response.toProduct();
    }


}
