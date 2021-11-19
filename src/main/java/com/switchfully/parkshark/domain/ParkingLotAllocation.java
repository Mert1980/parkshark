package com.switchfully.parkshark.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter @Builder
@NoArgsConstructor  @AllArgsConstructor
@Table(name = "parking_lot_allocations", schema = "parkshark")
public class ParkingLotAllocation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column (name = "parking_lot_allocation_id")
  long id;

  @Column(name = "parking_lot_id_fk")
  long parkingLotId;

  @OneToOne
  @JoinColumn(name = "person_id_fk")
  Person person;

  @Column(name = "license_plate_number")
  String licensePlateNumber;

  @Column(name = "start_time")
  String startDate;
  @Column(name = "stop_time")
  String endDate;

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
