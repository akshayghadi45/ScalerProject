package org.java.scalerproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends Base implements Serializable {
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    List<Product> products;

//    @OneToMany(mappedBy = "featuredCategory")
//    List<Product> featuredProducts;

}
