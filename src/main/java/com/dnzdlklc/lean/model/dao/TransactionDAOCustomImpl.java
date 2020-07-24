package com.dnzdlklc.lean.model.dao;

import com.dnzdlklc.lean.exception.CustomEntityNotFoundException;
import com.dnzdlklc.lean.model.entity.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by denizdalkilic on 24/07/2020 @ 14:58.
 */
@Slf4j
public class TransactionDAOCustomImpl implements TransactionDAOCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Transaction> findAllTransactionByAccountAndOrderByTimestampDesc(Long accountID) {
        Query query = entityManager.createNativeQuery("SELECT * FROM transaction WHERE account_id = :accountID ORDER BY time_stamp DESC", Transaction.class);
        query.setParameter("accountID", accountID);

        List<Transaction> transactions = query.getResultList();
        if (transactions.isEmpty()) {
            log.debug("No transactions found for given account.");
            throw new CustomEntityNotFoundException("No transactions found for given account ID.");
        }

        return transactions;
    }
}
