package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.api.dto.PersonDtoRequest;
import com.switchfully.parkshark.api.dto.PersonDtoResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PersonMapper {

  public Person toEntity(PersonDtoRequest personDtoRequest) {
    return Person.builder()
        .firstName(personDtoRequest.getFirstName())
        .lastName(personDtoRequest.getLastName())
        .email(personDtoRequest.getEmail())
        .address(personDtoRequest.getAddress())
        .phoneNumberMobile(personDtoRequest.getPhoneNumberMobile())
        .phoneNumberLocal(personDtoRequest.getPhoneNumberLocal())
        .licensePlateNumber(personDtoRequest.getLicencePlateNumber())
        .registrationDate(LocalDateTime.parse(personDtoRequest.getRegistrationDate()))
        .build();
  }


    public PersonDtoResponse toResponse(Person person) {
      return PersonDtoResponse.builder()
          .id(person.getId())
          .firstName(person.getFirstName())
          .lastName(person.getLastName())
          .email(person.getEmail())
          .address(person.getAddress())
          .phoneNumberMobile(person.getPhoneNumberMobile())
          .phoneNumberLocal(person.getPhoneNumberLocal())
          .licencePlateNumber(person.getLicensePlateNumber())
          .registrationDate(person.getRegistrationDate().toString())
          .build();
    }

    public PersonDtoResponse toResponse(PersonDtoRequest request){
      Person person = toEntity(request);
      return toResponse(person);
    }

}
