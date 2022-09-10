package com.sureservice_backend.security.domain.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@With
@AllArgsConstructor
@Entity
@Table(name = "clients")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Client extends User{

    private int amount_reservation;

}
