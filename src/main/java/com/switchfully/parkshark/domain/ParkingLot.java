package com.switchfully.parkshark.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "parkinglot", schema = "parkshark")
public class ParkingLot {
    @Id
    @SequenceGenerator(name = "parkinglot_id_seq", sequenceName = "parkinglot_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkinglot_id_seq")
    long id;

    @Column(name = "name")
    String name;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id_fk")
    Address address;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contact_id_fk")
    Person contactPerson;

    @Column(name = "capacity")
    int maxCapacity;
    @Column(name = "price_per_hour")
    double pricePerHour;





}
