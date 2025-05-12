package org.java.scalerproject.exceptions;

import org.java.scalerproject.DTOs.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ErrorDTO handleNullPointerException(){
        return new ErrorDTO("Failure occured", "Null pointer exception");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(Exception e){
       ErrorDTO errorDTO= new  ErrorDTO("Failure occured", e.getMessage());
       return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
