package org.java.scalerproject.service;

import org.java.scalerproject.exceptions.ProductNotFoundException;
import org.java.scalerproject.models.Category;
import org.java.scalerproject.models.Product;
import org.java.scalerproject.repositories.CategoryRepository;
import org.java.scalerproject.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSQLDbService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public ProductSQLDbService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository =categoryRepository;
    }


    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        throw new  ProductNotFoundException("Product with product ID "+id+"not found");
    }

    @Override
    public List<Product> getAllProducts() {
       List<Product> products= productRepository.findAll();
        return products;
    }

    @Override
    public Product createProduct(String name, String description, String imageURl, String category, Double price) {
        Product product = new Product();
        createProduct(product, name, description, imageURl, category, price);
        return productRepository.save(product);

    }

    @Override
    public Product replaceProduct(Long id, String name, String description, String imageURl, String category, Double price) {
        Product product = new Product();
        product.setId(id);
        createProduct(product, name, description, imageURl, category, price);
        return productRepository.save(product);

    }


    public void createProduct(Product product, String name, String description, String imageURl, String category, Double price) {
        product.setName(name);
        product.setDescription(description);
        product.setImageURl(imageURl);

        Optional<Category> categoryFromDb = categoryRepository.findByName(category);
        if(categoryFromDb.isPresent()){
            product.setCategory(categoryFromDb.get());
        }
        else {

            Category categoryObj = new Category();
            categoryObj.setName(category);
            product.setCategory(categoryRepository.save(categoryObj));
        }
        product.setPrice(price);

    }
}
