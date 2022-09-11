package com.sureservice_backend.security.resource;

import com.sureservice_backend.security.domain.model.entity.Role;
import com.sureservice_backend.security.domain.model.entity.Speciality;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnicianResource {
    private Long id;
    private String username;
    private String email;
    private Role rol;
    private String professional_profile;
    private int valoration;
    private String district;
    private int disponibility;
    private Speciality speciality;
}
