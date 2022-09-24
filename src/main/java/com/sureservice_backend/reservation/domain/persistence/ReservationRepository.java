package com.sureservice_backend.reservation.domain.persistence;


import com.sureservice_backend.reservation.domain.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation,Long> {
}
