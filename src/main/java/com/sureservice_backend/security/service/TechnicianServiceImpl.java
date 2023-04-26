package com.sureservice_backend.security.service;

import com.sureservice_backend.reservation.domain.model.entity.Reservation;
import com.sureservice_backend.security.domain.model.entity.Role;
import com.sureservice_backend.security.domain.model.entity.Speciality;
import com.sureservice_backend.security.domain.model.entity.Technician;
import com.sureservice_backend.security.domain.persistence.*;
import com.sureservice_backend.security.domain.service.TechnicianService;
import com.sureservice_backend.security.domain.service.communication.RegisterTechnicianRequest;
import com.sureservice_backend.security.domain.service.communication.UpdateTechnicianRequest;
import com.sureservice_backend.security.mapping.TechnicianMapper;
import com.sureservice_backend.shared.exception.ResourceNotFoundException;
import com.sureservice_backend.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class TechnicianServiceImpl implements TechnicianService {

    private static final String ENTITY = "Reservation";
    private final Validator validator;
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


    public TechnicianServiceImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public List<Technician> getAll() {
        return technicianRepository.findAll();
    }

    @Override
    public List<Technician> getByValoration(Integer valoration) {
        return technicianRepository.findAllByValoration(valoration);
    }

    @Override
    public List<Technician> getByAllSpeciality(Long specialityId) {

        Speciality speciality = specialityRepository.findAllById(specialityId);

        return technicianRepository.findAllBySpeciality(speciality);
    }

    @Override
    public List<Technician> getByDistrict(String districtName) {
        return technicianRepository.findAllByDistrict(districtName);
    }

    @Override
    public List<Technician> getByDisponibilityAndDistrictAndBySpeciality(int disponibility, String districtName, Long specialityId) {
        Speciality speciality = specialityRepository.findAllById(specialityId);
        return technicianRepository.findAllByDisponibilityAndDistrictAndSpeciality(disponibility,districtName,speciality);
    }

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
            technician.setTelephoneNumber(request.getTelephone_number());
            technician.setName(request.getName());
            technician.setLast_name(request.getLast_name());
            technician.setImage_url("https://res.cloudinary.com/daslzhbab/image/upload/v1667853785/mmb0zluthi93wazo6vaa.jpg");
            technician.setImage_Id("mmb0zluthi93wazo6vaa");

            return technicianRepository.save(technician);

        } catch (Exception e) {
            throw new ResourceValidationException(e.getMessage());
        }
    }

    @Override
    public Technician update(Long technicianId, UpdateTechnicianRequest request) {


        Role role = roleRepository.findAllById(2L);
        Speciality speciality = specialityRepository.findAllById(request.getSpeciality());

        Technician technician = technicianRepository.getById(technicianId);
        technician.setId(technicianId);
        technician.setDisponibility(request.getDisponibility());
        technician.setSpeciality(speciality);
        technician.setValoration(request.getValoration());
        technician.setProfessional_profile(request.getProfessional_profile());
        technician.setDistrict(request.getDistrict());
        technician.setUsername(request.getUsername());
        technician.setEmail(request.getEmail());
        technician.setRol(role);
        technician.setDni(request.getDni());
        technician.setTelephoneNumber(request.getTelephone_number());
        technician.setName(request.getName());
        technician.setLast_name(request.getLast_name());

        return technicianRepository.findById(technicianId).map(data ->
                technicianRepository.save(technician)
        ).orElseThrow(()-> new ResourceNotFoundException(ENTITY, technicianId));
    }

    @Override
    public Technician getById(Long id) {
        return technicianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }


}
