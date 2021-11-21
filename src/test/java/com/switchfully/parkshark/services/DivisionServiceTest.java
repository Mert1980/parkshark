package com.switchfully.parkshark.services;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import com.switchfully.parkshark.repositories.DivisionRepository;
import com.switchfully.parkshark.repositories.PersonRepository;
import com.switchfully.parkshark.services.exceptions.DivisionNotFoundException;
import com.switchfully.parkshark.services.exceptions.PersonNotFoundException;
import com.switchfully.parkshark.services.mapper.DivisionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class DivisionServiceTest {

    private DivisionService divisionService;
    private DivisionRepository divisionRepositoryMock;
    private DivisionMapper divisionMapperMock;
    private PersonRepository personRepositoryMock;
    private PersonService personServiceMock;

    private DivisionDtoRequest divisionToSave;
    private DivisionDtoResponse divisionDtoResponse;
    private Person director;
    private Division parentDivision;
    private Division subdivision;

    @BeforeEach
    void setUp() {
        divisionRepositoryMock = Mockito.mock(DivisionRepository.class);
        personRepositoryMock = Mockito.mock(PersonRepository.class);
        divisionMapperMock = Mockito.mock(DivisionMapper.class);
        personServiceMock = Mockito.mock(PersonService.class);
        divisionService = new DivisionService(divisionRepositoryMock,
                personRepositoryMock, divisionMapperMock, personServiceMock);

        divisionToSave = DivisionDtoRequest.builder()
                .name("test")
                .originalName("test")
                .directorId(1L)
                .parentDivisionId(1L)
                .build();

        divisionDtoResponse =
                DivisionDtoResponse.builder()
                        .divisionId(1L)
                        .directorId(1L)
                        .name("Test")
                        .originalName("Test")
                        .parentDivisionId(1L)
                        .build();

        director = Person.builder().build();

        parentDivision = new Division();
        parentDivision.setId(1L);
        parentDivision.setDirector(director);
        parentDivision.setOriginalName("Test");
        parentDivision.setName("Test");

        subdivision = new Division();
        subdivision.setId(2L);
        subdivision.setDirector(director);
        subdivision.setOriginalName("Test");
        subdivision.setName("Test");
    }


    @Test
    void whenAddingDivision_thenDivisionRepositorySaveMethodIsCalled() {

        Mockito.when(divisionRepositoryMock.findById(any(Long.class))).thenReturn(java.util.Optional.of((parentDivision)));
        Mockito.when(personRepositoryMock.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(director));
        Mockito.when(divisionMapperMock.toEntity(any(DivisionDtoRequest.class))).thenReturn(subdivision);
        Mockito.when(divisionMapperMock.toResponse(any(Division.class))).thenReturn(divisionDtoResponse);
        Mockito.when(divisionRepositoryMock.existsById(any(Long.class))).thenReturn(true);

        divisionService.save(divisionToSave);

        Mockito.verify(divisionRepositoryMock).save(any(Division.class));
    }

    @Test
    void whenAddingDivisionWithInvalidDirector_thenExceptionIsThrown() {
        Mockito.doThrow(PersonNotFoundException.class).when(personServiceMock).assertValidPersonId(any(Long.class));

        Mockito.when(divisionRepositoryMock.findById(any(Long.class))).thenReturn(java.util.Optional.of((parentDivision)));
        Mockito.when(divisionMapperMock.toEntity(any(DivisionDtoRequest.class))).thenReturn(subdivision);
        Mockito.when(divisionMapperMock.toResponse(any(Division.class))).thenReturn(divisionDtoResponse);
        Mockito.when(divisionRepositoryMock.existsById(any(Long.class))).thenReturn(true);

        assertThrows(PersonNotFoundException.class, () ->
                divisionService.save(divisionToSave));
    }

    @Test
    void
    whenAddingDivisionWithInvalidParentDivision_thenExceptionIsThrown() {
        Mockito.when(personRepositoryMock.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(director));
        Mockito.when(divisionMapperMock.toEntity(any(DivisionDtoRequest.class))).thenReturn(subdivision);
        Mockito.when(divisionMapperMock.toResponse(any(Division.class))).thenReturn(divisionDtoResponse);
        Mockito.when(divisionRepositoryMock.existsById(any(Long.class))).thenReturn(false);

        assertThrows(DivisionNotFoundException.class, () ->
                divisionService.save(divisionToSave));
    }

    @Test
    void
    whenParentDivisionIdNull_assertValidDivisionIdAndIdNotNullReturnsNoException() {
        Mockito.when(divisionRepositoryMock.existsById(any(Long.class))).thenReturn(false);

        assertDoesNotThrow(() ->
                divisionService.assertValidDivisionIdAndIdNotNull(null));
    }

    @Test
    void
    whenParentDivisionIdIsValid_assertValidDivisionIdAndIdNotNullReturnsNoException() {
        Mockito.when(divisionRepositoryMock.existsById(any(Long.class))).thenReturn(true);

        assertDoesNotThrow(() ->
                divisionService.assertValidDivisionIdAndIdNotNull(1L));
    }

    @Test
    void
    whenParentDivisionIdIsInvalid_assertValidDivisionIdAndIdNotNullReturnsException() {
        Mockito.when(divisionRepositoryMock.existsById(any(Long.class))).thenReturn(false);

        assertThrows(DivisionNotFoundException.class, () ->
                divisionService.assertValidDivisionIdAndIdNotNull(1L));
    }
}
