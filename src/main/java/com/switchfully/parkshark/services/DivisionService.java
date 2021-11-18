package com.switchfully.parkshark.services;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import com.switchfully.parkshark.repositories.DivisionRepository;
import com.switchfully.parkshark.repositories.PersonRepository;
import com.switchfully.parkshark.services.mapper.DivisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionService {

    @Autowired
    private final DivisionRepository divisionRepository;
    private final PersonRepository personRepository;
    private final DivisionMapper divisionMapper;
    private final PersonService personService;

    public DivisionService(DivisionRepository divisionRepository, PersonRepository personRepository, DivisionMapper divisionMapper, PersonService personService) {
        this.divisionRepository = divisionRepository;
        this.personRepository = personRepository;
        this.divisionMapper = divisionMapper;
        this.personService = personService;
    }

    public DivisionDtoResponse save(DivisionDtoRequest createDivisionDTO) {

        personService.assertPersonId(createDivisionDTO.getDirectorId());

        if (createDivisionDTO.getParentDivisionId() != null) {
            assertDivisionId(createDivisionDTO.getParentDivisionId());
        }

        Division division = divisionMapper.toEntity(createDivisionDTO);

        if (createDivisionDTO.getParentDivisionId() != null) {
            Division parentDivision = divisionRepository.getById(createDivisionDTO.getParentDivisionId());
            division.setParentDivision(parentDivision);
        }

        Person director = personRepository.getById(createDivisionDTO.getDirectorId());
        division.setDirector(director);

        return divisionMapper.toResponse(divisionRepository.save(division));
    }

    public List<DivisionDtoResponse> getAllDivisions() {
        return divisionMapper.toResponse(divisionRepository.findAll());
    }

    public DivisionDtoResponse getDivisionById(Long divisionId) {
        Optional<Division> divisionOptional = divisionRepository.findById(divisionId);

        if (divisionOptional.isEmpty()) {
            throw new IllegalArgumentException("Id not found");
        }

        return divisionMapper.toResponse(divisionOptional.get());
    }

    private void assertDivisionId(Long id) {
        if (divisionRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Parent division does not exist");
        }
    }
}
