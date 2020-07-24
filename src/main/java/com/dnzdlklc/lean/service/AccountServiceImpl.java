package com.dnzdlklc.lean.service;

import com.dnzdlklc.lean.exception.CustomEntityNotFoundException;
import com.dnzdlklc.lean.model.dao.AccountDAO;
import com.dnzdlklc.lean.model.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by denizdalkilic on 20/07/2020 @ 20:27.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public Account getAccount(Long accountID) {
        return accountDAO.findById(accountID)
                .orElseThrow(() -> new CustomEntityNotFoundException("No such account found with ID: " + accountID));
    }
}
