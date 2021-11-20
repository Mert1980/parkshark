package com.switchfully.parkshark.services;


import com.switchfully.parkshark.domain.MembershipLevelCategory;
import com.switchfully.parkshark.domain.ParkingLotAllocation;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoResponse;
import com.switchfully.parkshark.dto.ParkingLotAllocationStopDtoRequest;
import com.switchfully.parkshark.repositories.ParkingLotAllocationRepository;
import com.switchfully.parkshark.services.exceptions.NonMatchedLicencePlateException;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Cascade(CascadeType.PERSIST)
    public ParkingLotAllocationDtoResponse startParking(
            ParkingLotAllocationDtoRequest parkingLotAllocationDtoRequest) {

        System.out.println(parkingLotAllocationDtoRequest.toString());
        Person person = personService.findMemberById(parkingLotAllocationDtoRequest.getPersonId());

        assertAllocationRequestValid(parkingLotAllocationDtoRequest, person);

        ParkingLotAllocation parkingLotAllocation = parkingLotAllocationMapper.toEntity(
                parkingLotAllocationDtoRequest);

        parkingLotAllocation.setStartTime(LocalDateTime.now().toString());
        parkingLotAllocation.setPerson(person);

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

    public Page<ParkingLotAllocationDtoResponse> getAllParkingLotAllocations(Pageable paging, String allocationStatus) {

        switch (allocationStatus) {
            case "Stopped": {
                return parkingLotAllocationRepository.findByStopTimeNotNull(paging).map(parkingLotAllocationMapper::toResponse);
            }
            case "Active": {
                return parkingLotAllocationRepository.findByStopTimeNull(paging).map(parkingLotAllocationMapper::toResponse);
            }
            default:
                return parkingLotAllocationRepository.findAll(paging).map(parkingLotAllocationMapper::toResponse);
        }

    }

    private void assertAllocationRequestValid(ParkingLotAllocationDtoRequest allocationDtoRequest, Person person) {
        assertValidPersonId(allocationDtoRequest.getPersonId());
        assertValidParkingLotId(allocationDtoRequest.getParkingLotId());
        assertParkingLotIsNotFull(allocationDtoRequest.getParkingLotId());

        assertMemberCanEnterWithLicensePlate(person.getMembershipLevel(), allocationDtoRequest.getLicensePlateNumber(), person.getLicencePlateNumber());
//
//    assertLicencePlateBelongsToMember(allocationDtoRequest.getLicensePlateNumber(), person.getLicencePlateNumber());
//    assertMemberHasGoldMembershipStatus(person.getMembershipLevel());
    }

    private void assertMemberCanEnterWithLicensePlate(MembershipLevelCategory membershipLevelCategory, String licensePlateNumberFromMember, String licensePlateNumber) {
        if (!assertLicencePlateBelongsToMember(licensePlateNumberFromMember, licensePlateNumber)
                && !assertMemberHasGoldMembershipStatus(membershipLevelCategory)) {
            throw new NonMatchedLicencePlateException();
        }
    }

    private boolean assertMemberHasGoldMembershipStatus(MembershipLevelCategory membershipLevel) {
        return membershipLevel == MembershipLevelCategory.Gold;
    }

    private boolean assertLicencePlateBelongsToMember(String memberLicensePlate, String licensePlateToVerify) {
        return memberLicensePlate.equals(licensePlateToVerify);
    }

    private void assertAllocationActive(Long id) {
        ParkingLotAllocation allocation = parkingLotAllocationRepository.findById(id)
                .orElseThrow(() -> new ParkingLotAllocationNotFound(id));
        if (allocation.getStopTime() != null) {
            throw new ParkingLotAllocationNotActive(id);
        }
    }

    private void assertValidPersonId(Long id) {
        personService.assertValidPersonId(id);
    }

    private void assertRightPersonStopsAllocation(Long idExpected, Long idToAssert) {
        if (!Objects.equals(idExpected, idToAssert)) {
            throw new UnauthorizedParkingAllocation();
        }
    }

    private void assertValidParkingLotId(Long id) {
        parkingLotService.assertValidParkingLotId(id);
    }

    //TODO: Missing filter to include only spots that are active?
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
