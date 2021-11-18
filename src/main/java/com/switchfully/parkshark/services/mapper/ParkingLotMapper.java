package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.dto.ParkingLotDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotDtoResponse;

public class ParkingLotMapper {

    private final AddressMapper addressMapper;

    public ParkingLotMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public ParkingLot toEntity(ParkingLotDtoRequest request) {
        ParkingLot entity = ParkingLot.builder()
                .name(request.getName()   .maxCapacity(request.getMaxCapacity())
                .pricePerHour(request.getPricePerHour())
                .address(request.getAddressDtoRequest())
                .parkingCategory(request.getParkingCategory())
                .build();
        return entity;
    }

    public ParkingLotDtoResponse toResponse(ParkingLot parkingLot) {
        ParkingLotDtoResponse parkingLotDtoResponse = new ParkingLotDtoResponse();
        return parkingLotDtoResponse;
    }
}
