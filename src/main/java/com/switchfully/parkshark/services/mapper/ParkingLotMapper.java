package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.ParkingLotCategory;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.ParkingLotDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotDtoResponse;
import com.switchfully.parkshark.dto.ParkingLotDtoResponseForGetAll;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    private final AddressMapper addressMapper;
    private final DivisionMapper divisionMapper;

    public ParkingLotMapper(AddressMapper addressMapper, DivisionMapper divisionMapper) {
        this.addressMapper = addressMapper;
        this.divisionMapper = divisionMapper;
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
                .contactId(entity.getContactPerson().getId())
                .addressDtoResponse(addressMapper.toResponse(entity.getAddress()))
                .build();
    }

    public ParkingLotDtoResponseForGetAll toResponse(ParkingLot entity, Person person) {
        return ParkingLotDtoResponseForGetAll.builder()
                .id(entity.getId())
                .name(entity.getName())
                .capacity(entity.getCapacity())
                .email(person.getEmail())
                .mobilePhoneNumber(person.getPhoneNumberMobile())
                .build();
    }
}
