package com.girish.billconverter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CoinConverterExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handle(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body("invalid Argument. "+ ex.getMessage());
    }

    @ExceptionHandler(CoinConverterException.class)
    public ResponseEntity<Object> handle(CoinConverterException ex){
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

}
