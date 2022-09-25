package com.sureservice_backend.security.domain.service;

import com.sureservice_backend.security.domain.model.entity.User;
import com.sureservice_backend.security.domain.service.communication.AuthenticateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    ResponseEntity<?> authenticate(AuthenticateRequest request);
    List<User> getAll();
    ResponseEntity<?> delete(Long userId);
}