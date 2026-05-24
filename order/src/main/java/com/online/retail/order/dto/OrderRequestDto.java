package com.online.retail.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    @NotEmpty(message = "Order must have at least one item")
    @Valid
    private List<LineItemRequestDto> items;
}