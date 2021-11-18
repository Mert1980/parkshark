package com.switchfully.parkshark.dto;

import com.switchfully.parkshark.services.mapper.AddressDtoRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingLotDtoRequest {

    @NotBlank @NotNull
    String name;
    @NotBlank @NotNull
    String parkingCategory;
    @NotBlank @NotNull
    Double pricePerHour;
    @NotBlank @NotNull
    Integer maxCapacity;
    @NotBlank @NotNull
    Long contactId;
    @Valid
    AddressDtoRequest addressDtoRequest;
    @NotBlank @NotNull
    Long divisionId;

}
