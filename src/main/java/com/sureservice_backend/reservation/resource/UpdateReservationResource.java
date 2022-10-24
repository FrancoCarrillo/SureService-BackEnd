package com.sureservice_backend.reservation.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class UpdateReservationResource {
    private Date date_of;
    private Integer status;
}
