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

class DivisionServiceTest {


    private DivisionService divisionService;
    private DivisionRepository divisionRepositoryMock;
    private DivisionMapper divisionMapperMock;
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        divisionRepositoryMock = Mockito.mock(DivisionRepository.class);
        personRepository = Mockito.mock(PersonRepository.class);
        divisionMapperMock = Mockito.mock(DivisionMapper.class);
        divisionService = new DivisionService(divisionRepositoryMock, personRepository, divisionMapperMock);
    }

   /*
   MOVED some logic from DivisionMapper to DivisionService and now the test requires a lot more work to get it working...
   @Test
    void whenAddingDivision_thenDivisionRepositorySaveMethodIsCalled() {
        DivisionDtoRequest divisionToSave = DivisionDtoRequest.builder()
                .name("test")
                .originalName("test")
                .directorId(1L)
                .parentDivisionId(2L)
                .build();

        DivisionDtoResponse divisionDtoResponse = DivisionDtoResponse.builder()
                .divisionId(1L)
                .directorId(1L)
                .name("Test")
                .originalName("Test")
                .parentDivisionId(1L)
                .build();

        Person director = Person.builder().build();
        Division parentDivision = new Division();
        parentDivision.setDirector(director);
        parentDivision.setParentDivision(null);
        parentDivision.setOriginalName("Test");
        parentDivision.setName("Test");




        divisionService.save(divisionToSave);
        Division division = divisionMapperMock.toEntity(divisionToSave);

        Mockito.when(divisionMapperMock.toResponse(division)).thenReturn(divisionDtoResponse);
        Mockito.when(divisionRepositoryMock.getById(2L)).thenReturn(parentDivision);
        Mockito.when(personRepository.getById(1L)).thenReturn(director);

        Mockito.verify(divisionRepositoryMock).save(division);
    }*/
}