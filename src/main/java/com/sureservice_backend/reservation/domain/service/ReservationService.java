package com.sureservice_backend.reservation.domain.service;

import com.sureservice_backend.reservation.domain.model.entity.Reservation;
import com.sureservice_backend.servicesrequest.domain.model.entity.ServiceRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    Reservation getById(Long reservationId);
    List<Reservation> getAll();

    List<Reservation> getAllByTechnicianId(Long technicianId);

    List<Reservation> getAllByClientId(Long clientId);
    //post, put, delete
    Reservation create(Long serviceRequestId, Reservation reservation);
    Reservation update(Long reservationId, Reservation reservation);
    ResponseEntity<?> delete(Long reservationId);
}
