package com.sureservice_backend.security.service;

import com.sureservice_backend.security.domain.model.entity.*;
import com.sureservice_backend.security.domain.model.enumeration.Roles;
import com.sureservice_backend.security.domain.persistence.*;
import com.sureservice_backend.security.domain.service.UserService;
import com.sureservice_backend.security.domain.service.communication.*;
import com.sureservice_backend.security.middleware.JwtHandler;
import com.sureservice_backend.security.middleware.UserDetailsImpl;
import com.sureservice_backend.security.resource.AuthenticateResource;
import com.sureservice_backend.security.resource.ClientResource;
import com.sureservice_backend.security.resource.TechnicianResource;
import com.sureservice_backend.security.resource.UserResource;
import com.sureservice_backend.shared.exception.ResourceValidationException;
import com.sureservice_backend.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TechnicianRepository technicianRepository;

    @Autowired
    SpecialityRepository specialityRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandler handler;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EnhancedModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = handler.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateResource resource = mapper.map(userDetails, AuthenticateResource.class);
            resource.setRoles(roles);
            resource.setToken(token);

            AuthenticateResponse response = new AuthenticateResponse(resource);

            return ResponseEntity.ok(response.getResource());


        } catch (Exception e) {
            AuthenticateResponse response = new AuthenticateResponse(String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> registerClient(RegisterRequest request) {
        if (clientRepository.existsByUsername(request.getUsername())) {
            AuthenticateResponse response = new AuthenticateResponse("Username is already used.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }

        if (clientRepository.existsByEmail(request.getEmail())) {
            AuthenticateResponse response = new AuthenticateResponse("Email is already used.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }

        try {

            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if (rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_CLIENT)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found.")));
            }

            logger.info("Roles: {}", roles);

            Client client = new Client();
            client.setAmount_reservation(0);
            client.setUsername(request.getUsername());
            client.setEmail(request.getEmail());
            client.setPassword(encoder.encode(request.getPassword()));
            client.setRoles(roles);
            client.setDni(request.getDni());
            client.setTelephone_number(request.getTelephone_number());
            client.setName(request.getName());
            client.setLast_name(request.getLast_name());

            clientRepository.save(client);
            ClientResource resource = mapper.map(client, ClientResource.class);
            RegisterClientResponse response = new RegisterClientResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {

            RegisterResponse response = new RegisterResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());

        }
    }

    public ResponseEntity<?> registerTechnician(RegisterTechnicianRequest request) {

        if (clientRepository.existsByUsername(request.getUsername())) {
            AuthenticateResponse response = new AuthenticateResponse("Username is already used.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }

        if (clientRepository.existsByEmail(request.getEmail())) {
            AuthenticateResponse response = new AuthenticateResponse("Email is already used.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }

        try {

            Speciality speciality = specialityRepository.findAllById(request.getSpeciality());

            if(speciality == null){
                throw new ResourceValidationException("Speciality not found");
            }

            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if (rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_CLIENT)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found.")));
            }

            logger.info("Roles: {}", roles);

            Technician technician = new Technician();
            technician.setDisponibility(1);
            technician.setSpeciality(speciality);
            technician.setValoration(0);
            technician.setProfessional_profile(request.getProfessional_profile());
            technician.setDistrict(request.getDistrict());
            technician.setUsername(request.getUsername());
            technician.setEmail(request.getEmail());
            technician.setPassword(encoder.encode(request.getPassword()));
            technician.setRoles(roles);
            technician.setDni(request.getDni());
            technician.setTelephone_number(request.getTelephone_number());
            technician.setName(request.getName());
            technician.setLast_name(request.getLast_name());

            technicianRepository.save(technician);
            TechnicianResource resource = mapper.map(technician, TechnicianResource.class);
            RegisterTechnicianResponse response = new RegisterTechnicianResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {

            RegisterResponse response = new RegisterResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());

        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }



}