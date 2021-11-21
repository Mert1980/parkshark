package com.switchfully.parkshark.services.util;

import com.switchfully.parkshark.domain.ParkingLotAllocation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public class ParkingLotAllocationSpecification {

    private List<Predicate> predicates;

    public Specification<ParkingLotAllocation> getParkingLotAllocations(String allocationStatus, String memberId, String parkingLotId) {
        return (parkingLotAllocation, query, criteriaBuilder) -> {

            predicates = new ArrayList<>();

            addParkingLotAllocationStatusToQuery(allocationStatus, parkingLotAllocation, criteriaBuilder);
            addMemberIdToQuery(memberId, parkingLotAllocation, criteriaBuilder);
            addParkingLotIdToQuery(parkingLotId, parkingLotAllocation, criteriaBuilder);

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private void addParkingLotAllocationStatusToQuery(String allocationStatus, Root<ParkingLotAllocation> parkingLotAllocation, CriteriaBuilder criteriaBuilder) {
            if (allocationStatus.equals("Stopped")) {
                predicates.add(criteriaBuilder.isNotNull(parkingLotAllocation.get("stopTime")));
            }
            if (allocationStatus.equals("Active")) {
                predicates.add(criteriaBuilder.isNull(parkingLotAllocation.get("stopTime")));
            }
    }

    private void addMemberIdToQuery(String memberId, Root<ParkingLotAllocation> parkingLotAllocation, CriteriaBuilder criteriaBuilder) {
        if (memberId != null && !memberId.isEmpty()) {
            predicates.add(criteriaBuilder.equal(parkingLotAllocation.get("person").get("id"), memberId));
        }
    }

    private void addParkingLotIdToQuery(String parkingLotId, Root<ParkingLotAllocation> parkingLotAllocation, CriteriaBuilder criteriaBuilder) {
        if (parkingLotId != null && !parkingLotId.isEmpty()) {
            predicates.add(criteriaBuilder.equal(parkingLotAllocation.get("parkingLotId"), parkingLotId));
        }
    }

}
