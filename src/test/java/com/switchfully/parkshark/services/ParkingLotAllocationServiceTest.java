package com.switchfully.parkshark.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.switchfully.parkshark.domain.*;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.repositories.ParkingLotAllocationRepository;
import com.switchfully.parkshark.services.exceptions.NonMatchedLicencePlateException;
import com.switchfully.parkshark.services.mapper.ParkingLotAllocationMapper;
import com.switchfully.parkshark.services.util.ParkingLotAllocationSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class ParkingLotAllocationServiceTest {
    private PersonService personServiceMock;
    private ParkingLotService parkingLotServiceMock;
    private ParkingLotAllocationRepository parkingLotAllocationRepositoryMock;
    private ParkingLotAllocationService parkingLotAllocationService;
    private ParkingLotAllocationMapper parkingLotAllocationMapperMock;
    private ParkingLotAllocationSpecification parkingLotAllocationSpecificationMock;

    private ParkingLotAllocationDtoRequest allocationToSave;
    private ParkingLotAllocation parkingLotAllocation;

    @BeforeEach
    void setup() {
        personServiceMock = mock(PersonService.class);
        parkingLotAllocationMapperMock = mock(ParkingLotAllocationMapper.class);
        parkingLotAllocationRepositoryMock = mock(ParkingLotAllocationRepository.class);
        parkingLotAllocationSpecificationMock = mock(ParkingLotAllocationSpecification.class);
        parkingLotServiceMock = mock(ParkingLotService.class);
        parkingLotAllocationService = new ParkingLotAllocationService(parkingLotAllocationRepositoryMock, parkingLotAllocationMapperMock, parkingLotServiceMock, personServiceMock, parkingLotAllocationSpecificationMock);

        allocationToSave = ParkingLotAllocationDtoRequest.builder()
                .parkingLotId(1L)
                .licensePlateNumber("myfancyplate")
                .personId(1L)
                .build();

        parkingLotAllocation = ParkingLotAllocation.builder()
                .id(5)
                .build();
    }


    @Test
    void whenAddingValidAllocation_thenAllocationRepositorySaveMethodIsCalled() {

        Person person = Person.builder()
                .licencePlateNumber("myfancyplate")
                .membershipLevel(MembershipLevelCategory.Gold).build();

        Mockito.when(personServiceMock.findMemberById(any(Long.class))).thenReturn(person);
        Mockito.when(parkingLotAllocationMapperMock.toEntity(any(ParkingLotAllocationDtoRequest.class))).thenReturn(parkingLotAllocation);
        Mockito.when(parkingLotServiceMock.getParkingLotById(any(Long.class))).thenReturn(ParkingLot.builder().capacity(500).build());

        parkingLotAllocationService.startParking(allocationToSave);
        Mockito.verify(parkingLotAllocationRepositoryMock).save(any(ParkingLotAllocation.class));
    }

    @Test
    void whenAddingAllocationWithPersonWithBronzeMembershipAndMatchingPlate_theNoExceptionIsThrown() {

        Person person = Person.builder()
                .licencePlateNumber("myfancyplate")
                .membershipLevel(MembershipLevelCategory.Bronze).build();

        Mockito.when(personServiceMock.findMemberById(any(Long.class))).thenReturn(person);
        Mockito.when(parkingLotAllocationMapperMock.toEntity(any(ParkingLotAllocationDtoRequest.class))).thenReturn(parkingLotAllocation);
        Mockito.when(parkingLotServiceMock.getParkingLotById(any(Long.class))).thenReturn(ParkingLot.builder().capacity(500).build());

        assertDoesNotThrow(() ->
                parkingLotAllocationService.startParking(allocationToSave));
    }

    @Test
    void whenAddingAllocationWithPersonWithBronzeMembershipAndNonMatchingPlate_thenExceptionIsThrown() {

        Person person = Person.builder()
                .licencePlateNumber("oops")
                .membershipLevel(MembershipLevelCategory.Bronze).build();

        Mockito.when(personServiceMock.findMemberById(any(Long.class))).thenReturn(person);
        Mockito.when(parkingLotAllocationMapperMock.toEntity(any(ParkingLotAllocationDtoRequest.class))).thenReturn(parkingLotAllocation);
        Mockito.when(parkingLotServiceMock.getParkingLotById(any(Long.class))).thenReturn(ParkingLot.builder().capacity(500).build());


        assertThrows(NonMatchedLicencePlateException.class, () -> parkingLotAllocationService.startParking(allocationToSave));
    }


    @Test
    void whenAddingAllocationWithPersonWithSilverMembershipAndMatchingPlate_theNoExceptionIsThrown() {

        Person person = Person.builder()
                .licencePlateNumber("myfancyplate")
                .membershipLevel(MembershipLevelCategory.Silver).build();

        Mockito.when(personServiceMock.findMemberById(any(Long.class))).thenReturn(person);
        Mockito.when(parkingLotAllocationMapperMock.toEntity(any(ParkingLotAllocationDtoRequest.class))).thenReturn(parkingLotAllocation);
        Mockito.when(parkingLotServiceMock.getParkingLotById(any(Long.class))).thenReturn(ParkingLot.builder().capacity(500).build());

        assertDoesNotThrow(() ->
                parkingLotAllocationService.startParking(allocationToSave));
    }

    @Test
    void whenAddingAllocationWithPersonWithSilverMembershipAndNonMatchingPlate_thenExceptionIsThrown() {

        Person person = Person.builder()
                .licencePlateNumber("oops")
                .membershipLevel(MembershipLevelCategory.Silver).build();

        Mockito.when(personServiceMock.findMemberById(any(Long.class))).thenReturn(person);
        Mockito.when(parkingLotAllocationMapperMock.toEntity(any(ParkingLotAllocationDtoRequest.class))).thenReturn(parkingLotAllocation);
        Mockito.when(parkingLotServiceMock.getParkingLotById(any(Long.class))).thenReturn(ParkingLot.builder().capacity(500).build());


        assertThrows(NonMatchedLicencePlateException.class, () -> parkingLotAllocationService.startParking(allocationToSave));
    }

    @Test
    void whenAddingAllocationWithPersonWithGoldMembershipAndNonMatchingPlate_thenNoExceptionIsThrown() {

        Person person = Person.builder()
                .licencePlateNumber("thiswillnotfail")
                .membershipLevel(MembershipLevelCategory.Gold).build();

        Mockito.when(personServiceMock.findMemberById(any(Long.class))).thenReturn(person);
        Mockito.when(parkingLotAllocationMapperMock.toEntity(any(ParkingLotAllocationDtoRequest.class))).thenReturn(parkingLotAllocation);
        Mockito.when(parkingLotServiceMock.getParkingLotById(any(Long.class))).thenReturn(ParkingLot.builder().capacity(500).build());

        assertDoesNotThrow(() ->
                parkingLotAllocationService.startParking(allocationToSave));
    }

}
