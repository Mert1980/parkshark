package com.switchfully.parkshark.api.controllers;

import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import com.switchfully.parkshark.services.DivisionService;
import com.switchfully.parkshark.services.mapper.DivisionMapper;
import com.switchsecure.SecurityGuard;
import com.switchsecure.SecurityGuard.ApiUserRole;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/divisions")
public class DivisionController {

  private final DivisionService divisionService;
  private final DivisionMapper divisionMapper;
  private final Logger logger = LoggerFactory.getLogger(DivisionController.class);

  @Autowired
  public DivisionController(DivisionService divisionService, DivisionMapper divisionMapper) {
    this.divisionService = divisionService;
    this.divisionMapper = divisionMapper;
  }

  @GetMapping(produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @SecurityGuard(ApiUserRole.ADMIN)
  public List<DivisionDtoResponse> getAllDivisions() {
    logger.info("Retrieved all divisions");
    return divisionService.getAllDivisions();
  }

  @GetMapping(produces = "application/json", path = "{divisionId}")
  @ResponseStatus(HttpStatus.OK)
  @SecurityGuard(ApiUserRole.ADMIN)
  public DivisionDtoResponse getDivisionById(@PathVariable("divisionId") Long divisionId) {
    logger.info("Retrieved division id " + divisionId);
    return divisionService.getDivisionById(divisionId);
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  @SecurityGuard(ApiUserRole.ADMIN)
  public DivisionDtoResponse saveDivision(@Valid @RequestBody DivisionDtoRequest divisionDtoRequest) {
    logger.info("Register new division " + divisionDtoRequest.getName());
    return divisionService.save(divisionDtoRequest);
  }


}
