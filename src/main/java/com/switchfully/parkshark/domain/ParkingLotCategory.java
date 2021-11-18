package com.switchfully.parkshark.domain;

public enum ParkingLotCategory {
  UNDER_GROUND_BUILDING("underground building"),
  ABOVE_GROUND_BUILDING("above ground building"),;

  private final String name;
  ParkingLotCategory (String name){
    this.name = name;
  }
}
