package org.java.scalerproject.service;

import org.java.scalerproject.models.Product;
import org.java.scalerproject.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    private final ProductRepository productRepository;

    public SearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> searchProduct(String query, int pageNumber, int pageSize, String sortParam) {
        Sort sort = Sort.by(Sort.Direction.DESC, sortParam);
        sort.and(Sort.by(Sort.Direction.ASC, "price"));
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        return productRepository.findByNameContaining(query, pageable);
    }
}
