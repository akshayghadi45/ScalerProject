package org.java.scalerproject.service;

import org.java.scalerproject.models.Product;
import org.springframework.data.domain.Page;

public interface SearchService {
    public Page<Product> searchProduct(String query, int pageNumber, int pageSize, String sortParam);
}
