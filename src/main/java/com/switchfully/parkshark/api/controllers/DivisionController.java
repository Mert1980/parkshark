package com.switchfully.parkshark.api.controllers;

import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import com.switchfully.parkshark.services.DivisionService;
import com.switchfully.parkshark.services.mapper.DivisionMapper;
import com.switchsecure.SecurityGuard;
import com.switchsecure.SecurityGuard.ApiUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public DivisionDtoResponse saveDivision(@RequestBody DivisionDtoRequest divisionDtoRequest) {
        logger.info("Register new division " + divisionDtoRequest.getName());
        return divisionService.save(divisionDtoRequest);
    }


}
