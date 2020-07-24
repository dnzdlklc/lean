package com.dnzdlklc.lean.model.dao;

import com.dnzdlklc.lean.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by denizdalkilic on 20/07/2020 @ 20:16.
 */
public interface AccountDAO extends JpaRepository<Account, Long>{
    Account findByAccountID(Long id);
}
