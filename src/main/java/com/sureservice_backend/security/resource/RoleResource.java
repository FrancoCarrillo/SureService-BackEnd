package com.sureservice_backend.security.resource;

import lombok.*;

@Getter
@Setter
@With
public class RoleResource {
    private Long id;
    private String name;

    public RoleResource(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
