package com.sureservice_backend.security.domain.persistence;

import com.sureservice_backend.security.domain.model.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    Speciality findAllById(Long id);
}
