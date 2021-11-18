package com.switchfully.parkshark.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DivisionNotFoundException extends ResponseStatusException {

  public DivisionNotFoundException(long id) {
    super(HttpStatus.NOT_FOUND, "Division with id " + id + " not found");
  }
}
