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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandler handler;

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

    public List<User> getAll() {
        return userRepository.findAll();
    }



}