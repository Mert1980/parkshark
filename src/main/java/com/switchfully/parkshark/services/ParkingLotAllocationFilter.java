package com.switchfully.parkshark.services;

public enum ParkingLotAllocationFilter {
  IN_PROGRESS("inProgress"),
  COMPLETED("completed");

  private final String name;
  ParkingLotAllocationFilter(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
