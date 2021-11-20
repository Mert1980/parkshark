package com.switchfully.parkshark.api.controllers;

import com.switchfully.parkshark.dto.ParkingLotDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotDtoResponse;
import com.switchfully.parkshark.dto.ParkingLotDtoResponseForGetAll;
import com.switchfully.parkshark.services.ParkingLotService;
import com.switchsecure.SecurityGuard;
import com.switchsecure.SecurityGuard.ApiUserRole;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {

  private final ParkingLotService parkingLotService;
  private final Logger logger = LoggerFactory.getLogger(ParkingLotService.class);

  public ParkingLotController(ParkingLotService parkingLotService) {
    this.parkingLotService = parkingLotService;
  }

  //TODO: not sure why this says: Only admin can access this REST endpoint.... otherwise does work?
  @GetMapping(path = "/{id}", produces = "application/json")
  @SecurityGuard(ApiUserRole.ADMIN)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ParkingLotDtoResponse GetParkingLotById(@PathVariable(name = "id", required = false) long id){
    logger.info("Retrieving parking lot with id " + id);
    return parkingLotService.findById(id);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  @SecurityGuard(ApiUserRole.ADMIN)
  public ParkingLotDtoResponse addParkingLot(@Valid @RequestBody ParkingLotDtoRequest request) {
    logger.info("Creating a parkingLot");
    return parkingLotService.save(request);
  }

  @GetMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @SecurityGuard(ApiUserRole.ADMIN)
  public List<ParkingLotDtoResponseForGetAll> getAllParkingLots() {
    logger.info("Retrieved all parking lots");
    return parkingLotService.findAll();
  }
}
