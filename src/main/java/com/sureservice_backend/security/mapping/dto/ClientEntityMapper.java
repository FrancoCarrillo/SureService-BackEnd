package com.sureservice_backend.security.mapping.dto;

import com.sureservice_backend.security.domain.model.entity.Role;
import lombok.Getter;

@Getter
public class ClientEntityMapper {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private int amount_reservation;

    public ClientEntityMapper(Long id, String username, String email, Role role, int amount_reservation) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.amount_reservation = amount_reservation;
    }
}
