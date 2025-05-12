package org.java.scalerproject.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
    private String status;
    private String message;


    public ErrorDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
