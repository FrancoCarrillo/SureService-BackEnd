package com.sureservice_backend.security.mapping.dto;

import com.sureservice_backend.security.domain.model.entity.Role;
import com.sureservice_backend.security.domain.model.entity.Speciality;
import lombok.Getter;

@Getter
public class TechnicianEntityMapper {
    private Long id;
    private String username;
    private String email;
    private Role rol;
    private String professional_profile;
    private int valoration;
    private String district;
    private int disponibility;
    private Speciality speciality;

    public TechnicianEntityMapper(Long id, String username, String email, Role rol, String professional_profile, int valoration, String district, int disponibility, Speciality speciality) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.professional_profile = professional_profile;
        this.valoration = valoration;
        this.district = district;
        this.disponibility = disponibility;
        this.speciality = speciality;
    }
}
