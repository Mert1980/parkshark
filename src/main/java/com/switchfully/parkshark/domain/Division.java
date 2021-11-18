package com.switchfully.parkshark.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Division {
    @Id
//    @SequenceGenerator(name = "division_seq", sequenceName = "DIVISION_SEQ", initialValue = 1, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "division_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "division_id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "original_name")
    String originalName;

    @OneToOne
    @JoinColumn(name = "director_id")
    Person director;

    @OneToOne
    @JoinColumn(name = "parent_division_id")
    Division parentDivision;


}
