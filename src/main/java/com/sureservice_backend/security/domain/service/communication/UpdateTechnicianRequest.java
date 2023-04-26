package com.sureservice_backend.security.domain.service.communication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UpdateTechnicianRequest {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String last_name;

    @NotNull
    @NotBlank
    private String telephone_number;

    @NotNull
    @NotBlank
    private String dni;

    @NotNull
    @NotBlank
    private String professional_profile;


    @NotNull
    @NotBlank
    private String district;

    @NotNull
    private Long speciality;

    @Min(value = 1, message = "The valoration must have more than 1")
    @Max(value = 5,  message = "The valoration must have less than 5")
    private int valoration;

    @Min(value = 0, message = "The valoration must have more than 0")
    @Max(value = 1,  message = "The valoration must have less than 1")
    private int disponibility;
}
