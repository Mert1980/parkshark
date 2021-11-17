package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import com.switchfully.parkshark.repositories.DivisionRepository;
import com.switchfully.parkshark.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DivisionMapper {

    @Autowired
    private final DivisionRepository divisionRepository;
    private final PersonRepository personRepository;

    public DivisionMapper(DivisionRepository divisionRepository, PersonRepository personRepository) {
        this.divisionRepository = divisionRepository;
        this.personRepository = personRepository;
    }


    public Division toEntity(DivisionDtoRequest createDivisionDTO) {
        Division parentDivision = divisionRepository.getById(createDivisionDTO.getParentDivisionId());
        Person director = personRepository.getById(createDivisionDTO.getDirectorId());

        Division division = new Division();
        division.setName(createDivisionDTO.getName());
        division.setOriginalName(createDivisionDTO.getOriginalName());
        division.setDirector(director);
        division.setParentDivision(parentDivision);
        return division;
    }


    public DivisionDtoResponse toResponse(Division division) {
        Long parentDivisionId = 0L;

        if (division.getParentDivision() != null) {
            parentDivisionId = division.getParentDivision().getId();
        }

        return DivisionDtoResponse.builder()
                .divisionId(division.getId())
                .name(division.getName())
                .originalName(division.getOriginalName())
                .directorId(division.getDirector().getId())
                .parentDivisionId(parentDivisionId)
                .build();
    }

    public List<DivisionDtoResponse> toResponse(List<Division> divisions) {
        return divisions.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
