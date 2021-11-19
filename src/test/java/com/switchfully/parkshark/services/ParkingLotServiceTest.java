package com.switchfully.parkshark.services;

import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.dto.ParkingLotDtoRequest;
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

  /*  @Test
    void whenAddingParkingLot_thenParkingLotRepositorySaveMethodIsCalled() {

        ParkingLotDtoRequest parkingLotDtoRequest = ParkingLotDtoRequest.builder()
                .name("Test")
                .parkingLotCategory("aboveground")
                .divisionId(5L)
                .pricePerHour(5.0)
                .contactId(12L)
                .capacity(500)
                .addressDtoRequest(AddressDtoRequest.builder()
                        .streetName("Cool Street")
                        .streetNumber("5")
                        .postalCode("5641")
                        .city("CoolVille")
                        .build())
                .build();

        ParkingLot parkingLot = ParkingLot.builder().build();

        Mockito.when(parkingLotRepositoryMock.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(parkingLot));

        parkingLotService.save(parkingLotDtoRequest);
        Mockito.verify(parkingLotRepositoryMock).save(any(ParkingLot.class));
    }*/
}