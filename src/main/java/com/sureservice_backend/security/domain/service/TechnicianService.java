package com.sureservice_backend.security.domain.service;

import com.sureservice_backend.security.domain.model.entity.Technician;
import com.sureservice_backend.security.domain.service.communication.RegisterTechnicianRequest;

public interface TechnicianService {
    Technician register(RegisterTechnicianRequest request);
}
