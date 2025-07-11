package org.java.scalerproject.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFakeStoreRequestDTO {
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
