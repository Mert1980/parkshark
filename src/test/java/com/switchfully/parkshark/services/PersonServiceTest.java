package com.switchfully.parkshark.services;

import com.switchfully.parkshark.domain.Address;
import com.switchfully.parkshark.domain.MembershipLevelCategory;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.AddressDtoResponse;
import com.switchfully.parkshark.dto.PersonDtoRequest;
import com.switchfully.parkshark.dto.PersonDtoResponse;
import com.switchfully.parkshark.repositories.PersonRepository;
import com.switchfully.parkshark.services.exceptions.PersonNotFoundException;
import com.switchfully.parkshark.dto.AddressDtoRequest;
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

    private AddressDtoRequest addressDtoRequest;
    private Address addressEntity;
    private PersonDtoRequest personDtoRequest;
    private Person personEntity;
    private AddressDtoResponse addressDtoResponse;
    private PersonDtoResponse personDtoResponse;

    @BeforeEach
    void setUp() {
        personRepositoryMock = Mockito.mock(PersonRepository.class);
        personMapperMock = Mockito.mock(PersonMapper.class);
        personServiceMock = new PersonService(personRepositoryMock,
                personMapperMock);

        addressDtoRequest = AddressDtoRequest.builder()
                .streetName("Cool Street")
                .streetNumber("5")
                .postalCode("5641")
                .city("CoolVille")
                .build();

        addressEntity = Address.builder()
                .streetName("Cool Street")
                .streetNumber("5")
                .postalCode("5641")
                .city("CoolVille")
                .build();

        personDtoRequest = PersonDtoRequest.builder()
                .firstName("Jhon")
                .lastName("Doe")
                .email("john@doe.be")
                .addressDtoRequest(addressDtoRequest)
                .phoneNumberMobile("074777777")
                .phoneNumberLocal("069887744")
                .licencePlateNumber("1-ppp-987")
                .membershipLevel("Bronze")
                .build();

        personEntity = Person.builder()
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

        addressDtoResponse =
                AddressDtoResponse.builder()
                        .streetName("Cool Street")
                        .streetNumber("5")
                        .postalCode("5641")
                        .city("CoolVille")
                        .build();

        personDtoResponse = PersonDtoResponse.builder()
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
    }

    @Test
    void whenAddingPerson_thenPersonRepositorySaveMethodIsCalled() {
        Mockito.when(personMapperMock.toEntity(any(PersonDtoRequest.class))).thenReturn(personEntity);
        Mockito.when(personMapperMock.toResponse(any(Person.class))).thenReturn(personDtoResponse);

        personServiceMock.registerMember(personDtoRequest);

        Mockito.verify(personRepositoryMock).save(any(Person.class));
    }

    @Test
    void whenEmailIsValid_ValidationDoesNotThrowException() {
        assertDoesNotThrow(() ->
                personServiceMock.assertValidPersonDTORequest(personDtoRequest));
    }

    @Test
    void whenEmailIsInValid_ValidationDoesThrowException() {

        PersonDtoRequest invalidPersonDtoRequest = PersonDtoRequest.builder()
                .firstName("Jhon")
                .lastName("Doe")
                .email("johndoebe")
                .addressDtoRequest(addressDtoRequest)
                .phoneNumberMobile("074777777")
                .phoneNumberLocal("069887744")
                .licencePlateNumber("1-ppp-987")
                .build();

        assertThrows(IllegalArgumentException.class, () ->
                personServiceMock.assertValidPersonDTORequest(invalidPersonDtoRequest));
    }

    @Test
    void whenPersonIdIsValid_AssertValidPersonIdDoesNotThrowException() {

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
        PersonDtoRequest personDtoRequest = PersonDtoRequest.builder()
                .firstName("Jhon")
                .lastName("Doe")
                .email("johndoebe")
                .addressDtoRequest(addressDtoRequest)
                .phoneNumberMobile("074777777")
                .phoneNumberLocal("069887744")
                .licencePlateNumber("1-ppp-987")
                .membershipLevel("")
                .build();

        personServiceMock.setMemberShipLevelToBronzeIfNothingIsProvided(personDtoRequest);

        assertEquals(MembershipLevelCategory.Bronze.getName(), personDtoRequest.getMembershipLevel());
    }
}
