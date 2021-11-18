package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.ParkingLotCategory;
import com.switchfully.parkshark.dto.ParkingLotDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    private final AddressMapper addressMapper;

    public ParkingLotMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public ParkingLot toEntity(ParkingLotDtoRequest request) {
        return ParkingLot.builder()
                .name(request.getName())
                .capacity(request.getCapacity())
                .pricePerHour(request.getPricePerHour())
                .address(addressMapper.toEntity(request.getAddressDtoRequest()))
                .parkingLotCategory(ParkingLotCategory.valueOf(request.getParkingLotCategory()))
                .build();
    }

    // TODO divisonId not implemented
    public ParkingLotDtoResponse toResponse(ParkingLot entity) {
        return ParkingLotDtoResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .parkingLotCategory(String.valueOf(entity.getParkingLotCategory()))
                .pricePerHour(entity.getPricePerHour())
                .capacity(entity.getCapacity())
                .addressDtoResponse(addressMapper.toResponse(entity.getAddress()))
                .build();

    }
}
