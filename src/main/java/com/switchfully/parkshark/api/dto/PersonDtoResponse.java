package com.switchfully.parkshark.api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import com.switchfully.parkshark.domain.Address;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDtoResponse {

   long id;
   String firstName;
   String lastName;
   String email;
   Address address;
   String phoneNumberMobile;
   String phoneNumberLocal;
   String licencePlateNumber;
   String registrationDate;
}
