package com.switchfully.parkshark.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Builder
@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDtoRequest {

  @NotBlank(message = "First name can not be empty")
  @NotNull
  String firstName;

  @NotBlank(message = "Last name can not be empty")
  @NotNull
  String lastName;

  @NotBlank(message = "Email name can not be empty")
  @NotNull
  String email;

  @Valid
  AddressDtoRequest addressDtoRequest;

  @NotBlank(message = "Mobile phone number name can not be empty")
  @NotNull
  String phoneNumberMobile;

  String phoneNumberLocal;

  @NotBlank(message = "Licence plate number can not be empty")
  @NotNull
  String licencePlateNumber;

  String membershipLevel;
}
