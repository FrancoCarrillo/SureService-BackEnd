package com.sureservice_backend.security.api;


import com.sureservice_backend.security.domain.model.entity.Client;
import com.sureservice_backend.security.domain.service.ClientService;
import com.sureservice_backend.security.domain.service.communication.RegisterRequest;
import com.sureservice_backend.security.domain.service.communication.UpdateClientRequest;
import com.sureservice_backend.security.domain.service.communication.UpdateTechnicianRequest;
import com.sureservice_backend.security.mapping.ClientMapper;
import com.sureservice_backend.security.resource.ClientResource;
import com.sureservice_backend.security.resource.TechnicianResource;
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

    private final ClientMapper mapper;
    private final ClientService clientService;

    public ClientsController(ClientMapper mapper, ClientService clientService) {
        this.mapper = mapper;
        this.clientService = clientService;
    }


    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @PostMapping("sign-up")
    public ResponseEntity<ClientResource> registerClient(@Valid @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(mapper.toResource(clientService.register(request)) , HttpStatus.CREATED) ;
    }

    @PutMapping("{clientId}")
    public ClientResource update(@PathVariable Long clientId, @RequestBody UpdateClientRequest clientRequest){
        return mapper.toResource(clientService.update(clientId, clientRequest));
    }


}
