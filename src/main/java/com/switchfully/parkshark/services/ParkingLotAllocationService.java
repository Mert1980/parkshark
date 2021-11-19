package com.switchfully.parkshark.services;


import com.switchfully.parkshark.domain.ParkingLotAllocation;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoResponse;
import com.switchfully.parkshark.dto.ParkingLotAllocationStopDtoRequest;
import com.switchfully.parkshark.repositories.ParkingLotAllocationRepository;
import com.switchfully.parkshark.services.exceptions.NotGoldMemberException;
import com.switchfully.parkshark.services.exceptions.ParkingIsFullException;
import com.switchfully.parkshark.services.exceptions.ParkingLotAllocationNotActive;
import com.switchfully.parkshark.services.exceptions.ParkingLotAllocationNotFound;
import com.switchfully.parkshark.services.exceptions.UnauthorizedParkingAllocation;
import com.switchfully.parkshark.services.mapper.ParkingLotAllocationMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
      PersonService personService) {
    this.parkingLotAllocationRepository = parkingLotAllocationRepository;
    this.parkingLotAllocationMapper = parkingLotAllocationMapper;
    this.parkingLotService = parkingLotService;
    this.personService = personService;
  }

  public ParkingLotAllocationDtoResponse save(
      ParkingLotAllocationDtoRequest parkingLotAllocationDtoRequest) {

    assertAllocationRequestValid(parkingLotAllocationDtoRequest);

    ParkingLotAllocation parkingLotAllocation = parkingLotAllocationMapper.toEntity(
        parkingLotAllocationDtoRequest);

    parkingLotAllocation.setStartTime(LocalDateTime.now().toString());

    return parkingLotAllocationMapper.toResponse(
        parkingLotAllocationRepository.save(parkingLotAllocation));
  }

  public ParkingLotAllocationDtoResponse stopParking(ParkingLotAllocationStopDtoRequest parkingLotAllocationStopDtoRequest) {
    assertValidPersonId(parkingLotAllocationStopDtoRequest.getMemberId());
    Long parkingLotAllocationId = parkingLotAllocationStopDtoRequest.getParkingLotAllocationId();

    ParkingLotAllocation allocation = parkingLotAllocationRepository.findById(parkingLotAllocationId)
        .orElseThrow(() -> new ParkingLotAllocationNotFound(parkingLotAllocationId));

    Long memberId = allocation.getPerson().getId();

    assertRightPersonStopsAllocation(memberId, parkingLotAllocationStopDtoRequest.getMemberId());
    assertAllocationActive(parkingLotAllocationId);
    allocation.setStopTime(LocalDateTime.now().toString());
    return parkingLotAllocationMapper.toResponse(allocation);

  }

  public List<ParkingLotAllocationDtoResponse> getAllParkingLotAllocations(){
    return parkingLotAllocationRepository.findAll().stream()
        .map(parkingLotAllocationMapper::toResponse)
        .collect(Collectors.toList());
  }

  private void assertAllocationRequestValid(ParkingLotAllocationDtoRequest allocationDtoRequest) {
    assertValidPersonId(allocationDtoRequest.getPersonId());
    assertValidParkingLotId(allocationDtoRequest.getParkingLotId());
    assertParkingLotIsNotFull(allocationDtoRequest.getParkingLotId());
    String personLicensePlateNumber = getLicencePlateNumberFromMember(
        allocationDtoRequest.getPersonId());
    assertLicensePlateFromMemberIsTheirs(allocationDtoRequest.getLicensePlateNumber(),
        personLicensePlateNumber);
  }

  private String getLicencePlateNumberFromMember(Long memberId) {
    return personService.getMemberById(memberId).getLicencePlateNumber();
  }

  private void assertAllocationActive(Long id) {
    ParkingLotAllocation allocation = parkingLotAllocationRepository.findById(id)
        .orElseThrow(() -> new ParkingLotAllocationNotFound(id));
    if (allocation.getStopTime() != null) {
      throw new ParkingLotAllocationNotActive(id);
    }
  }

  private void assertValidPersonId(Long id) {
    System.out.println("2: " + id);
    personService.assertValidPersonId(id);
  }

  private void assertRightPersonStopsAllocation(Long idExpected, Long idToAssert) {
    if (!Objects.equals(idExpected, idToAssert)) {
      throw new UnauthorizedParkingAllocation();
    }
  }

  private void assertValidParkingLotId(long id) {
    parkingLotService.assertValidParkingLotId(id);
  }

  private void assertLicensePlateFromMemberIsTheirs(String licensePlate1, String licensePlate2) {
    if (!licensePlate1.equals(licensePlate2)) {
      throw new NotGoldMemberException();
    }
  }

  public Long getNumberOfAllocationForParkingLot(Long parkingLotId) {
    return parkingLotAllocationRepository.findAll().stream()
        .filter(parkingLotAllocation -> parkingLotAllocation.getParkingLotId() == parkingLotId)
        .count();
  }

  private void assertParkingLotIsNotFull(Long parkingLotId) {
    if (getNumberOfAllocationForParkingLot(parkingLotId) >= parkingLotService.getParkingLotById(
        parkingLotId).getCapacity()) {
      throw new ParkingIsFullException(parkingLotId);
    }
  }
}
