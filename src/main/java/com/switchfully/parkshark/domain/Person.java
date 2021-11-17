package com.switchfully.parkshark.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "person", schema = "parkshark")
public class Person {
    @Id
    @SequenceGenerator(name = "person_id_seq", sequenceName = "PERSON_ID_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
    private long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phonenumber_local")
    private String phoneNumberLocal;
    @Column(name = "phonenumber_mobile")
    private String phoneNumberMobile;
    @Column(name =  "license_plate_number")
    private String licensePlateNumber;
    @Column(name = "registration_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime registrationDate;
    @Column(name = "address_id_fk")
    private long AddressIdFk;

    public Person(long id, String firstName, String lastName, String email,
        String phoneNumberLocal, String phoneNumberMobile, String licensePlateNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumberLocal = phoneNumberLocal;
        this.phoneNumberMobile = phoneNumberMobile;
        this.licensePlateNumber = licensePlateNumber;
        this.registrationDate = LocalDateTime.now();
    }
}
