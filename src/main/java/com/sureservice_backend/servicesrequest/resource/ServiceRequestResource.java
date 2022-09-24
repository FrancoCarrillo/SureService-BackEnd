package com.sureservice_backend.servicesrequest.resource;

import com.sureservice_backend.security.resource.ClientResource;
import com.sureservice_backend.security.resource.TechnicianResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestResource {
    private Long id;
    private String detail;
    private int total_price;
    private int reservation_price;
    private int confirmation;
    private ClientResource client;
    private TechnicianResource technician;
}
