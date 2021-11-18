package com.switchfully.parkshark.services;

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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Cascade(CascadeType.ALL)
    public PersonDtoResponse registerMember(PersonDtoRequest personDtoRequest) {
        Person person = personRepository.save(personMapper.toEntity(personDtoRequest));
        return personMapper.toResponse(person);
    }

    public PersonDtoResponse getMemberById(long id) {

        return personMapper.toResponse(personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id)));
    }

    public PersonDtoResponse deleteMember(long id) {
        return null;
    }

    protected void assertValidPersonId(Long id) {
        if (personRepository.findById(id).isEmpty()) {
            throw new PersonNotFoundException(id);
        }
    }

    private void assertValidPersonDTORequest(PersonDtoRequest personDtoRequest) {
        if (!EmailValidator.getInstance().isValid(personDtoRequest.getEmail())) {
            throw new IllegalArgumentException("Invalid email for user");
        }
    }

    private void assertIsNotNullOrEmpty(PersonDtoRequest personDtoRequest) {
//        if (Stream.of(personDtoRequest.getFirstName(),
//                        personDtoRequest.getLastName())
//                .anyMatch(Objects::isNull)) {
//            throw new IllegalArgumentException("All fields are required")
//
//        }
       // Arrays.stream(PersonDtoRequest.class.getDeclaredFields()).anyMatch(str -> str.toString().isEmpty());
    }
}
