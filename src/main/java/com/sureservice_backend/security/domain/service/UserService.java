package com.sureservice_backend.security.domain.service;

import com.sureservice_backend.security.domain.model.entity.User;
import com.sureservice_backend.security.domain.service.communication.AuthenticateRequest;
import com.sureservice_backend.security.domain.service.communication.UpdatePasswordRequest;
import com.sureservice_backend.security.resource.UserResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    ResponseEntity<?> authenticate(AuthenticateRequest request);
    List<User> getAll();
    ResponseEntity<?> delete(Long userId);
    User updatePassword(Long userId, UpdatePasswordRequest request);

}