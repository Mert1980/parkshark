package com.switchfully.parkshark.services;

import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.repositories.DivisionRepository;
import com.switchfully.parkshark.services.mapper.DivisionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DivisionServiceTest {


    private final DivisionService divisionService;
    private DivisionRepository divisionRepositoryMock;
    private final DivisionMapper divisionMapper;

    @Autowired
    DivisionServiceTest(DivisionService divisionService, DivisionMapper divisionMapper) {
        this.divisionService = divisionService;
        this.divisionMapper = divisionMapper;
    }


    @BeforeEach
    void setUp() {
        divisionRepositoryMock = Mockito.mock(DivisionRepository.class);
    }

    @Test
    void whenAddingDivision_thenDivisionRepositorySaveMethodIsCalled() {
        DivisionDtoRequest divisionToSave = DivisionDtoRequest.builder()
                .name("test")
                .originalName("test")
                .directorId(Person.builder().id(1L).build())
                .build();

        Division division = divisionMapper.toEntity(divisionToSave);

        divisionService.save(divisionToSave);

        Mockito.verify(divisionRepositoryMock).save(division);
    }
}