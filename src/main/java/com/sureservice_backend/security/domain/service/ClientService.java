package com.sureservice_backend.security.domain.service;

import com.sureservice_backend.security.domain.model.entity.Client;
import com.sureservice_backend.security.domain.service.communication.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    Client register(RegisterRequest request);
}
