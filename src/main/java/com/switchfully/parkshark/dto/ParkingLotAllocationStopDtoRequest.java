package com.switchfully.parkshark.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingLotAllocationStopDtoRequest {

  @NotNull(message = "ParkingLot allocation id can not be empty")
  Long parkingLotAllocationId;

  @NotNull(message = "Member id can not be empty")
  Long memberId;
}
