package com.dnzdlklc.lean.service;

import com.dnzdlklc.lean.exception.CustomEntityNotFoundException;
import com.dnzdlklc.lean.model.dao.TransactionDAO;
import com.dnzdlklc.lean.model.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by denizdalkilic on 20/07/2020 @ 20:29.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    public Transaction getTransaction(Long transactionID) {
        return transactionDAO.findById(transactionID)
                .orElseThrow(() -> new CustomEntityNotFoundException("No such transaction found."));
    }

    public List<Transaction> getTransactions(Long accountID) {
        return transactionDAO.findAllTransactionByAccountAndOrderByTimestampDesc(accountID);
    }
}
