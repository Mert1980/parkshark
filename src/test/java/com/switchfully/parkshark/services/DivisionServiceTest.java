package com.switchfully.parkshark.services;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import com.switchfully.parkshark.repositories.DivisionRepository;
import com.switchfully.parkshark.services.mapper.DivisionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DivisionServiceTest {


    private DivisionService divisionService;
    private DivisionRepository divisionRepositoryMock;
    private DivisionMapper divisionMapperMock;

    @BeforeEach
    void setUp() {
        divisionRepositoryMock = Mockito.mock(DivisionRepository.class);
        divisionMapperMock = Mockito.mock(DivisionMapper.class);
        divisionService = new DivisionService(divisionRepositoryMock, divisionMapperMock);
    }

    @Test
    void whenAddingDivision_thenDivisionRepositorySaveMethodIsCalled() {
        DivisionDtoRequest divisionToSave = DivisionDtoRequest.builder()
                .name("test")
                .originalName("test")
                .directorId(1L)
                .build();

        DivisionDtoResponse divisionDtoResponse = DivisionDtoResponse.builder()
                .divisionId(1L)
                .directorId(1L)
                .name("Test")
                .originalName("Test")
                .build();

        divisionService.save(divisionToSave);
        Division division = divisionMapperMock.toEntity(divisionToSave);

        Mockito.when(divisionMapperMock.toResponse(division)).thenReturn(divisionDtoResponse);


        Mockito.verify(divisionRepositoryMock).save(division);
    }
}