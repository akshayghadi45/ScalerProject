package org.java.scalerproject.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String name;
    private String description;
    private String imageURl;
    private Category category;
    private Double price;
}
