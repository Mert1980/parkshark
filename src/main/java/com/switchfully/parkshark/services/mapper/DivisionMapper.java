package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<DivisionDtoResponse> toResponse(List<Division> divisions) {
        return divisions.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
