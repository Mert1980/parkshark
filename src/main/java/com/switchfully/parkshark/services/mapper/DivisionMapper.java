package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.Division;
import com.switchfully.parkshark.dto.DivisionDtoRequest;
import com.switchfully.parkshark.dto.DivisionDtoResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {

    public Division toEntity(DivisionDtoRequest request) {
        Division entity = new Division();
        entity.setName(request.getName());
        entity.setOriginalName(request.getOriginalName());
        return entity;
    }


    public DivisionDtoResponse toResponse(Division entity) {
        Long parentDivisionId = null;

        if (entity.getParentDivision() != null) {
            parentDivisionId = entity.getParentDivision().getId();
        }

        return DivisionDtoResponse.builder()
                .divisionId(entity.getId())
                .name(entity.getName())
                .originalName(entity.getOriginalName())
                .directorId(entity.getDirector().getId())
                .parentDivisionId(parentDivisionId)
                .build();
    }

    public List<DivisionDtoResponse> toResponse(List<Division> entities) {
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
