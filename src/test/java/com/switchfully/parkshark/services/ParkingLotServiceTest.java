package com.switchfully.parkshark.services;

import com.switchfully.parkshark.repositories.DivisionRepository;
import com.switchfully.parkshark.repositories.ParkingLotRepository;
import com.switchfully.parkshark.repositories.PersonRepository;
import com.switchfully.parkshark.services.mapper.AddressDtoRequest;
import com.switchfully.parkshark.services.mapper.DivisionMapper;
import com.switchfully.parkshark.services.mapper.ParkingLotMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ParkingLotServiceTest {

    private DivisionService divisionServiceMock;
    private PersonService personServiceMock;
    private ParkingLotRepository parkingLotRepositoryMock;
    private ParkingLotMapper parkingLotMapperMock;
    private ParkingLotService parkingLotService;

    @BeforeEach
    void setUp() {
        divisionServiceMock = Mockito.mock(DivisionService.class);
        personServiceMock = Mockito.mock(PersonService.class);
        parkingLotRepositoryMock = Mockito.mock(ParkingLotRepository.class);
        parkingLotMapperMock = Mockito.mock(ParkingLotMapper.class);
        parkingLotService = new ParkingLotService(parkingLotRepositoryMock, parkingLotMapperMock, personServiceMock, divisionServiceMock);
    }

    @Test
    void whenAddingDivision_thenDivisionRepositorySaveMethodIsCalled() {

}