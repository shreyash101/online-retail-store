package com.online.retail.shopping_service.dto.customer;

import com.online.retail.shopping_service.dto.order.BulkOrderResponseDto;
import lombok.Data;

@Data
public class CustomerOrderDetailsDto {
    private BulkOrderResponseDto orders;
    private CustomerDetailsDto customer;
}
