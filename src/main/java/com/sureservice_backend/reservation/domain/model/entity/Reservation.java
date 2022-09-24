package com.sureservice_backend.reservation.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sureservice_backend.servicesrequest.domain.model.entity.ServiceRequest;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date_of;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name= "service_request_id", nullable = false)
    @JsonIgnore
    private ServiceRequest serviceRequest;
}
