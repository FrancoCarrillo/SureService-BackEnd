package com.sureservice_backend.security.domain.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name= "technicians")
@PrimaryKeyJoinColumn(name = "technician_id")
public class Technician extends User {

    private String professional_profile;

    private int valoration;

    private String district;

    private int disponibility;

    @ManyToOne
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;
}
