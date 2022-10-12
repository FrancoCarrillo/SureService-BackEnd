package com.sureservice_backend.servicesrequest.domain.service;

import com.sureservice_backend.servicesrequest.domain.model.entity.ServiceRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceRequestService {
    ServiceRequest getById(Long serviceRequestId);
    List<ServiceRequest> getAll();

    List<ServiceRequest> getAllByTechnicianId(Long technicianId);

    List<ServiceRequest> getAllByClientId(Long clientId);

    //post, put, delete
    ServiceRequest create(Long clientId, Long technicianId, ServiceRequest serviceRequest);
    ServiceRequest update(Long serviceRequestId, ServiceRequest serviceRequest);
    ResponseEntity<?> delete(Long requestId);
}
