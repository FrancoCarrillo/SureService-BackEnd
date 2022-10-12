package com.sureservice_backend.servicesrequest.domain.persistence;

import com.sureservice_backend.servicesrequest.domain.model.entity.ServiceRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,Long> {
    List<ServiceRequest> findByClientId(Long clientId);
    List<ServiceRequest> findByTechnicianId(Long technicianId);
}
