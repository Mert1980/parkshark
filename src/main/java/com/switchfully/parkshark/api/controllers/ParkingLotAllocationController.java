package com.switchfully.parkshark.api.controllers;

import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/parking-lot-allocations")
public class ParkingLotAllocationController {

  @PostMapping
  public ParkingLotAllocationDtoResponse createParkingLotAllocation(ParkingLotAllocationDtoRequest request) {
    return null;
  }

  @PutMapping
  public ParkingLotAllocationDtoResponse stopParkingLotAllocation(ParkingLotAllocationDtoRequest request) {
    return null;
  }

  @GetMapping
  public List<ParkingLotAllocationDtoResponse> getParkingLotAllocation() {
    return null;
  }
}
