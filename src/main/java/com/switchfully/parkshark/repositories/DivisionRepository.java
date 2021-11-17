package com.switchfully.parkshark.repositories;

import com.switchfully.parkshark.domain.Division;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisionRepository extends JpaRepository<Division, Long> {
}
