package com.switchfully.parkshark.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DivisionDtoRequest {

  @NotBlank @NotNull
  String name;

  @NotBlank @NotNull
  String originalName;

  @NotNull
  Long directorId;
  Long parentDivisionId;
}
