package com.switchfully.parkshark.services;

import com.switchfully.parkshark.domain.ParkingLot;
import com.switchfully.parkshark.dto.ParkingLotDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotDtoResponse;
import com.switchfully.parkshark.dto.ParkingLotDtoResponseForGetAll;
import com.switchfully.parkshark.repositories.ParkingLotRepository;
import com.switchfully.parkshark.services.exceptions.ParkingLotNotFoundException;
import com.switchfully.parkshark.services.mapper.ParkingLotMapper;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;
    private final PersonService personService;
    private final ParkingLotMapper parkingLotMapper;

    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingLotMapper parkingLotMapper, PersonService personService) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotMapper = parkingLotMapper;
        this.personService = personService;
    }

    @Cascade(CascadeType.PERSIST)
    public ParkingLotDtoResponse save(ParkingLotDtoRequest parkingLotDtoRequest) {
        personService.assertValidPersonId(parkingLotDtoRequest.getContactId());

        ParkingLot parkingLot = parkingLotMapper.toEntity(parkingLotDtoRequest);
        parkingLot.setContactPerson(personService.findMemberById(parkingLotDtoRequest.getContactId()));

        return parkingLotMapper.toResponse(parkingLotRepository.save(parkingLot));
    }

    public List<ParkingLotDtoResponseForGetAll> findAll() {

        return parkingLotRepository.findAll()
                .stream()
                .map(parkingLot -> parkingLotMapper.toResponse(parkingLot, parkingLot.getContactPerson()))
                .collect(Collectors.toList());
    }

    public ParkingLot getParkingLotById(Long parkingLotId) {
        assertValidParkingLotId(parkingLotId);

        return parkingLotRepository.getById(parkingLotId);
    }

    public void assertValidParkingLotId(Long parkingLotId) {
        if (parkingLotRepository.findById(parkingLotId).isEmpty()) {
            throw new ParkingLotNotFoundException(parkingLotId);
        }
    }


}
