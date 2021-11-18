package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.dto.ParkingLotDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotDtoResponse;

public class ParkingLotMapper {

    public ParkingLot toEntity(ParkingLotDtoRequest parkingLotDtoRequest) {
        ParkingLot entity = new ParkingLot();
        return entity;
    }

    public ParkingLotDtoResponse toResponse(ParkingLot parkingLot) {
        ParkingLotDtoResponse parkingLotDtoResponse = new ParkingLotDtoResponse();
        return parkingLotDtoResponse;
    }
}
