package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.PersonDtoRequest;
import com.switchfully.parkshark.dto.PersonDtoResponse;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
  private final AddressMapper addressMapper;

  @Autowired
  public PersonMapper(AddressMapper addressMapper) {
    this.addressMapper = addressMapper;
  }


  public Person toEntity(PersonDtoRequest personDtoRequest) {
    return Person.builder()
        .firstName(personDtoRequest.getFirstName())
        .lastName(personDtoRequest.getLastName())
        .email(personDtoRequest.getEmail())
        .address(addressMapper.toEntity(personDtoRequest.getAddressDtoRequest()))
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
          .addressDtoResponse(addressMapper.toResponse(person.getAddress()))
          .phoneNumberMobile(person.getPhoneNumberMobile())
          .phoneNumberLocal(person.getPhoneNumberLocal())
          .licencePlateNumber(person.getLicensePlateNumber())
          .registrationDate(person.getRegistrationDate().toString())
          .build();
    }
}
