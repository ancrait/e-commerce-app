package com.sorokaandriy.order_service.exception;

import com.sorokaandriy.order_service.dto.ErrorResponseDto;
import com.sorokaandriy.order_service.dto.ErrorValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFoundException(Exception e){

        var errorDto = new ErrorResponseDto(
                e.getMessage(),
                "Order exception due to another ms",
                LocalDateTime.now());

        return ResponseEntity
                .status(404)
                .body(errorDto);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductPurchaseException(Exception e){

        var errorDto = new ErrorResponseDto(
                e.getMessage(),
                "Order not found exception",
                LocalDateTime.now());

        return ResponseEntity
                .status(404)
                .body(errorDto);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorValidationResponse> handleValidationException(MethodArgumentNotValidException e){

        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError)error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName,errorMessage);
                });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorValidationResponse(errors));
    }

}
