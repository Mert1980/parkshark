package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.api.dto.CreateDivisionRequest;
import com.switchfully.parkshark.api.dto.CreateDivisionResponse;
import com.switchfully.parkshark.domain.Division;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    public Division toEntity(CreateDivisionRequest createDivisionDTO) {
        Division division = new Division();
        division.setName(createDivisionDTO.getName());
        division.setOriginalName(createDivisionDTO.getOriginalName());
        division.setDirector(createDivisionDTO.getDirectorId());

        return division;
    }


    public CreateDivisionResponse toResponse(Division division) {
        return CreateDivisionResponse.builder()
                .divisionId(division.getId())
                .name(division.getName())
                .originalName(division.getOriginalName())
                .directorId(division.getDirector().getId())
                .build();
    }
}
