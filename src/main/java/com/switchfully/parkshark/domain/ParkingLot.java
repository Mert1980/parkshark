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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "parking_lot", schema = "parkshark")
public class ParkingLot {
    @Id
    @SequenceGenerator(name = "parking_lot_id_seq", sequenceName = "parking_lot_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_lot_id_seq")
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "parking_lot_category")
    ParkingLotCategory parkingLotCategory;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id_fk")
    Address address;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contact_id_fk")
    Person contactPerson;

    @Column(name = "capacity")
    int capacity;
    @Column(name = "price_per_hour")
    double pricePerHour;

}
