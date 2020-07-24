package com.dnzdlklc.lean.service;

import com.dnzdlklc.lean.model.entity.Transaction;

import java.util.List;

/**
 * Created by denizdalkilic on 24/07/2020 @ 21:03.
 */
public interface TransactionService {
    Transaction getTransaction(Long transactionID);
    List<Transaction> getTransactions(Long accountID);
}
