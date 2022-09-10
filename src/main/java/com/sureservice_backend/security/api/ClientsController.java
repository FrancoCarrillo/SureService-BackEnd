package com.sureservice_backend.security.api;


import com.sureservice_backend.security.domain.service.ClientService;
import com.sureservice_backend.security.domain.service.UserService;
import com.sureservice_backend.security.domain.service.communication.RegisterRequest;
import com.sureservice_backend.security.mapping.ClientMapper;
import com.sureservice_backend.security.resource.ClientResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "sureservice")
@Tag(name = "Clients", description = "sign-up, sign-in, update clients")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {

    private final UserService userService;
    private final ClientMapper mapper;
    private final ClientService clientService;

    public ClientsController(UserService userService, ClientMapper mapper, ClientService clientService) {
        this.userService = userService;
        this.mapper = mapper;
        this.clientService = clientService;
    }


    @PostMapping("sign-up")
    public ResponseEntity<ClientResource> registerClient(@Valid @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(mapper.toResource(clientService.register(request)) , HttpStatus.CREATED) ;
    }


}
