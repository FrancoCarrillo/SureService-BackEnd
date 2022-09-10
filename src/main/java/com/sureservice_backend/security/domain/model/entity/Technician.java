package com.sureservice_backend.security.domain.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
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

    @Size(max = 5)
    private int valoration;

    @NotBlank
    @Size(max = 50)
    private String district;

    @NotBlank
    @Size(max = 2)
    private int disponibility;
}
