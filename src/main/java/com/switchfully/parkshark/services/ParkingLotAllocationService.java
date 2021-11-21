package com.switchfully.parkshark.services;


import com.switchfully.parkshark.domain.MembershipLevelCategory;
import com.switchfully.parkshark.domain.ParkingLotAllocation;
import com.switchfully.parkshark.domain.Person;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoResponse;
import com.switchfully.parkshark.dto.ParkingLotAllocationStopDtoRequest;
import com.switchfully.parkshark.repositories.ParkingLotAllocationRepository;
import com.switchfully.parkshark.services.exceptions.NonMatchedLicencePlateException;
import com.switchfully.parkshark.services.exceptions.ParkingIsFullException;
import com.switchfully.parkshark.services.exceptions.ParkingLotAllocationNotActive;
import com.switchfully.parkshark.services.exceptions.ParkingLotAllocationNotFound;
import com.switchfully.parkshark.services.exceptions.UnauthorizedParkingAllocation;
import com.switchfully.parkshark.services.mapper.ParkingLotAllocationMapper;

import java.time.LocalDateTime;
import java.util.Objects;

import com.switchfully.parkshark.services.util.ParkingLotAllocationSpecification;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParkingLotAllocationService {

    private final ParkingLotAllocationRepository parkingLotAllocationRepository;
    private final ParkingLotAllocationMapper parkingLotAllocationMapper;
    private final ParkingLotService parkingLotService;
    private final PersonService personService;
    private final ParkingLotAllocationSpecification parkingLotAllocationSpecification;

    @Autowired
    public ParkingLotAllocationService(ParkingLotAllocationRepository parkingLotAllocationRepository,
                                       ParkingLotAllocationMapper parkingLotAllocationMapper,
                                       ParkingLotService parkingLotService,
                                       PersonService personService, ParkingLotAllocationSpecification parkingLotAllocationSpecification) {
        this.parkingLotAllocationRepository = parkingLotAllocationRepository;
        this.parkingLotAllocationMapper = parkingLotAllocationMapper;
        this.parkingLotService = parkingLotService;
        this.personService = personService;
        this.parkingLotAllocationSpecification = parkingLotAllocationSpecification;
    }

    @Cascade(CascadeType.PERSIST)
    public ParkingLotAllocationDtoResponse startParking(
            ParkingLotAllocationDtoRequest parkingLotAllocationDtoRequest) {

        Person person = personService.findMemberById(parkingLotAllocationDtoRequest.getPersonId());

        assertAllocationRequestValid(parkingLotAllocationDtoRequest, person);

        ParkingLotAllocation parkingLotAllocation = parkingLotAllocationMapper.toEntity(
                parkingLotAllocationDtoRequest);

        parkingLotAllocation.setStartTime(LocalDateTime.now().toString());
        parkingLotAllocation.setPerson(person);

        return parkingLotAllocationMapper.toResponse(parkingLotAllocationRepository.save(parkingLotAllocation));
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

    public Page<ParkingLotAllocationDtoResponse> getAllParkingLotAllocations(Pageable paging, String allocationStatus, String memberId, String parkingLotId) {

        Specification<ParkingLotAllocation> queryFilter = parkingLotAllocationSpecification.getParkingLotAllocations(allocationStatus, memberId, parkingLotId);

        return parkingLotAllocationRepository.findAll(queryFilter, paging).map(parkingLotAllocationMapper::toResponse);
    }

    private void assertAllocationRequestValid(ParkingLotAllocationDtoRequest allocationDtoRequest, Person person) {
        assertValidPersonId(allocationDtoRequest.getPersonId());
        assertValidParkingLotId(allocationDtoRequest.getParkingLotId());
        assertParkingLotIsNotFull(allocationDtoRequest.getParkingLotId());

        assertMemberCanEnterWithLicensePlate(person.getMembershipLevel(), allocationDtoRequest.getLicensePlateNumber(), person.getLicencePlateNumber());
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

    public Long getNumberOfAllocationForParkingLot(Long parkingLotId) {
        return parkingLotAllocationRepository.findAll().stream()
                .filter(parkingLotAllocation -> parkingLotAllocation.getParkingLotId() == parkingLotId)
                .filter(parkingLotAllocation -> parkingLotAllocation.getStopTime() == null)
                .count();
    }

    private void assertParkingLotIsNotFull(Long parkingLotId) {
        if (getNumberOfAllocationForParkingLot(parkingLotId) >= parkingLotService.getParkingLotById(
                parkingLotId).getCapacity()) {
            throw new ParkingIsFullException(parkingLotId);
        }
    }
}
