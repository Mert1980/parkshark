package com.switchfully.parkshark.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotGoldMemberException extends ResponseStatusException {

  public NotGoldMemberException() {
    super(HttpStatus.BAD_REQUEST,
        "You are not a Gold status member, please upgrade or use the license plate linked to your account");
  }
}
