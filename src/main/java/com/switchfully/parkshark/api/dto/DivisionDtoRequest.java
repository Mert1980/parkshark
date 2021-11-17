package com.switchfully.parkshark.api.dto;

import com.switchfully.parkshark.domain.Person;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DivisionDtoRequest {

    String name;
    String originalName;
    Person directorId;
}
