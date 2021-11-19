package com.switchfully.parkshark.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotAllocationDtoResponse {

  long id;

  long personId;

  String licencePlateNumber;

  long parkingLotId;





}
