package com.sureservice_backend.security.domain.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name= "clients")
@PrimaryKeyJoinColumn(name = "client_id")
public class Client extends User{

    private int amount_of_reservation;
}
