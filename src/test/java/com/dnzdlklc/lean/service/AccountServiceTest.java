package com.dnzdlklc.lean.service;

import com.dnzdlklc.lean.exception.CustomEntityNotFoundException;
import com.dnzdlklc.lean.model.dao.AccountDAO;
import com.dnzdlklc.lean.model.entity.Account;
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
 * Created by denizdalkilic on 24/07/2020 @ 19:30.
 */
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @TestConfiguration
    static class AccountServiceImplTestContextConfiguration {

        @Bean
        public AccountServiceImpl employeeService() {
            return new AccountServiceImpl();
        }
    }

    @MockBean
    private AccountDAO accountDAO;

    @Autowired
    private AccountServiceImpl accountService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validAccountID_shouldReturnEntity() {
        final Long id = 1L;

        given(accountDAO.findById(id)).willReturn(Optional.of(TestDataGen.getAnAccount()));

        final Account accountRetrieved = accountService.getAccount(1L);

        assertEquals(accountRetrieved.getAccountID(), TestDataGen.getAnAccount().getAccountID());
    }

    @Test
    public void invalidAccountID_shouldThrowException() {
        final Long id = 10L;

        given(accountDAO.findById(id)).willReturn(Optional.empty());
        exception.expect(CustomEntityNotFoundException.class);

        accountService.getAccount(id);
    }
}
