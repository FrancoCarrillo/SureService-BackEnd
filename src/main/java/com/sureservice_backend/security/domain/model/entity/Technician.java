package com.sureservice_backend.security.domain.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@With
@AllArgsConstructor
@Entity
@Table(name = "technicians")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Technician extends User{

    @NotBlank
    @Size(max = 50)
    private String professional_profile;

    private int valoration;

    @NotBlank
    @Size(max = 50)
    private String district;

    private int disponibility;

    @ManyToOne
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;
}
