package com.dnzdlklc.lean.model.entity;

import com.dnzdlklc.lean.model.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by denizdalkilic on 20/07/2020 @ 19:55.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long accountID;

    /** This is declared twice because of how hibernate and its entity graphing works during mapping
     * and to avoid N+1 query issues.
     */
    @JsonIgnore
    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customer_id;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private AccountType accountType;
    @Column(name = "account_number")
    private Long accountNumber;
    private String iban;
    private String status;
    private Double balance;
    @Column(name = "currency_code")
    private String currencyCode;
}
