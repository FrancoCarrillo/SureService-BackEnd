package com.sureservice_backend.servicesrequest.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateServiceRequestResource {
    @NotBlank
    @Size(max = 500)
    private String detail;

    private int total_price;

    private int reservation_price;

    private int confirmation;
}
