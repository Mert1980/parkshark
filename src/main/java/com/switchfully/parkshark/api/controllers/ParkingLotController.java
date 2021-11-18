package com.switchfully.parkshark.api.controllers;

import com.switchfully.parkshark.dto.ParkingLotDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotDtoResponse;
import com.switchfully.parkshark.services.ParkingLotService;
import com.switchfully.parkshark.services.mapper.ParkingLotMapper;
import com.switchsecure.SecurityGuard;
import com.switchsecure.SecurityGuard.ApiUserRole;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingLotController {
    private final ParkingLotMapper parkingLotMapper;
    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotMapper parkingLotMapper, ParkingLotService parkingLotService) {
        this.parkingLotMapper = parkingLotMapper;
        this.parkingLotService = parkingLotService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityGuard(ApiUserRole.ADMIN)
    public ParkingLotDtoResponse addParkingLot(@RequestBody ParkingLotDtoRequest request) {
        return parkingLotService.save(request);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(ApiUserRole.ADMIN)
    public List<ParkingLotDtoResponse> getAllParkingLots() {
        return parkingLotService.findAll();
    }
}
