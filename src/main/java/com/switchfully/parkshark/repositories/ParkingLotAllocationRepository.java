package com.switchfully.parkshark.repositories;

import com.switchfully.parkshark.domain.ParkingLotAllocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParkingLotAllocationRepository extends JpaRepository<ParkingLotAllocation, Long>, JpaSpecificationExecutor<ParkingLotAllocation> {

    Page<ParkingLotAllocation> findAll(Specification<ParkingLotAllocation> specification, Pageable pageable);

}
