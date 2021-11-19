package com.switchfully.parkshark.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ParkingLotAllocationNotFound extends ResponseStatusException {

  public ParkingLotAllocationNotFound(long id) {
    super(HttpStatus.NOT_FOUND, "Parking lot allocation with id " + id + " not found");
  }
}
