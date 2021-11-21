package com.switchfully.parkshark.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotAllocationDtoRequest {

  @NotNull(message = "Person id can not be empty")
  Long personId;

  @NotBlank(message = "License plate number can not be empty")
  @NotNull
  String licensePlateNumber;

  @NotNull(message = "ParkingLot id can not be empty")
  Long parkingLotId;
}
