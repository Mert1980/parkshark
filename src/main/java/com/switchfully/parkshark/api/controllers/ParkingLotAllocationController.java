package com.switchfully.parkshark.api.controllers;

import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoResponse;
import com.switchfully.parkshark.dto.ParkingLotAllocationStopDtoRequest;
import com.switchfully.parkshark.services.ParkingLotAllocationService;
import com.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
  private final Logger logger = LoggerFactory.getLogger(ParkingLotAllocationService.class);

  @Autowired
  public ParkingLotAllocationController(
      ParkingLotAllocationService parkingLotAllocationService) {
    this.parkingLotAllocationService = parkingLotAllocationService;
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public ParkingLotAllocationDtoResponse createParkingLotAllocation(@RequestBody ParkingLotAllocationDtoRequest request) {
    logger.info("Creating parking lot allocation");
    return parkingLotAllocationService.startParking(request);
  }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ParkingLotAllocationDtoResponse stopParkingLotAllocation(@RequestBody ParkingLotAllocationStopDtoRequest request) {
        logger.info("Stopping parking lot allocation with id " + request.getParkingLotAllocationId());
        return parkingLotAllocationService.stopParking(request);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    @ResponseBody
    public Page<ParkingLotAllocationDtoResponse> getAllParkingLotAllocations(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                                                              @RequestParam(defaultValue = "startTime") String sortBy,
                                                                              @RequestParam(defaultValue = "ASC") String sortDirection,
                                                                              @RequestParam(defaultValue = "All") String allocationStatus){
        logger.info("Retrieving all parking lot allocations based on parameter criteria");
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.valueOf(sortDirection), sortBy));
        return parkingLotAllocationService.getAllParkingLotAllocations(paging, allocationStatus);
    }
}
