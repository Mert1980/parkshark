package com.switchfully.parkshark.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ParkingLotAllocationNotActive extends ResponseStatusException {
    public ParkingLotAllocationNotActive(long id) {
        super(HttpStatus.BAD_REQUEST, "Parking lot allocation with id: " + id + "is not active");
    }

}
