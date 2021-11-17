package com.switchfully.parkshark.repositories;

import com.switchfully.parkshark.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
