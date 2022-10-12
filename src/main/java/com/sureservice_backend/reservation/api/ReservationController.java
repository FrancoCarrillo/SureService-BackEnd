package com.sureservice_backend.reservation.api;

import com.sureservice_backend.reservation.domain.service.ReservationService;
import com.sureservice_backend.reservation.mapping.ReservationMapper;
import com.sureservice_backend.reservation.resource.CreateReservationResource;
import com.sureservice_backend.reservation.resource.ReservationResource;
import com.sureservice_backend.reservation.resource.UpdateReservationResource;
import com.sureservice_backend.servicesrequest.resource.ServiceRequestResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reservation", description = "Create, read, update and delete reservation")
@RestController
@RequestMapping(value = "api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper mapper;


    public ReservationController(ReservationService reservationService, ReservationMapper mapper) {
        this.reservationService = reservationService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ReservationResource> getAll() {
        return mapper.modelListToResource(reservationService.getAll());
    }

    @GetMapping("technician/{technicianId}")
    public List<ReservationResource> getByTechnicianId(@PathVariable Long technicianId) {
        return mapper.modelListToResource(reservationService.getAllByTechnicianId(technicianId));
    }

    @GetMapping("client/{clientId}")
    public List<ReservationResource> getByClientId(@PathVariable Long clientId) {
        return mapper.modelListToResource(reservationService.getAllByClientId(clientId));
    }

    @GetMapping("{reservationId}")
    public ReservationResource getById(@PathVariable Long reservationId) {
        return mapper.toResource(reservationService.getById(reservationId));
    }

    @PostMapping("{serviceRequestId}")
    public ReservationResource createRequest(@PathVariable("serviceRequestId") Long serviceRequestId, @RequestBody CreateReservationResource reservationResource) {
        return mapper.toResource(reservationService.create(serviceRequestId, mapper.toModel(reservationResource)));
    }

    @PutMapping("{reservationId}")
    public ReservationResource updateRequest(@PathVariable Long reservationId, @RequestBody UpdateReservationResource reservationResource) {
        return mapper.toResource(reservationService.update(reservationId, mapper.toModel(reservationResource)));
    }

    @DeleteMapping("{reservationId}")
    public ResponseEntity<?> deleteRequest(@PathVariable Long reservationId) {
        return reservationService.delete(reservationId);
    }
}
