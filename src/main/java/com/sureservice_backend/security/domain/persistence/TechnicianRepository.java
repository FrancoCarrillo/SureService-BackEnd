package com.sureservice_backend.security.domain.persistence;

import com.sureservice_backend.security.domain.model.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    @Override
    Technician getById(Long id);
}
