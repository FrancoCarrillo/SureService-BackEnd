package com.sureservice_backend.security.resource;

import com.sureservice_backend.security.domain.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResource {
    private Long id;
    private String username;
    private String name;
    private String last_name;
    private String email;
    private String dni;
    private String telephoneNumber;
    private Role rol;
    private int amount_reservation;
    private String image_url;
    private String image_Id;
}
