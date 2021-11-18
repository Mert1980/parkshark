package com.switchfully.parkshark.dto;

import com.switchfully.parkshark.services.mapper.AddressDtoRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Data @ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDtoRequest {
  String firstName;
  String lastName;
  @NotBlank @NotNull
  String email;

  @Valid
  AddressDtoRequest addressDtoRequest;
  String phoneNumberMobile;
  String phoneNumberLocal;
  String licencePlateNumber;
  String registrationDate;
}
