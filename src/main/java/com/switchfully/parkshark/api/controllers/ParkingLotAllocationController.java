package com.switchfully.parkshark.api.controllers;

import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoResponse;
import com.switchfully.parkshark.dto.ParkingLotAllocationStopDtoRequest;
import com.switchfully.parkshark.services.ParkingLotAllocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
//Should be ordered by start time ascending
  @GetMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<ParkingLotAllocationDtoResponse> getAllParkingLotAllocations(
      @RequestParam(value="orderDirection", defaultValue="ASC") String orderDirection,
      @RequestParam(value="filter", required = false, defaultValue = "showAll") String filter,
      @RequestParam(value="page", defaultValue="0") int page,
      @RequestParam(value="pageSize", defaultValue="10") int pageSize) {

    return parkingLotAllocationService.getAllParkingLotAllocations(orderDirection, filter, page, pageSize);
  }

  //limit the resultSet to a number

  //filter between all allocations
  // filter between all active allocations
  // filter beteween all stopped allocations
  //ordering can be set to descending or ascending

}
