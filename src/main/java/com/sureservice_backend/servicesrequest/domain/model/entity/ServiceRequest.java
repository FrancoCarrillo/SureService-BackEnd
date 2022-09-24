package com.sureservice_backend.servicesrequest.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sureservice_backend.security.domain.model.entity.Client;
import com.sureservice_backend.security.domain.model.entity.Technician;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services_request")
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 500)
    private String detail;

    private int total_price;

    private int reservation_price;

    private int confirmation;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name= "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name= "technician_id", nullable = false)
    @JsonIgnore
    private Technician technician;
}
