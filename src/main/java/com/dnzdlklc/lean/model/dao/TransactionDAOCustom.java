package com.dnzdlklc.lean.model.dao;

import com.dnzdlklc.lean.model.entity.Transaction;

import java.util.List;

/**
 * Created by denizdalkilic on 24/07/2020 @ 14:54.
 */
public interface TransactionDAOCustom {
    List<Transaction> findAllTransactionByAccountAndOrderByTimestampDesc(Long accountID);
}
