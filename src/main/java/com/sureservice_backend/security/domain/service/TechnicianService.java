package com.sureservice_backend.security.domain.service;

import com.sureservice_backend.security.domain.model.entity.Technician;
import com.sureservice_backend.security.domain.service.communication.RegisterTechnicianRequest;
import com.sureservice_backend.security.domain.service.communication.UpdateTechnicianRequest;

import java.util.List;

public interface TechnicianService {
    Technician register(RegisterTechnicianRequest request);

    Technician update(Long technicianId, UpdateTechnicianRequest technician);

    Technician getById(Long id);

    List<Technician> getAll();

    List<Technician> getByValoration(Integer valoration);

    List<Technician> getByAllSpeciality(Long specialityId);

    List<Technician> getByDistrict(String districtName);
}
