package com.sureservice_backend.security.domain.model.entity;

import lombok.*;

import com.sureservice_backend.shared.domain.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String last_name;

    @NotBlank
    @Size(max = 120)
    private String telephone;

    @NotBlank
    @Size(max = 120)
    private String dni;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

}