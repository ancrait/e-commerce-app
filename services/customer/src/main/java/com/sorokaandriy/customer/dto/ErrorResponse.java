package com.sorokaandriy.customer.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        LocalDateTime errorDate
){
}
