package com.switchfully.parkshark.services;

import com.switchfully.parkshark.domain.MembershipLevelCategory;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.PersonDtoRequest;
import com.switchfully.parkshark.dto.PersonDtoResponse;
import com.switchfully.parkshark.repositories.PersonRepository;
import com.switchfully.parkshark.services.exceptions.PersonNotFoundException;
import com.switchfully.parkshark.services.mapper.PersonMapper;
import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDtoResponse> getAllMembers() {
        return personRepository.findAll()
                .stream()
                .map(person -> personMapper.toResponse(person))
                .collect(Collectors.toList());
    }

    @Cascade(CascadeType.PERSIST)
    public PersonDtoResponse registerMember(PersonDtoRequest personDtoRequest) {
        assertValidPersonDTORequest(personDtoRequest);

        setMemberShipLevelToBronzeIfNothingIsProvided(personDtoRequest);

        return personMapper.toResponse(personRepository.save(personMapper.toEntity(personDtoRequest)));
    }

    private void setMemberShipLevelToBronzeIfNothingIsProvided(PersonDtoRequest personDtoRequest) {
        if (personDtoRequest.getMembershipLevel().isEmpty()) {
            personDtoRequest.setMembershipLevel(String.valueOf(MembershipLevelCategory.Bronze));
        }
    }

    public PersonDtoResponse getMemberById(long id) {
        return personMapper.toResponse(personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id)));
    }

    public Person findMemberById(long id) {
        return personRepository.getById(id);
    }

    public PersonDtoResponse deleteMember(long id) {
        return null;
    }

    protected void assertValidPersonId(Long id) {
        if (personRepository.findById(id).isEmpty()) {
            throw new PersonNotFoundException(id);
        }
    }

    protected void assertValidPersonDTORequest(PersonDtoRequest personDtoRequest) {
        if (!EmailValidator.getInstance().isValid(personDtoRequest.getEmail())) {
            throw new IllegalArgumentException("Invalid email for user");
        }
    }

}
