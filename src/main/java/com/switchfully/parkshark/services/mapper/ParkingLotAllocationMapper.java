package com.switchfully.parkshark.services.mapper;

import com.switchfully.parkshark.domain.ParkingLotAllocation;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoRequest;
import com.switchfully.parkshark.dto.ParkingLotAllocationDtoResponse;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@Component
public class ParkingLotAllocationMapper {

  public ParkingLotAllocation toEntity(ParkingLotAllocationDtoRequest request) {
    return ParkingLotAllocation.builder()
        .licensePlateNumber(request.getLicensePlateNumber())
        .parkingLotId(request.getParkingLotId())
        .build();
  }

  public ParkingLotAllocationDtoResponse toResponse(ParkingLotAllocation entity) {
    return ParkingLotAllocationDtoResponse.builder()
        .parkingLotAllocationId(entity.getId())
        .personId(entity.getPerson().getId())
        .licensePlateNumber(entity.getLicensePlateNumber())
        .parkingLotId(entity.getParkingLotId())
        .startTime(entity.getStartTime())
        .stopTime(entity.getStopTime())
            .duration(calculateDuration(entity))
        .build();
  }

  private String calculateDuration(ParkingLotAllocation entity) {

    DateTimeFormatter dateTimeFormatter =
            new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE)
                    .appendLiteral(' ')
                    .appendPattern("HH:mm:ss")
                    .appendFraction(ChronoField.NANO_OF_SECOND, 1, 9, true)
                    .toFormatter();

    LocalDateTime startTime = LocalDateTime.parse(entity.getStartTime().replace("T", " "), dateTimeFormatter);

    LocalDateTime stopTime = LocalDateTime.now();
    if(entity.getStopTime() != null){
      stopTime = LocalDateTime.parse(entity.getStopTime().replace("T", " "), dateTimeFormatter);
    }

    Duration duration = Duration.between(startTime, stopTime);

    long days = duration.toDays() % 32;
    long hours = duration.toHours() % 24 + days * 24;
    long minutes = duration.toMinutes() % 60;
    long seconds = duration.getSeconds() % 60;

    return ""+ hours + ":" + minutes + ":" + seconds;
  }

}