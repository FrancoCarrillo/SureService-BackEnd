package com.sureservice_backend.security.domain.service.communication;

import com.sureservice_backend.security.domain.model.entity.Speciality;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class RegisterTechnicianRequest {
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

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
    private String speciality;
}
