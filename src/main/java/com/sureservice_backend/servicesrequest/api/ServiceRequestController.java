package com.sureservice_backend.servicesrequest.api;

import com.sureservice_backend.servicesrequest.domain.service.ServiceRequestService;
import com.sureservice_backend.servicesrequest.mapping.ServiceRequestMapper;
import com.sureservice_backend.servicesrequest.resource.CreateServiceRequestResource;
import com.sureservice_backend.servicesrequest.resource.ServiceRequestResource;
import com.sureservice_backend.servicesrequest.resource.UpdateServiceRequestResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ServiceRequest", description = "Create, read, update and delete services requests")
@RestController
@RequestMapping(value = "api/v1/services")
public class ServiceRequestController {
    private final ServiceRequestService serviceRequestService;
    private final ServiceRequestMapper mapper;

    public ServiceRequestController(ServiceRequestService serviceRequestService, ServiceRequestMapper mapper) {
        this.serviceRequestService = serviceRequestService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ServiceRequestResource> getAll() {
        return mapper.modelListToResource(serviceRequestService.getAll());
    }


    @GetMapping("technician/{technicianId}")
    public List<ServiceRequestResource> getAllByTechnicianId(@PathVariable Long technicianId) {
        return mapper.modelListToResource(serviceRequestService.getAllByTechnicianId(technicianId));
    }

    @GetMapping("client/{clientId}")
    public List<ServiceRequestResource> getAllByClientId(@PathVariable Long clientId) {
        return mapper.modelListToResource(serviceRequestService.getAllByClientId(clientId));
    }
    @GetMapping("{serviceRequestId}")
    public ServiceRequestResource getById(@PathVariable Long serviceRequestId) {
        return mapper.toResource(serviceRequestService.getById(serviceRequestId));
    }

    @PostMapping("{clientId}/{technicianId}")
    public ServiceRequestResource createRequest(@PathVariable("clientId") Long clientId,@PathVariable("technicianId")
    Long technicianId, @RequestBody CreateServiceRequestResource request) {
        return mapper.toResource(serviceRequestService.create(clientId,technicianId, mapper.toModel(request)));
    }

    @PutMapping("{serviceRequestId}")
    public ServiceRequestResource updateRequest(@PathVariable("serviceRequestId") Long requestId, @RequestBody UpdateServiceRequestResource request) {
        return mapper.toResource(serviceRequestService.update(requestId, mapper.toModel(request)));
    }

    @DeleteMapping("{serviceRequestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable("serviceRequestId") Long requestId) {
        return serviceRequestService.delete(requestId);
    }
}
