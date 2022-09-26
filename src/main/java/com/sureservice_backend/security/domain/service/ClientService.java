package com.sureservice_backend.security.domain.service;

import com.sureservice_backend.security.domain.model.entity.Client;
import com.sureservice_backend.security.domain.model.entity.Technician;
import com.sureservice_backend.security.domain.service.communication.RegisterRequest;
import com.sureservice_backend.security.domain.service.communication.UpdateClientRequest;
import com.sureservice_backend.security.domain.service.communication.UpdateTechnicianRequest;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    Client register(RegisterRequest request);

    Client update(Long clientId, UpdateClientRequest client);
}
