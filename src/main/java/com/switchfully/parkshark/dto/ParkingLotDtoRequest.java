package com.switchfully.parkshark.dto;

import com.switchfully.parkshark.services.mapper.AddressDtoRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingLotDtoRequest {

    @NotBlank(message = "Name can not be empty")
    @NotNull
    String name;

    @NotBlank(message = "Name can not be empty")
    @NotNull
    String parkingLotCategory;

    @Positive(message = "Price should be greater than zero")
    @NotNull
    Double pricePerHour;

    @Min(value = 1, message = "Capacity needs to be at least one")
    @NotNull
    Integer capacity;

    @NotNull(message = "Contact id can not be empty")
    @NotNull
    Long contactId;

    @Valid
    AddressDtoRequest addressDtoRequest;

    @NotNull(message = "Division id can not be empty")
    Long divisionId;
}
