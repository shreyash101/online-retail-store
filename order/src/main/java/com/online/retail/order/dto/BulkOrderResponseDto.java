package com.online.retail.order.dto;

import lombok.Data;

import java.util.List;

@Data
public class BulkOrderResponseDto {
    private List<OrderResponseDto> orders;
    private List<Long> missingOrderIds;
}