package com.switchfully.parkshark.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDtoResponse {

  long id;
  String firstName;
  String lastName;
  String email;
  AddressDtoResponse addressDtoResponse;
  String phoneNumberMobile;
  String phoneNumberLocal;
  String licencePlateNumber;
  String registrationDate;
}
