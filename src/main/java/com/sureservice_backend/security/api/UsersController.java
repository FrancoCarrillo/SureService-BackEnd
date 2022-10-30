package com.sureservice_backend.security.api;

import com.sureservice_backend.security.domain.model.entity.User;
import com.sureservice_backend.security.domain.service.UserService;
import com.sureservice_backend.security.domain.service.communication.AuthenticateRequest;
import com.sureservice_backend.security.domain.service.communication.RegisterRequest;
import com.sureservice_backend.security.domain.service.communication.RegisterTechnicianRequest;
import com.sureservice_backend.security.domain.service.communication.UpdatePasswordRequest;
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
import java.util.List;

@SecurityRequirement(name = "sureservice")
@Tag(name = "Users", description = "Create, read, update and delete users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
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

    @PutMapping("/password/{userId}")
    public UserResource updatePassword(@PathVariable Long userId, @RequestBody UpdatePasswordRequest request){
        return mapper.toResource(userService.updatePassword(userId, request));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return mapper.modelListToResource(userService.getAll());
    }


    @DeleteMapping("{userId}")
    public ResponseEntity<?> delete(@PathVariable Long userId){
        return userService.delete(userId);
    }
}