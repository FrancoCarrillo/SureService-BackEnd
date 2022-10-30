package com.sureservice_backend.security.mapping.dto;

import com.sureservice_backend.security.domain.model.entity.Role;
import lombok.Getter;

@Getter
public class ClientEntityMapper {
    private Long id;
    private String username;
    private String name;
    private String last_name;
    private String telephone_number;
    private String dni;
    private String email;
    private Role rol;
    private int amount_reservation;

    public ClientEntityMapper(Long id, String username, String email, Role rol, int amount_reservation, String name, String last_name, String telephone_number, String dni) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.amount_reservation = amount_reservation;
        this.name = name;
        this.last_name = last_name;
        this.telephone_number = telephone_number;
        this.dni = dni;
    }
}
