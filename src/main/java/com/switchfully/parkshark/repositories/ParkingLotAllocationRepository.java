package com.switchfully.parkshark.repositories;

import com.switchfully.parkshark.domain.ParkingLotAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotAllocationRepository extends JpaRepository<ParkingLotAllocation, Long> {
}
