package com.switchfully.parkshark.services;

import com.switchfully.parkshark.dto.PersonDtoRequest;
import com.switchfully.parkshark.dto.PersonDtoResponse;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.repositories.PersonRepository;
import com.switchfully.parkshark.services.mapper.PersonMapper;
import java.util.List;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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

@Cascade(CascadeType.ALL)
  public PersonDtoResponse registerMember(PersonDtoRequest personDtoRequest) {
   Person person =   personRepository.save(personMapper.toEntity(personDtoRequest));
    return  personMapper.toResponse(person);
  }

  public PersonDtoRequest getMemberById(long id) {
    return null;
  }

  public PersonDtoResponse deleteMember(long id) {
    return null;
  }
}
