package com.switchfully.parkshark.services;


import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoResponse;
import com.switchfully.parkshark.repositories.ParkingLotAllocationRepository;
import com.switchfully.parkshark.services.mapper.ParkingLotAllocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParkingLotAllocationService {

  private final ParkingLotAllocationRepository parkingLotAllocationRepository;
  private final ParkingLotAllocationMapper parkingLotAllocationMapper;
  private final ParkingLotService parkingLotService;
  private final PersonService personService;

  @Autowired
  public ParkingLotAllocationService(ParkingLotAllocationRepository parkingLotAllocationRepository,
      ParkingLotAllocationMapper parkingLotAllocationMapper,
      ParkingLotService parkingLotService,
      PersonService personService){
    this.parkingLotAllocationRepository = parkingLotAllocationRepository;
    this.parkingLotAllocationMapper = parkingLotAllocationMapper;
    this.parkingLotService = parkingLotService;
    this.personService = personService;
  }

  public ParkingLotAllocationDtoResponse save(ParkingLotAllocationDtoRequest parkingLotAllocationDtoRequest){
    return parkingLotAllocationMapper.toResponse(parkingLotAllocationRepository.save(parkingLotAllocationMapper.toEntity(parkingLotAllocationDtoRequest))) ;
  }

  private void isAllocationRequestValid(ParkingLotAllocationDtoRequest allocationDtoRequest) {
    assertValidPersonId(allocationDtoRequest.getPersonId());
    assertValidParkingLotId(allocationDtoRequest.getParkingLotId());
    String  personLicensePlateNumber = getLicencePlateNumberFromMember(allocationDtoRequest.getPersonId());
    assertLicensePlateFromMemberIsTheirs(allocationDtoRequest.getLicensePlateNumber(),personLicensePlateNumber);

  }

  private String getLicencePlateNumberFromMember(long memberId) {
    return personService.getMemberById(memberId).getLicencePlateNumber();
  }

  private void assertValidPersonId(long id){
    personService.assertValidPersonId(id);
  }
  private void assertValidParkingLotId(long id){
    parkingLotService.assertValidParkingLotId(id);
  }
  private boolean assertLicensePlateFromMemberIsTheirs(String licensePlate1, String licensePlate2 ){
    return licensePlate1.equals(licensePlate2);
  }
}
