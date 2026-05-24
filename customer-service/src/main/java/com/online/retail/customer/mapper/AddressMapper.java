package com.online.retail.customer.mapper;

import com.online.retail.customer.dto.AddressDto;
import com.online.retail.customer.entity.CustomerAddress;

public class AddressMapper {
    public static CustomerAddress toEntity(AddressDto dto) {
        CustomerAddress address = new CustomerAddress();
        address.setCity(dto.getCity());
        address.setPincode(dto.getPincode());
        address.setLayout(dto.getLayout());
        address.setDoorNo(dto.getDoorNo());
        address.setStreetName(dto.getStreetName());
        return address;
    }

    public static AddressDto toDto(CustomerAddress address) {
        AddressDto dto = new AddressDto();
        dto.setCity(address.getCity());
        dto.setLayout(address.getLayout());
        dto.setDoorNo(address.getDoorNo());
        dto.setPincode(address.getPincode());
        dto.setStreetName(address.getStreetName());
        return dto;
    }
}