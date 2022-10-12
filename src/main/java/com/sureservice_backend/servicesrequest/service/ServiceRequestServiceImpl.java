package com.sureservice_backend.servicesrequest.service;

import com.sureservice_backend.security.domain.persistence.ClientRepository;
import com.sureservice_backend.security.domain.persistence.TechnicianRepository;
import com.sureservice_backend.servicesrequest.domain.model.entity.ServiceRequest;
import com.sureservice_backend.servicesrequest.domain.persistence.ServiceRequestRepository;
import com.sureservice_backend.servicesrequest.domain.service.ServiceRequestService;
import com.sureservice_backend.shared.exception.ResourceNotFoundException;
import com.sureservice_backend.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private static final String ENTITY = "ServiceRequest";
    private final TechnicianRepository technicianRepository;
    private final ClientRepository clientRepository;
    private final ServiceRequestRepository serviceRequestRepository;
    private final Validator validator;

    public ServiceRequestServiceImpl(TechnicianRepository technicianRepository, ClientRepository clientRepository, ServiceRequestRepository serviceRequestRepository, Validator validator) {
        this.technicianRepository = technicianRepository;
        this.clientRepository = clientRepository;
        this.serviceRequestRepository = serviceRequestRepository;
        this.validator = validator;
    }


    @Override
    public ServiceRequest getById(Long serviceRequestId) {
        return serviceRequestRepository.findById(serviceRequestId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,serviceRequestId));
    }

    @Override
    public List<ServiceRequest> getAll() {
        return serviceRequestRepository.findAll();
    }

    @Override
    public List<ServiceRequest> getAllByTechnicianId(Long technicianId) {
        return serviceRequestRepository.findByTechnicianId(technicianId);
    }

    @Override
    public List<ServiceRequest> getAllByClientId(Long clientId) {
        return serviceRequestRepository.findByClientId(clientId);
    }

    @Override
    public ServiceRequest create(Long clientId, Long technicianId, ServiceRequest serviceRequest) {
        Set<ConstraintViolation<ServiceRequest>> violations = validator.validate(serviceRequest);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return clientRepository.findById(clientId).map(client->{
            serviceRequest.setClient(client);
            return technicianRepository.findById(technicianId).map(technician->{
                serviceRequest.setTechnician(technician);
                return serviceRequestRepository.save(serviceRequest);
            }).orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        }).orElseThrow(() -> new ResourceNotFoundException("Technician", technicianId));
    }

    @Override
    public ServiceRequest update(Long serviceRequestId, ServiceRequest serviceRequest) {
        Set<ConstraintViolation<ServiceRequest>> violations = validator.validate(serviceRequest);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return serviceRequestRepository.findById(serviceRequestId).map(data ->
                serviceRequestRepository.save(
                        data.withDetail(data.getDetail())
                                .withTotal_price(serviceRequest.getTotal_price())
                                .withReservation_price(serviceRequest.getReservation_price())
                                .withConfirmation(serviceRequest.getConfirmation()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, serviceRequestId));
    }

    @Override
    public ResponseEntity<?> delete(Long requestId) {
        return serviceRequestRepository.findById(requestId).map(announcement -> {
            serviceRequestRepository.delete(announcement);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, requestId));
    }
}
