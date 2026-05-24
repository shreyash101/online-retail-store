package com.online.retail.shopping_service.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class BulkOrderResponseDto {
    private List<OrderResponseDto> orders;
    private List<Long> missingOrderIds;
}