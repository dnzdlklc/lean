package com.dnzdlklc.lean.model.entity;

import com.dnzdlklc.lean.model.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by denizdalkilic on 20/07/2020 @ 20:00.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private Long transactionID;

    @JsonIgnore
    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "account_id", insertable = false, updatable = false)
    private Long account_id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TransactionType type;

    private String description;
    private Double amount;
    @Column(name = "currency_code")
    private String currencyCode;
    private ZonedDateTime time_stamp;
}
