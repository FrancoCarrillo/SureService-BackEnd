package com.sureservice_backend.security.api;

import com.sureservice_backend.security.domain.service.UserService;
import com.sureservice_backend.security.domain.service.communication.AuthenticateRequest;
import com.sureservice_backend.security.domain.service.communication.RegisterRequest;
import com.sureservice_backend.security.domain.service.communication.RegisterTechnicianRequest;
import com.sureservice_backend.security.mapping.UserMapper;
import com.sureservice_backend.security.resource.UserResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "sureservice")
@Tag(name = "Users", description = "Create, read, update and delete users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UsersController {

    private final UserService userService;

    private final UserMapper mapper;

    public UsersController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateRequest request) {
        return userService.authenticate(request);
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        Page<UserResource> resources = mapper.modelListToPage(userService.getAll(), pageable);
        return ResponseEntity.ok(resources);
    }


}