package com.sureservice_backend.security.domain.service.communication;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class RegisterRequest {
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Email(message = "Email not valid")
    private String email;

    @NotNull
    @NotBlank
    @Length(min = 6, message = "Wrong password lenght")
    private String password;


    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String last_name;

    @NotNull
    @NotBlank
    @Length(min = 9, max = 9, message = "Wrong phone number")
    private String telephone_number;

    @NotNull
    @NotBlank
    @Length(min = 8, max = 8, message = "Wrong DNI")
    private String dni;


}