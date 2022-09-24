package com.sureservice_backend.reservation.resource;

import com.sureservice_backend.servicesrequest.resource.ServiceRequestResource;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservationResource {
    private Long id;
    private Date date_of;
    private ServiceRequestResource serviceRequest;
}
