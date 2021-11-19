package com.switchfully.parkshark.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parking_lot", schema = "parkshark")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parking_lot_id")
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "parking_lot_category", columnDefinition = "parking_category")
    @Enumerated(EnumType.STRING)
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
