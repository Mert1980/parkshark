package com.switchfully.parkshark.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Data @ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressDtoResponse {
  String streetName;
  String streetNumber;
  String postalCode;
  String city;
}
