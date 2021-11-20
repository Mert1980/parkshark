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

    public Specification<ParkingLotAllocation> getParkingLotAllocations(String allocationStatus, String memberId, String parkingLotId) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            addParkingLotAllocationStatusToQuery(allocationStatus, root, criteriaBuilder, predicates);
            addMemberIdToQuery(memberId, root, criteriaBuilder, predicates);
            addParkingLotIdToQuery(parkingLotId, root, criteriaBuilder, predicates);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private void addParkingLotAllocationStatusToQuery(String allocationStatus, Root<ParkingLotAllocation> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (allocationStatus != null && !allocationStatus.isEmpty()) {
            if (allocationStatus.equals("Stopped")) {
                predicates.add(criteriaBuilder.isNotNull(root.get("stopTime")));
            }
            if (allocationStatus.equals("Active")) {
                predicates.add(criteriaBuilder.isNull(root.get("stopTime")));
            }
        }
    }

    private void addMemberIdToQuery(String memberId, Root<ParkingLotAllocation> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (memberId != null && !memberId.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("person").get("id"), memberId));
        }
    }

    private void addParkingLotIdToQuery(String parkingLotId, Root<ParkingLotAllocation> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        if (parkingLotId != null && !parkingLotId.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("parkingLotId"), parkingLotId));
        }
    }

}
