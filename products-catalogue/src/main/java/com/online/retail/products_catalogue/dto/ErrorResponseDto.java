package com.online.retail.products_catalogue.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponseDto {
    private final HttpStatus status;
    private final String message;
}