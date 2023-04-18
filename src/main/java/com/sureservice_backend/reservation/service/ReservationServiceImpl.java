package com.sureservice_backend.reservation.service;

import com.sureservice_backend.reservation.domain.model.entity.Reservation;
import com.sureservice_backend.reservation.domain.persistence.ReservationRepository;
import com.sureservice_backend.reservation.domain.service.ReservationService;
import com.sureservice_backend.servicesrequest.domain.model.entity.ServiceRequest;
import com.sureservice_backend.servicesrequest.domain.persistence.ServiceRequestRepository;
import com.sureservice_backend.shared.exception.ResourceNotFoundException;
import com.sureservice_backend.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final String ENTITY = "Reservation";
    private final ServiceRequestRepository serviceRequestRepository;
    private final ReservationRepository reservationRepository;
    private final Validator validator;

    public ReservationServiceImpl(ServiceRequestRepository serviceRequestRepository, ReservationRepository reservationRepository, Validator validator) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.reservationRepository = reservationRepository;
        this.validator = validator;
    }


    @Override
    public Reservation getById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,reservationId));
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getAllByTechnicianId(Long technicianId) {
        Comparator<Reservation> comparator = Comparator.comparing(Reservation::getId).reversed();
        return reservationRepository.findByTechnicianId(technicianId).stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    @Override
    public List<Reservation> getAllByClientId(Long clientId) {
        Comparator<Reservation> comparator = Comparator.comparing(Reservation::getId).reversed();
        return reservationRepository.findByClientId(clientId).stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public Reservation create(Long serviceRequestId, Reservation reservation) {
        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return serviceRequestRepository.findById(serviceRequestId).map(serviceRequest->{
            reservation.setServiceRequest(serviceRequest);
                return reservationRepository.save(reservation);
            }).orElseThrow(() -> new ResourceNotFoundException("ServiceRequest", serviceRequestId));
    }

    @Override
    public Reservation update(Long reservationId, Reservation reservation) {
        Set<ConstraintViolation<Reservation>> violations = validator.validate(reservation);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return reservationRepository.findById(reservationId).map(data ->
                reservationRepository.save(
                        data.withDate_of(reservation.getDate_of())
                                .withStatus(reservation.getStatus())
                )
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, reservationId));
    }

    @Override
    public ResponseEntity<?> delete(Long reservationId) {
        return reservationRepository.findById(reservationId).map(announcement -> {
            reservationRepository.delete(announcement);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, reservationId));
    }
}
