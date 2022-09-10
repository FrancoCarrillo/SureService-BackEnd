package com.sureservice_backend.security.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientResource {
    private Long id;
    private String username;
    private String email;
    private List<RoleResource> roles;
    private int amount_reservation;
}
