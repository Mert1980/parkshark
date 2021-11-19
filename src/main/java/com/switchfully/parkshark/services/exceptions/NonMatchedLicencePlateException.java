package com.switchfully.parkshark.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NonMatchedLicencePlateException extends ResponseStatusException {

  public NonMatchedLicencePlateException() {
    super(HttpStatus.NOT_FOUND, "The  license plate does not match to your account");
  }
}
