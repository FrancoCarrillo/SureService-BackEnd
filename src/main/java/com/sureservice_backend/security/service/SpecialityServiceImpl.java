package com.sureservice_backend.security.service;

import com.sureservice_backend.security.domain.model.entity.Speciality;
import com.sureservice_backend.security.domain.persistence.SpecialityRepository;
import com.sureservice_backend.security.domain.service.SpecialityService;
import com.sureservice_backend.shared.exception.ResourceNotFoundException;
import com.sureservice_backend.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    private static final String ENTITY = "Speciality";
    private final SpecialityRepository specialityRepository;
    private final Validator validator;

    public SpecialityServiceImpl(SpecialityRepository specialityRepository, Validator validator) {
        this.specialityRepository = specialityRepository;
        this.validator = validator;
    }


    @Override
    public List<Speciality> getAll() {
        return specialityRepository.findAll();
    }

    @Override
    public Speciality create(Speciality speciality) {
        Set<ConstraintViolation<Speciality>> violations = validator.validate(speciality);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        try{
            return specialityRepository.save(speciality);
        } catch (Exception e){
            throw new ResourceValidationException(ENTITY, e);
        }
    }

    @Override
    public Speciality update(Long specialityId, Speciality speciality) {
        Set<ConstraintViolation<Speciality>> violations = validator.validate(speciality);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        return specialityRepository.findById(specialityId).map(data ->
                specialityRepository.save(
                        data.withName(speciality.getName()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, specialityId));
    }

    @Override
    public ResponseEntity<?> delete(Long specialityId) {
        return specialityRepository.findById(specialityId).map(announcement -> {
            specialityRepository.delete(announcement);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, specialityId));
    }
}
