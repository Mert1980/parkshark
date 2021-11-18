package com.switchfully.parkshark.services;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import com.switchfully.parkshark.repositories.DivisionRepository;
import com.switchfully.parkshark.repositories.PersonRepository;
import com.switchfully.parkshark.services.exceptions.DivisionNotFoundException;
import com.switchfully.parkshark.services.mapper.DivisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DivisionService {

  @Autowired
  private final DivisionRepository divisionRepository;
  private final PersonRepository personRepository;
  private final DivisionMapper divisionMapper;
  private final PersonService personService;

  public DivisionService(DivisionRepository divisionRepository, PersonRepository personRepository,
      DivisionMapper divisionMapper, PersonService personService) {
    this.divisionRepository = divisionRepository;
    this.personRepository = personRepository;
    this.divisionMapper = divisionMapper;
    this.personService = personService;
  }

  public DivisionDtoResponse save(DivisionDtoRequest divisionDtoRequest) {

    personService.assertValidPersonId(divisionDtoRequest.getDirectorId());
    assertValidDivisionIdAndIdNotNull(divisionDtoRequest.getParentDivisionId());

    Division division = divisionMapper.toEntity(divisionDtoRequest);
//Set ParentId if not null
    if (divisionDtoRequest.getParentDivisionId() != null) {
      Division parentDivision = divisionRepository.getById(
          divisionDtoRequest.getParentDivisionId());
      division.setParentDivision(parentDivision);
    }
//Set DirectorId
    Person director = personRepository.getById(divisionDtoRequest.getDirectorId());
    division.setDirector(director);

    return divisionMapper.toResponse(divisionRepository.save(division));
  }

  private void assertValidDivisionIdAndIdNotNull(Long id) {
      if (id != null && !divisionRepository.existsById(id) ) {
          throw new DivisionNotFoundException(id);
      }
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
