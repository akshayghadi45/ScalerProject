package org.java.scalerproject.repositories;

import org.java.scalerproject.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long aLong);

    @Override
    public List<Product> findAll();

    public Page<Product> findByNameContaining(String query, Pageable  pageable);
}

