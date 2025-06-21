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

    public static ProductResponseDTO from(Product product){

        if(product==null){
         return null;
        }
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setTitle(product.getName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setCategory(product.getCategory().getName());
        productResponseDTO.setImage(product.getImageURl());
        productResponseDTO.setPrice(product.getPrice().toString());
        productResponseDTO.setIndicater("productResponseDTO");
        return productResponseDTO;
    }
}
