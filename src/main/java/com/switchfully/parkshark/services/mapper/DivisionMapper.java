package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.api.dto.DivisionDtoRequest;
import com.switchfully.parkshark.api.dto.DivisionDtoResponse;
import com.switchfully.parkshark.domain.Division;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    public Division toEntity(DivisionDtoRequest createDivisionDTO) {
        Division division = new Division();
        division.setName(createDivisionDTO.getName());
        division.setOriginalName(createDivisionDTO.getOriginalName());
        division.setDirector(createDivisionDTO.getDirectorId());

        return division;
    }


    public DivisionDtoResponse toResponse(Division division) {
        return DivisionDtoResponse.builder()
                .divisionId(division.getId())
                .name(division.getName())
                .originalName(division.getOriginalName())
                .directorId(division.getDirector().getId())
                .build();
    }
}
