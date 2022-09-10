package com.sureservice_backend.security.domain.service;

import com.sureservice_backend.security.domain.service.communication.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface TechnicianService extends UserDetailsService {
    ResponseEntity<?> register(RegisterRequest request);
}
