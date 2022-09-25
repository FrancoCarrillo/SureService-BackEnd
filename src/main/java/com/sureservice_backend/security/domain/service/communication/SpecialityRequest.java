package com.sureservice_backend.security.domain.service.communication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SpecialityRequest {

    @NotBlank
    @NotNull
    @Size(max = 500)
    private String name;
}
