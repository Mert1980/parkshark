package com.switchfully.parkshark.domain;

public enum ParkingLotCategory {
  underground("underground"),
  aboveground("aboveground");

  private final String name;
  ParkingLotCategory (String name){
    this.name = name;
  }
}
