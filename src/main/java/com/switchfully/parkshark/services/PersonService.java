package com.switchfully.parkshark.services;

import com.switchfully.parkshark.api.dto.PersonDtoRequest;
import com.switchfully.parkshark.api.dto.PersonDtoResponse;
import com.switchfully.parkshark.repositories.PersonRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class PersonService {

  private PersonRepository personRepository;


  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public List<PersonDtoRequest> getAllMembers() {
    return null;
  }


  public PersonDtoResponse registerMember(PersonDtoRequest personDtoRequest) {
    return personRepository.save(personDtoRequest);
  }

  public PersonDtoRequest getMemberById(long id) {
    return null;
  }

  public PersonDtoResponse deleteMember(long id) {

  }
}
