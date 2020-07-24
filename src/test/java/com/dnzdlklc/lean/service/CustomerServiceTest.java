package com.dnzdlklc.lean.service;

import com.dnzdlklc.lean.exception.CustomEntityNotFoundException;
import com.dnzdlklc.lean.model.dao.CustomerDAO;
import com.dnzdlklc.lean.model.entity.Customer;
import com.dnzdlklc.lean.utils.TestDataGen;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * Created by denizdalkilic on 24/07/2020 @ 20:21.
 */
@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @TestConfiguration
    static class CustomerServiceTestContextConfiguration {

        @Bean
        public CustomerService customerService() {
            return new CustomerServiceImpl();
        }
    }

    @MockBean
    private CustomerDAO customerDAO;

    @Autowired
    private CustomerServiceImpl customerService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validAccountID_shouldReturnEntity() {
        final Long id = 1L;


        given(customerDAO.findById(id)).willReturn(Optional.of(TestDataGen.getACustomer()));
        final Customer customerRetrieved = customerService.getCustomer(id);

        assertEquals(TestDataGen.getACustomer().getCustomerID(), customerRetrieved.getCustomerID());
    }

    @Test
    public void invalidCustomerID_shouldThrowException() {
        final Long id = 100L;

        given(customerDAO.findById(id)).willReturn(Optional.empty());
        exception.expect(CustomEntityNotFoundException.class);

        customerService.getCustomer(id);
    }
}
