package com.switchfully.parkshark.repositories;

import com.switchfully.parkshark.domain.ParkingLotAllocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotAllocationRepository extends JpaRepository<ParkingLotAllocation, Long> {

    Page<ParkingLotAllocation> findByStopTimeNull(Pageable pageable);

    Page<ParkingLotAllocation> findByStopTimeNotNull(Pageable pageable);
}
