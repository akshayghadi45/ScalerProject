package org.java.scalerproject.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.java.scalerproject.models.Product;

import java.util.ArrayList;
@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String title;
    private String price;
    private String description;
    private String category;
    private String image;
    private String indicater;
    public ProductResponseDTO() {}

    public  ProductResponseDTO(Product product){
        this.setId(product.getId());
        this.setTitle(product.getName());
        this.setDescription(product.getDescription());
        this.setCategory(product.getCategory().getName());
        this.setImage(product.getImageURl());
        this.setPrice(product.getPrice().toString());
        this.setIndicater("productResponseDTO");

    }
}
