package com.sorokaandriy.customer.dto;

import java.util.Map;

public record ArgumentErrorResponse(
        Map<String,String> erorrs
) {
}
