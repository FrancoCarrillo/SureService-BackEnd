package com.sureservice_backend.security.domain.service;

import com.sureservice_backend.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {

    void seed();

    List<Role> getAll();
}