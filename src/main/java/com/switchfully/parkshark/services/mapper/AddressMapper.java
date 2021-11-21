package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.dto.AddressDtoRequest;
import com.switchfully.parkshark.dto.AddressDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

  public AddressDtoResponse toResponse(Address entity) {
    return AddressDtoResponse.builder()
        .streetName(entity.getStreetName())
        .streetNumber(entity.getStreetNumber())
        .postalCode(entity.getPostalCode())
        .city(entity.getCity())
        .build();
  }

  public Address toEntity(AddressDtoRequest request) {
    return Address.builder()
        .streetName(request.getStreetName())
        .streetNumber(request.getStreetNumber())
        .postalCode(request.getPostalCode())
        .city(request.getCity())
        .build();
  }
}
