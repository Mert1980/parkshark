package com.switchfully.parkshark.api.dto;

import com.switchfully.parkshark.domain.Person;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateDivisionResponse {

    Long divisionId;
    String name;
    String originalName;
    Long directorId;
}
