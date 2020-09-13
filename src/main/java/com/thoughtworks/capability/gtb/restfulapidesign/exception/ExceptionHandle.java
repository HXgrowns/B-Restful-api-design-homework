package com.thoughtworks.capability.gtb.restfulapidesign.exception;

import com.thoughtworks.capability.gtb.restfulapidesign.utils.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<?> handleBussinessException(BussinessException e) {
        return ResponseEntity.badRequest().body(Result.error(e.getCode(),e.getMessage()));
    }
}
