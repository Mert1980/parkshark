package com.switchfully.parkshark.dto;

import com.switchfully.parkshark.domain.Division;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingLotDtoResponseForGetAll {
    Long id;
    String name;
    Integer capacity;
    String email;
    String mobilePhoneNumber;
}
