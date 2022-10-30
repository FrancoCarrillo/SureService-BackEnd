package com.sureservice_backend.security.domain.service.communication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class UpdateClientRequest {

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
    private int amount_reservation;
}
