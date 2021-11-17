package com.switchfully.parkshark.api.dto;

import com.switchfully.parkshark.domain.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDtoRequest {
  String firstName;
  String lastName;
  String email;
  Address address;
  String phoneNumberMobile;
  String phoneNumberLocal;
  String licencePlateNumber;
  String registrationDate;
}
