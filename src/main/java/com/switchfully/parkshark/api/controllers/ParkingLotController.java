package com.switchfully.parkshark.api.controllers;

import com.switchfully.parkshark.dto.ParkingLotDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotDtoResponse;
import com.switchfully.parkshark.services.mapper.ParkingLotMapper;
import com.switchsecure.SecurityGuard;
import com.switchsecure.SecurityGuard.ApiUserRole;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {
  private final ParkingLotMapper parkingLotMapper;

  public ParkingLotController(
      ParkingLotMapper parkingLotMapper) {
    this.parkingLotMapper = parkingLotMapper;
  }


  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  @SecurityGuard(ApiUserRole.ADMIN)
  public ParkingLotDtoResponse addParkingLot(@RequestBody ParkingLotDtoRequest request) {
    return null;
  }

  @GetMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @SecurityGuard(ApiUserRole.ADMIN)
  public List<ParkingLotDtoResponse> getAllParkingLots() {
    return null;
  }
}
