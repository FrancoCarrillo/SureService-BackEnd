package com.sureservice_backend.security.api;

import com.sureservice_backend.reservation.resource.CreateReservationResource;
import com.sureservice_backend.reservation.resource.ReservationResource;
import com.sureservice_backend.reservation.resource.UpdateReservationResource;
import com.sureservice_backend.security.domain.service.SpecialityService;
import com.sureservice_backend.security.domain.service.communication.SpecialityRequest;
import com.sureservice_backend.security.mapping.SpecialityMapper;
import com.sureservice_backend.security.resource.SpecialityResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Speciality", description = "create, delete, update specialities")
@RestController
@RequestMapping("/api/v1/speciality")
public class SpecialityController {
    private final SpecialityService specialityService;

    private final SpecialityMapper mapper;

    public SpecialityController(SpecialityService specialityService, SpecialityMapper mapper) {
        this.specialityService = specialityService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<SpecialityResource> getAll() {
        return mapper.modelListToResource(specialityService.getAll());
    }


    @PostMapping()
    public SpecialityResource create(@RequestBody SpecialityRequest specialityResource) {
        return mapper.toResource(specialityService.create(mapper.toModel(specialityResource)));
    }

    @PutMapping("{specialityId}")
    public SpecialityResource updateRequest(@PathVariable Long specialityId, @RequestBody SpecialityRequest specialityResource) {
        return mapper.toResource(specialityService.update(specialityId, mapper.toModel(specialityResource)));
    }

    @DeleteMapping("{specialityId}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long specialityId) {
        return specialityService.delete(specialityId);
    }
}
