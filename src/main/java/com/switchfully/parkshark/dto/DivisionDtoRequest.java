package com.switchfully.parkshark.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DivisionDtoRequest {

    @NotBlank @NotNull
    String name;

    @NotBlank @NotNull
    String originalName;

    @NotNull
    Long directorId;

    Long parentDivisionId;
}
