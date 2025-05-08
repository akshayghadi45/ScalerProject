package org.java.scalerproject.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.java.scalerproject.models.Product;
import org.java.scalerproject.models.Category;

@Getter
@Setter
public class FakeStoreResponseDTO {
    private Long id;
    private String title;
    private String price;
    private String description;
    private String category;
    private String image;



    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setName(title);
        product.setImageURl(image);
        product.setPrice(Double.valueOf(price));
        Category category1 = new Category();
        category1.setName(category);
        category1.setId(id+id);
        product.setCategory(category1);
        return product;
    }
}
