package com.switchfully.parkshark.services.mapper;

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
public class AddressDtoRequest {
    @NotBlank @NotNull
    String streetName;
    @NotBlank @NotNull
    String streetNumber;
    @NotBlank @NotNull
    String postalCode;
    @NotBlank @NotNull
    String city;
}
