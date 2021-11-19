package com.switchfully.parkshark.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ParkingLotNotFoundException extends ResponseStatusException {

  public ParkingLotNotFoundException(long id) {
    super(HttpStatus.NOT_FOUND, "Parking lot with id " + id + " not found");
  }
}
