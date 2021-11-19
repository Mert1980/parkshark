package com.switchfully.parkshark.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotAllocationDtoRequest {

  Long personId;
  String licensePlateNumber;
  Long parkingLotId;

}
