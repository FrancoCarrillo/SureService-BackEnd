package com.sureservice_backend.security.mapping.dto;

import com.sureservice_backend.security.domain.model.entity.Speciality;
import com.sureservice_backend.security.resource.RoleResource;

import java.util.List;

public class TechnicianEntityMapper {
    private Long id;
    private String username;
    private String email;
    private List<RoleResource> roles;
    private String professional_profile;
    private int valoration;
    private String district;
    private int disponibility;
    private Speciality speciality;

    public TechnicianEntityMapper(Long id, String username, String email, List<RoleResource> roles, String professional_profile, int valoration, String district, int disponibility, Speciality speciality) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.professional_profile = professional_profile;
        this.valoration = valoration;
        this.district = district;
        this.disponibility = disponibility;
        this.speciality = speciality;
    }
}
