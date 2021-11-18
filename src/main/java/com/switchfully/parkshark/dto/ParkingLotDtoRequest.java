package com.switchfully.parkshark.dto;

import com.switchfully.parkshark.services.mapper.AddressDtoRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    Integer capacity;
    @NotBlank @NotNull
    Long contactId;
    @Valid
    AddressDtoRequest addressDtoRequest;
    @NotBlank @NotNull
    Long divisionId;

}
