package org.java.scalerproject.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.java.scalerproject.DTOs.ProductResponseDTO;

import java.io.Serializable;

@Getter
@Setter
@Entity

public class Product extends Base implements Serializable {

    private String description;
    private String imageURl;
    @ManyToOne
    private Category category;
//    @ManyToOne
//    private Category featuredCategory;
    private Double price;
    private Double weight;

    public ProductResponseDTO toProductResponseDTO() {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(this.getId());
        productResponseDTO.setTitle(this.getName());
        productResponseDTO.setDescription(this.description);
        productResponseDTO.setImage(this.imageURl);
        productResponseDTO.setCategory(this.category.getName());
        productResponseDTO.setPrice(this.price.toString());
        return productResponseDTO;
    }
}
