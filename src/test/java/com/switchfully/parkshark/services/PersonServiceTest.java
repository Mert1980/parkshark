package com.switchfully.parkshark.services;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.MembershipLevelCategory;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.AddressDtoResponse;
import com.switchfully.parkshark.dto.PersonDtoRequest;
import com.switchfully.parkshark.dto.PersonDtoResponse;
import com.switchfully.parkshark.repositories.PersonRepository;
import com.switchfully.parkshark.services.exceptions.PersonNotFoundException;
import com.switchfully.parkshark.services.mapper.AddressDtoRequest;
import com.switchfully.parkshark.services.mapper.PersonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PersonServiceTest {
    private PersonMapper personMapperMock;
    private PersonRepository personRepositoryMock;
    private PersonService personServiceMock;

    @BeforeEach
    void setUp() {
        personRepositoryMock = Mockito.mock(PersonRepository.class);
        personMapperMock = Mockito.mock(PersonMapper.class);
        personServiceMock = new PersonService(personRepositoryMock,
                personMapperMock);
    }

    @Test
    void whenAddingPerson_thenPersonRepositorySaveMethodIsCalled() {

        AddressDtoRequest addressDtoRequest = AddressDtoRequest.builder()
                .streetName("Cool Street")
                .streetNumber("5")
                .postalCode("5641")
                .city("CoolVille")
                .build();

        Address addressEntity = Address.builder()
                .streetName("Cool Street")
                .streetNumber("5")
                .postalCode("5641")
                .city("CoolVille")
                .build();

        PersonDtoRequest personDtoRequest = PersonDtoRequest.builder()
                .firstName("Jhon")
                .lastName("Doe")
                .email("john@doe.be")
                .addressDtoRequest(addressDtoRequest)
                .phoneNumberMobile("074777777")
                .phoneNumberLocal("069887744")
                .licencePlateNumber("1-ppp-987")
                .membershipLevel("Bronze")
                .registrationDate(LocalDate.now().toString())
                .build();

        Person personEntity = Person.builder()
                .firstName("Jhon")
                .lastName("Doe")
                .email("john@doe.be")
                .address(addressEntity)
                .phoneNumberMobile("074777777")
                .phoneNumberLocal("069887744")
                .licencePlateNumber("1-ppp-555")
                .registrationDate(LocalDate.now())
                .membershipLevel(MembershipLevelCategory.Gold)
                .build();

        AddressDtoResponse addressDtoResponse =
                AddressDtoResponse.builder()
                        .streetName("Cool Street")
                        .streetNumber("5")
                        .postalCode("5641")
                        .city("CoolVille")
                        .build();

        PersonDtoResponse personDtoResponse = PersonDtoResponse.builder()
                .id(1L)
                .firstName("Jhon")
                .lastName("Doe")
                .email("john@doe.be")
                .phoneNumberLocal("454545")
                .licencePlateNumber("688798798")
                .registrationDate(LocalDate.now().toString())
                .addressDtoResponse(addressDtoResponse)
                .phoneNumberMobile("684789798")
                .membershipLevel(MembershipLevelCategory.Bronze.toString())
                .build();

        Mockito.when(personMapperMock.toEntity(any(PersonDtoRequest.class))).thenReturn(personEntity);

        Mockito.when(personMapperMock.toResponse(any(Person.class))).thenReturn(personDtoResponse);

        personServiceMock.registerMember(personDtoRequest);

        Mockito.verify(personRepositoryMock).save(any(Person.class));
    }

    @Test
    void whenEmailIsValid_ValidationDoesNotThrowException() {

        AddressDtoRequest addressDtoRequest = AddressDtoRequest.builder()
                .streetName("Cool Street")
                .streetNumber("5")
                .postalCode("5641")
                .city("CoolVille")
                .build();

        PersonDtoRequest personDtoRequest = PersonDtoRequest.builder()
                .firstName("Jhon")
                .lastName("Doe")
                .email("john@doe.be")
                .addressDtoRequest(addressDtoRequest)
                .phoneNumberMobile("074777777")
                .phoneNumberLocal("069887744")
                .licencePlateNumber("1-ppp-987")
                .registrationDate(LocalDate.now().toString())
                .build();

        assertDoesNotThrow(() ->
                personServiceMock.assertValidPersonDTORequest(personDtoRequest));
    }

    @Test
    void whenEmailIsInValid_ValidationDoesThrowException() {

        AddressDtoRequest addressDtoRequest = AddressDtoRequest.builder()
                .streetName("Cool Street")
                .streetNumber("5")
                .postalCode("5641")
                .city("CoolVille")
                .build();

        PersonDtoRequest personDtoRequest = PersonDtoRequest.builder()
                .firstName("Jhon")
                .lastName("Doe")
                .email("johndoebe")
                .addressDtoRequest(addressDtoRequest)
                .phoneNumberMobile("074777777")
                .phoneNumberLocal("069887744")
                .licencePlateNumber("1-ppp-987")
                .registrationDate(LocalDate.now().toString())
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                personServiceMock.assertValidPersonDTORequest(personDtoRequest));
    }

    @Test
    void whenPersonIdIsValid_AssertValidPersonIdDoesNotThrowException() {

        Address addressEntity = Address.builder()
                .streetName("Cool Street")
                .streetNumber("5")
                .postalCode("5641")
                .city("CoolVille")
                .build();

        Person personEntity = Person.builder()
                .firstName("Jhon")
                .lastName("Doe")
                .email("john@doe.be")
                .address(addressEntity)
                .phoneNumberMobile("074777777")
                .phoneNumberLocal("069887744")
                .licencePlateNumber("1-ppp-555")
                .registrationDate(LocalDate.now())
                .build();

        Mockito.when(personRepositoryMock.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(personEntity));

        assertDoesNotThrow(() ->
                personServiceMock.assertValidPersonId(1L));
    }

    @Test
    void whenPersonIdIsInvalid_AssertValidPersonIdDoesThrowException() {
        Mockito.when(personRepositoryMock.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () ->
                personServiceMock.assertValidPersonId(1L));
    }

    @Test
    void whenPersonDTOHasNoMemberShipLevel_AssertSetMemberShipLeveLMethodSetsPersonToBronzeLevel() {

        AddressDtoRequest addressDtoRequest = AddressDtoRequest.builder()
                .streetName("Cool Street")
                .streetNumber("5")
                .postalCode("5641")
                .city("CoolVille")
                .build();

        PersonDtoRequest personDtoRequest = PersonDtoRequest.builder()
                .firstName("Jhon")
                .lastName("Doe")
                .email("johndoebe")
                .addressDtoRequest(addressDtoRequest)
                .phoneNumberMobile("074777777")
                .phoneNumberLocal("069887744")
                .licencePlateNumber("1-ppp-987")
                .registrationDate(LocalDate.now().toString())
                .membershipLevel("")
                .build();

        personServiceMock.setMemberShipLevelToBronzeIfNothingIsProvided(personDtoRequest);

        assertEquals(MembershipLevelCategory.Bronze.toString(), personDtoRequest.getMembershipLevel());
    }
}
