package com.sureservice_backend.reservation.domain.service;

import com.sureservice_backend.reservation.domain.model.entity.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    Reservation getById(Long reservationId);
    List<Reservation> getAll();

    //post, put, delete
    Reservation create(Long serviceRequestId, Reservation reservation);
    Reservation update(Long reservationId, Reservation reservation);
    ResponseEntity<?> delete(Long reservationId);
}
