package com.sureservice_backend.security.resource;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class TechnicianResource {
    private Long id;
    private String username;
    private String email;
    private List<RoleResource> roles;
    private int amount_reservation;
    private String professional_profile;
    private int valoration;
    private String district;
    private int disponibility;
    private String speciality;
}
