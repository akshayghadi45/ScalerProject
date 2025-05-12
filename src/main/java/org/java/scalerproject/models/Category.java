package org.java.scalerproject.models;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends Base {
    @OneToMany(mappedBy = "category")
    List<Product> products;

//    @OneToMany(mappedBy = "featuredCategory")
//    List<Product> featuredProducts;

}
