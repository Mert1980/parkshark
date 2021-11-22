package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.MembershipLevelCategory;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.PersonDtoRequest;
import com.switchfully.parkshark.dto.PersonDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
                .licencePlateNumber(personDtoRequest.getLicencePlateNumber())
                .registrationDate(LocalDate.now())
                .membershipLevel(MembershipLevelCategory.valueOf(personDtoRequest.getMembershipLevel()))
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
                .licencePlateNumber(person.getLicencePlateNumber())
                .registrationDate(person.getRegistrationDate().toString())
                .membershipLevel(person.getMembershipLevel().toString())
                .build();
    }
}
