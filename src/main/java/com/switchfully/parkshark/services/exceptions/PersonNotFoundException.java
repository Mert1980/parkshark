package com.switchfully.parkshark.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PersonNotFoundException extends ResponseStatusException {

  public PersonNotFoundException(long id) {
    super(HttpStatus.NOT_FOUND, "Person with id " + id + " not found");
  }
  public PersonNotFoundException(){
    super(HttpStatus.NOT_FOUND);
  }
}
