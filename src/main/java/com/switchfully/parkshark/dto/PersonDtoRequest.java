package com.switchfully.parkshark.dto;

import com.switchfully.parkshark.services.mapper.AddressDtoRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Builder
@Data @ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDtoRequest {
  @NotBlank @NotNull
  String firstName;
  @NotBlank @NotNull
  String lastName;
  @NotBlank @NotNull
  String email;

  @Valid
  AddressDtoRequest addressDtoRequest;

  @NotBlank @NotNull
  String phoneNumberMobile;
  String phoneNumberLocal;
  @NotBlank @NotNull
  String licencePlateNumber;
  @NotBlank @NotNull
  String registrationDate;
}
