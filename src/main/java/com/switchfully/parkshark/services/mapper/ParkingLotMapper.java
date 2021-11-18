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

    // TODO divisonId not implemented
    public ParkingLotDtoResponse toResponse(ParkingLot entity) {
        return ParkingLotDtoResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .parkingCategory(entity.getParkingLotCategory())
                .pricePerHour(entity.getPricePerHour())
                .maxCapacity(entity.getMaxCapacity())
                .addressDtoResponse(addressMapper.toResponse(entity.getAddress()))
        .build();

    }
}
