package com.switchfully.parkshark.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ParkingIsFullException extends ResponseStatusException {

  public ParkingIsFullException(Long parkingLotId) {
    super(HttpStatus.BAD_REQUEST, "Parking lot with id " + parkingLotId + " is full. Please seek another parking lot");
  }
}
