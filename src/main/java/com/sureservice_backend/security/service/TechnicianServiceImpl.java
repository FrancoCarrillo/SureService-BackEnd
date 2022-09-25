package com.sureservice_backend.security.service;

import com.sureservice_backend.security.domain.model.entity.Role;
import com.sureservice_backend.security.domain.model.entity.Speciality;
import com.sureservice_backend.security.domain.model.entity.Technician;
import com.sureservice_backend.security.domain.persistence.*;
import com.sureservice_backend.security.domain.service.TechnicianService;
import com.sureservice_backend.security.domain.service.communication.RegisterTechnicianRequest;
import com.sureservice_backend.security.mapping.TechnicianMapper;
import com.sureservice_backend.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TechnicianServiceImpl implements TechnicianService {

    @Autowired
    TechnicianRepository technicianRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SpecialityRepository specialityRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    TechnicianMapper mapper;
    @Override
    public Technician register(RegisterTechnicianRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new ResourceValidationException("Username is already used.");

        if (userRepository.existsByEmail(request.getEmail()))
            throw new ResourceValidationException("Email is already used.");

        Role role = roleRepository.findAllById(2L);

        if(role == null)
            throw new ResourceValidationException("Role not found");

        Speciality speciality = specialityRepository.findAllById(request.getSpeciality());

        if(speciality == null)
            throw new ResourceValidationException("Speciality not found");

        try {

            Technician technician = new Technician();
            technician.setDisponibility(1);
            technician.setSpeciality(speciality);
            technician.setValoration(0);
            technician.setProfessional_profile(request.getProfessional_profile());
            technician.setDistrict(request.getDistrict());
            technician.setUsername(request.getUsername());
            technician.setEmail(request.getEmail());
            technician.setPassword(encoder.encode(request.getPassword()));
            technician.setRol(role);
            technician.setDni(request.getDni());
            technician.setTelephone_number(request.getTelephone_number());
            technician.setName(request.getName());
            technician.setLast_name(request.getLast_name());

            return technicianRepository.save(technician);

        } catch (Exception e) {
            throw new ResourceValidationException(e.getMessage());
        }
    }
}
