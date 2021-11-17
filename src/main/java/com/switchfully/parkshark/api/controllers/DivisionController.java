package com.switchfully.parkshark.api.controllers;

import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import com.switchfully.parkshark.services.DivisionService;
import com.switchsecure.SecurityGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/divisions")
public class DivisionController {

    private final DivisionService divisionService;
    private final Logger logger = LoggerFactory.getLogger(DivisionController.class);

    @Autowired
    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public DivisionDtoResponse saveDivision(@RequestBody DivisionDtoRequest createDivisionRequest) {
        logger.info("Register new division " + createDivisionRequest.getName());
        return divisionService.save(createDivisionRequest);
    }
}
