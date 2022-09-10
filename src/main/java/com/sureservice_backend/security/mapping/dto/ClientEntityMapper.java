package com.sureservice_backend.security.mapping.dto;

import com.sureservice_backend.security.domain.model.entity.Role;
import com.sureservice_backend.security.resource.RoleResource;
import lombok.*;

import java.util.List;
import java.util.Set;

public class ClientEntityMapper {
    private Long id;
    private String username;
    private String email;
    private List<RoleResource> roles;
    private int amount_reservation;

    public ClientEntityMapper(Long id, String username, String email, List<RoleResource> roles, int amount_reservation) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.amount_reservation = amount_reservation;
    }
}
