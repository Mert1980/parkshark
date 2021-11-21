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

  @NotBlank(message = "Division name can not be empty")
  @NotNull
  String name;

  @NotBlank(message = "Division original name can not be empty")
  @NotNull
  String originalName;

  @NotNull(message = "Director id can not be empty")
  Long directorId;

  Long parentDivisionId;
}
