package com.dnzdlklc.lean.model.dao;

import com.dnzdlklc.lean.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by denizdalkilic on 20/07/2020 @ 20:16.
 */
public interface CustomerDAO extends JpaRepository<Customer, Long> {
}
