package com.sureservice_backend.security.domain.service;

import com.sureservice_backend.security.domain.model.entity.Speciality;
import com.sureservice_backend.security.domain.service.communication.SpecialityRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpecialityService {

    List<Speciality> getAll();
    Speciality create(Speciality speciality);
    Speciality update(Long specialityId, Speciality speciality);
    ResponseEntity<?> delete(Long specialityId);
}
