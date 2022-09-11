package com.sureservice_backend.security.api;

import com.sureservice_backend.security.domain.service.TechnicianService;
import com.sureservice_backend.security.domain.service.communication.RegisterTechnicianRequest;
import com.sureservice_backend.security.mapping.TechnicianMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "sureservice")
@Tag(name = "Technician", description = "sign-up, sign-in, update technician")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/technician")
public class TechnicianController {

    private final TechnicianMapper mapper;

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianMapper mapper, TechnicianService technicianService) {
        this.mapper = mapper;
        this.technicianService = technicianService;
    }


    @PostMapping("sign-up")
    public ResponseEntity<?> registerTechnician(@Valid @RequestBody RegisterTechnicianRequest request) {
        return new ResponseEntity<>(mapper.toResource(technicianService.register(request)) , HttpStatus.CREATED) ;
    }

}
