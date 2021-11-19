package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.ParkingLotAllocation;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotAllocationMapper {


  public ParkingLotAllocation toEntity(ParkingLotAllocationDtoRequest request) {
    return ParkingLotAllocation.builder()
        .licensePlateNumber(request.getLicensePlateNumber())
        .parkingLotId(request.getParkingLotId())
        .build();
  }

  public ParkingLotAllocationDtoResponse toResponse(ParkingLotAllocation entity) {
    return ParkingLotAllocationDtoResponse.builder()
        .parkingLotAllocationId(entity.getId())
        .personId(entity.getPerson().getId())
        .licencePlateNumber(entity.getLicensePlateNumber())
        .parkingLotId(entity.getParkingLotId())
        .build();
  }


}