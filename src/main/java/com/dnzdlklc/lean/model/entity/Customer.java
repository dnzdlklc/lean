package com.dnzdlklc.lean.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by denizdalkilic on 20/07/2020 @ 19:58.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long customerID;
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private String nationality;
    @Column(name = "email_address")
    private String emailAddress;
    private String address;
}
