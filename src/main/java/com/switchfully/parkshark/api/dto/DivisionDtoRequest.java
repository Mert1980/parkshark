package com.switchfully.parkshark.api.dto;

import com.switchfully.parkshark.domain.Person;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DivisionDtoRequest {

    String name;
    String originalName;
    Person directorId;
}
