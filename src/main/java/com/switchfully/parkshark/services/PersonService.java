package com.switchfully.parkshark.services;

import com.switchfully.parkshark.api.dto.PersonDtoRequest;
import com.switchfully.parkshark.api.dto.PersonDtoResponse;
import com.switchfully.parkshark.repositories.PersonRepository;
import com.switchfully.parkshark.services.mapper.PersonMapper;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class PersonService {

  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
    this.personRepository = personRepository;
    this.personMapper = personMapper;
  }

  public List<PersonDtoRequest> getAllMembers() {
    return null;
  }


  public PersonDtoResponse registerMember(PersonDtoRequest personDtoRequest) {
    personRepository.save(personMapper.toEntity(personDtoRequest));
    return personMapper.toResponse(personDtoRequest);
  }

  public PersonDtoRequest getMemberById(long id) {
    return null;
  }

  public PersonDtoResponse deleteMember(long id) {
    return null;
  }
}
