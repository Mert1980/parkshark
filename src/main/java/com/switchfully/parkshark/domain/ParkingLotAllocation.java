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
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parking_lot_allocations", schema = "parkshark")
public class ParkingLotAllocation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "parking_lot_allocation_id")
  long id;

  @Column(name = "parking_lot_id_fk")
  long parkingLotId;

  @OneToOne
  @JoinColumn(name = "person_id_fk")
  Person person;

  @Column(name = "license_plate_number")
  String licensePlateNumber;

  @Column(name = "start_time")
  String startTime;
  @Column(name = "stop_time")
  String stopTime;

  public void setStartTime(String startDate) {
    this.startTime = startDate;
  }

  public void setStopTime(String endDate) {
    this.stopTime = endDate;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
