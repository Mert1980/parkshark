package com.switchfully.parkshark.services;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import com.switchfully.parkshark.repositories.DivisionRepository;
import com.switchfully.parkshark.repositories.PersonRepository;
import com.switchfully.parkshark.services.mapper.DivisionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

class DivisionServiceTest {

    private DivisionService divisionService;
    private DivisionRepository divisionRepositoryMock;
    private DivisionMapper divisionMapperMock;
    private PersonRepository personRepositoryMock;

    @BeforeEach
    void setUp() {
        divisionRepositoryMock = Mockito.mock(DivisionRepository.class);
        personRepositoryMock = Mockito.mock(PersonRepository.class);
        divisionMapperMock = Mockito.mock(DivisionMapper.class);
        divisionService = new DivisionService(divisionRepositoryMock, personRepositoryMock, divisionMapperMock);
    }


    @Test
    void whenAddingDivision_thenDivisionRepositorySaveMethodIsCalled() {
        DivisionDtoRequest divisionToSave = DivisionDtoRequest.builder()
                .name("test")
                .originalName("test")
                .directorId(1L)
                .parentDivisionId(1L)
                .build();

        DivisionDtoResponse divisionDtoResponse =
                DivisionDtoResponse.builder()
                        .divisionId(1L)
                        .directorId(1L)
                        .name("Test")
                        .originalName("Test")
                        .parentDivisionId(1L)
                        .build();

        Person director = Person.builder().build();
        Division parentDivision = new Division();
        parentDivision.setId(1L);
        parentDivision.setDirector(director);
        parentDivision.setOriginalName("Test");
        parentDivision.setName("Test");

        Division subdivision = new Division();
        subdivision.setId(2L);
        subdivision.setDirector(director);
        subdivision.setOriginalName("Test");
        subdivision.setName("Test");

        Mockito.when(divisionRepositoryMock.findById(any(Long.class))).thenReturn(java.util.Optional.of((parentDivision)));
        Mockito.when(personRepositoryMock.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(director));
        Mockito.when(divisionMapperMock.toEntity(any(DivisionDtoRequest.class))).thenReturn(subdivision);
        Mockito.when(divisionMapperMock.toResponse(any(Division.class))).thenReturn(divisionDtoResponse);

        divisionService.save(divisionToSave);

        Mockito.verify(divisionRepositoryMock).save(any(Division.class));
    }
}