package com.sureservice_backend.security.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sureservice_backend.security.domain.model.enumeration.Roles;
import com.sureservice_backend.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "roles")
public class Role extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    @OneToMany(mappedBy = "rol")
    @JsonIgnore
    private List<User> users;
}