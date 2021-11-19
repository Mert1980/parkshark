package com.switchfully.parkshark.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingLotDtoResponse {

  Long id;
  String name;
  String parkingLotCategory;
  Double pricePerHour;
  Integer capacity;
  Long contactId;
  AddressDtoResponse addressDtoResponse;
  DivisionDtoResponse division;
}
