//package com.switchfully.parkshark.services;
//
//import static org.mockito.Mockito.mock;
//
//import com.switchfully.parkshark.repositories.ParkingLotRepository;
//import com.switchfully.parkshark.services.mapper.ParkingLotAllocationMapper;
//import com.switchfully.parkshark.services.mapper.ParkingLotMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class ParkingLotAllocationServiceTest {
//  private DivisionService divisionServiceMock;
//  private PersonService personServiceMock;
//  private ParkingLotRepository parkingLotRepositoryMock;
//  private ParkingLotService parkingLotService;
//  private ParkingLotAllocationRepository parkingLotAllocationRepositoryMock;
//  private ParkingLotAllocationService parkingLotAllocationService;
//  private ParkingLotAllocationMapper parkingLotAllocationMapperMock;
//  private PersonService personService;
//
//  @BeforeEach
//  void setup() {
//    divisionServiceMock = mock(DivisionService.class);
//    personServiceMock = mock(PersonService.class);
//    parkingLotRepositoryMock = mock(ParkingLotRepository.class);
//    parkingLotAllocationMapperMock = mock(ParkingLotAllocationMapper.class);
//    parkingLotAllocationRepositoryMock = mock(ParkingLotAllocationRepository);
//    parkingLotService = new ParkingLotService(parkingLotRepositoryMock, parkingLotMapper, PersonService personService, DivisionService divisionService, DivisionMapper divisionMapper);
//    parkingLotAllocationService = new ParkingLotAllocationService(parkingLotAllocationRepositoryMock, parkingLotAllocationMapperMock, parkingLotService, personService);
//  }
//
//
//  @Test
//  void whenAddingAllocation_thenAllocationRepositorySaveMethodIsCalled() {
        // TO DO
//    ParkingLotAllocationDtoRequest allocationToSave =

//    parkingLotAllocationService.save(allocationToSave);
//    Mockito.verify(parkingLotAllocationRepositoryMock).save(any(ParkingLotAllocation.class));
//  }
//
//}
