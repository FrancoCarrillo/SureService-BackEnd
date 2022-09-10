package com.sureservice_backend.security.resource;

import com.sureservice_backend.security.domain.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResource {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private int amount_reservation;
}
