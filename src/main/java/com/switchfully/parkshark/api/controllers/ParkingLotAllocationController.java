package com.switchfully.parkshark.api.controllers;

import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoResponse;
import com.switchfully.parkshark.dto.ParkingLotAllocationStopDtoRequest;
import com.switchfully.parkshark.services.ParkingLotAllocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking-lot-allocations")
public class ParkingLotAllocationController {

  private final ParkingLotAllocationService parkingLotAllocationService;

  @Autowired
  public ParkingLotAllocationController(
      ParkingLotAllocationService parkingLotAllocationService) {
    this.parkingLotAllocationService = parkingLotAllocationService;
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public ParkingLotAllocationDtoResponse createParkingLotAllocation(@RequestBody ParkingLotAllocationDtoRequest request) {
    System.out.println(request.toString());
    return parkingLotAllocationService.startParking(request);
  }

  @PutMapping(consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ParkingLotAllocationDtoResponse stopParkingLotAllocation(@RequestBody ParkingLotAllocationStopDtoRequest request) {
    return parkingLotAllocationService.stopParking(request);
  }

  @GetMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<ParkingLotAllocationDtoResponse> getAllParkingLotAllocations() {
    return parkingLotAllocationService.getAllParkingLotAllocations();
  }
}
