package com.dnzdlklc.lean.controller;

import com.dnzdlklc.lean.exception.CustomEntityNotFoundException;
import com.dnzdlklc.lean.model.entity.Account;
import com.dnzdlklc.lean.model.entity.Customer;
import com.dnzdlklc.lean.model.entity.Transaction;
import com.dnzdlklc.lean.service.AccountServiceImpl;
import com.dnzdlklc.lean.service.CustomerServiceImpl;
import com.dnzdlklc.lean.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by denizdalkilic on 20/07/2020 @ 19:50.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final TransactionServiceImpl transactionService;
    private final AccountServiceImpl accountService;
    private final CustomerServiceImpl customerService;

    @Autowired
    public RestController(TransactionServiceImpl transactionService, AccountServiceImpl accountService, CustomerServiceImpl customerService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) throws Exception {
        Customer customer;
        try {
            customer = customerService.getCustomer(id);
        } catch (CustomEntityNotFoundException e) {
            throw new CustomEntityNotFoundException(e.getMessage());
        }

        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("accounts/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id) throws Exception {
        Account account;
        try {
            account = accountService.getAccount(id);
        } catch (CustomEntityNotFoundException e) {
            throw new CustomEntityNotFoundException(e.getMessage());
        }

        return ResponseEntity.ok().body(account);
    }

    @GetMapping("transactions/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable Long id) throws Exception {
        Transaction transaction;
        try {
            transaction = transactionService.getTransaction(id);
        } catch (CustomEntityNotFoundException e) {
            throw new CustomEntityNotFoundException(e.getMessage());
        }

        return ResponseEntity.ok().body(transaction);
    }

    @GetMapping("accounts/{id}/transactions")
    public ResponseEntity<?> getTransactionForAccount(@PathVariable Long id) throws Exception {
        List<Transaction> transactions;
        try {
            transactions = transactionService.getTransactions(id);
        } catch (CustomEntityNotFoundException e) {
            throw new CustomEntityNotFoundException(e.getMessage());
        }

        return ResponseEntity.ok().body(transactions);
    }
}
