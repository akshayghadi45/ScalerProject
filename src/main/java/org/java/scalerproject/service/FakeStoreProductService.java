package org.java.scalerproject.service;

import org.java.scalerproject.DTOs.CreateFakeStoreRequestDTO;
import org.java.scalerproject.DTOs.FakeStoreResponseDTO;
import org.java.scalerproject.exceptions.ProductNotFoundException;
import org.java.scalerproject.models.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate  = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreResponseDTO response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id+"/",
                FakeStoreResponseDTO.class
        );
        if(response==null){
            throw new ProductNotFoundException("Product Not found for "+ id);
        }
        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreResponseDTO[] products = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreResponseDTO[].class
        );
        List<Product> productList = new ArrayList<Product>();
        for(FakeStoreResponseDTO fakeStoreResponseDTO : products){
            Product product = fakeStoreResponseDTO.toProduct();
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product createProduct(String name, String description, String imageURl, String category, Double price) {
        CreateFakeStoreRequestDTO requestDTO = createFakeStoreRequestDTO(name, description, imageURl, category, price);
        FakeStoreResponseDTO response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                requestDTO,
                FakeStoreResponseDTO.class
        );

        return response.toProduct();
    }

    @Override
    public Product replaceProduct(Long id, String name, String description, String imageURl, String category, Double price) {
        CreateFakeStoreRequestDTO requestDTO  = createFakeStoreRequestDTO(name, description, imageURl, category, price);

        //Below put operation doesnt return anything hence we could not be sure if the transaction has completed
//        FakeStoreResponseDTO response = restTemplate.put(
//                "https://fakestoreapi.com/products/"+id,
//                requestDTO,
//                FakeStoreResponseDTO.class
//        );

        // hence we will use exchange();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateFakeStoreRequestDTO> entity = new HttpEntity<>(requestDTO, headers);

         ResponseEntity<FakeStoreResponseDTO>  fakeStoreResponseDTOResponseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT,
                entity,
                FakeStoreResponseDTO.class
        );


        return fakeStoreResponseDTOResponseEntity.getBody().toProduct();
    }

    public CreateFakeStoreRequestDTO createFakeStoreRequestDTO(String name, String description, String imageURl, String category, Double price){
        CreateFakeStoreRequestDTO createFakeStoreRequestDTO = new CreateFakeStoreRequestDTO();
        createFakeStoreRequestDTO.setTitle(name);
        createFakeStoreRequestDTO.setDescription(description);
        createFakeStoreRequestDTO.setImage(imageURl);
        createFakeStoreRequestDTO.setCategory(category);
        createFakeStoreRequestDTO.setPrice(price);
        return createFakeStoreRequestDTO;
    }


}
