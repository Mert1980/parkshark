package com.switchfully.parkshark.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @ToString  @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotAllocationDtoResponse {

  long parkingLotAllocationId;
  long personId;
  String licencePlateNumber;
  long parkingLotId;
  String startTime;
  String stopTime;
}
