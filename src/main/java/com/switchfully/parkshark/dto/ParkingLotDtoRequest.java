package com.switchfully.parkshark.dto;

import com.switchfully.parkshark.services.mapper.AddressDtoRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingLotDtoRequest {

  @NotBlank(message = "Name can not be empty")
  @NotNull
  String name;

    @NotBlank(message = "Parking category can not be empty")
    @NotNull
    String parkingLotCategory;

  @Positive(message = "Price should be greater than zero")
  @NotNull
  Double pricePerHour;

  @Min(value = 1, message = "Capacity needs to be at least one")
  @NotNull
  Integer capacity;

  @NotNull(message = "Contact id can not be empty")
  @NotNull
  Long contactId;

  @Valid
  AddressDtoRequest addressDtoRequest;

  @NotNull(message = "Division id can not be empty")
  Long divisionId;
}
