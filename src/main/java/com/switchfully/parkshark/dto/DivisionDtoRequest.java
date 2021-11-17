package com.switchfully.parkshark.dto;

import com.switchfully.parkshark.domain.Division;
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
    Long directorId;
    Long parentDivisionId;
}
