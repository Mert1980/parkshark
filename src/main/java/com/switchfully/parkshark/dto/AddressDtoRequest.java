package com.switchfully.parkshark.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressDtoRequest {

  @NotBlank(message = "Street name can not be empty")
  @NotNull
  String streetName;

  @NotBlank(message = "Street number can not be empty")
  @NotNull
  String streetNumber;

  @NotBlank(message = "Postal code can not be empty")
  @NotNull
  String postalCode;

  @NotBlank(message = "City can not be empty")
  @NotNull
  String city;
}
