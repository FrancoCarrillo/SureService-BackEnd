package com.sureservice_backend.reservation.domain.persistence;


import com.sureservice_backend.reservation.domain.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation,Long> {

    @Query(value = "SELECT * FROM reservation as r, services_request as sr Where sr.client_id=:clientId", nativeQuery=true)
    List<Reservation> findByClientId(Long clientId);
    @Query(value = "SELECT * FROM reservation as r, services_request as sr Where sr.technician_id=:technicianId", nativeQuery=true)
    List<Reservation> findByTechnicianId(Long technicianId);
}
