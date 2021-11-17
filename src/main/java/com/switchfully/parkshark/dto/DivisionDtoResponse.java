package com.switchfully.parkshark.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DivisionDtoResponse {

    Long divisionId;
    String name;
    String originalName;
    Long directorId;
    Long parentDivisionId;
}
