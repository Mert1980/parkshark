package com.switchfully.parkshark.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingLotAllocationStopDtoRequest {

  Long parkingLotAllocationId;
  Long memberId;
}
