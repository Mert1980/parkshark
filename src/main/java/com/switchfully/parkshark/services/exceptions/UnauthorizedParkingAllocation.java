package com.switchfully.parkshark.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedParkingAllocation extends ResponseStatusException {

  public UnauthorizedParkingAllocation() {
    super(HttpStatus.UNAUTHORIZED, "You are not authorized to allocate parking for this id");
  }
}
