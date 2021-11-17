package com.switchfully.parkshark.services;

import com.switchfully.parkshark.api.dto.CreateDivisionRequest;
import com.switchfully.parkshark.api.dto.CreateDivisionResponse;
import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.repositories.DivisionRepository;
import com.switchfully.parkshark.services.mapper.DivisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisionService {

    @Autowired
    private final DivisionRepository divisionRepository;
    private final DivisionMapper divisionMapper;

    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }

    public CreateDivisionResponse save(CreateDivisionRequest createDivisionDTO) {
        Division division = divisionMapper.toEntity(createDivisionDTO);
        return divisionMapper.toResponse(divisionRepository.save(division));
    }

}
