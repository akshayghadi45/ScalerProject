package org.java.scalerproject.controllers;

import org.java.scalerproject.DTOs.ProductResponseDTO;
import org.java.scalerproject.DTOs.SearchRequestDto;
import org.java.scalerproject.models.Product;
import org.java.scalerproject.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    private SearchService searchService;
    SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public List<ProductResponseDTO> search(@RequestBody SearchRequestDto searchRequestDto) {
        Page<Product> pagedProducts = searchService.searchProduct(searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(),
                searchRequestDto.getPageSize(),
                searchRequestDto.getSortParam()
        );
        return productResponseDTOList(pagedProducts);
    }

    @GetMapping("/search")
    public List<ProductResponseDTO> search(@RequestParam String query, @RequestParam Integer pageNumber, @RequestParam Integer pageSize, @RequestParam String sortParam) {
        Page<Product> pagedProducts = searchService.searchProduct(query, pageNumber, pageSize, sortParam);
        return productResponseDTOList(pagedProducts);

    }

    public List<ProductResponseDTO> productResponseDTOList(Page<Product> pagedProducts) {
        List<Product> products = pagedProducts.getContent();
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        for (Product product : products) {
            ProductResponseDTO productResponseDTO = ProductResponseDTO.from(product);
            productResponseDTOList.add(productResponseDTO);
        }
        return productResponseDTOList;
    }



}
