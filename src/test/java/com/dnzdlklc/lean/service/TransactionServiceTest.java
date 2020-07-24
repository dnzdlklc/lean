package com.dnzdlklc.lean.service;

import com.dnzdlklc.lean.utils.TestDataGen;
import com.dnzdlklc.lean.exception.CustomEntityNotFoundException;
import com.dnzdlklc.lean.model.dao.TransactionDAO;
import com.dnzdlklc.lean.model.entity.Transaction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * Created by denizdalkilic on 24/07/2020 @ 21:04.
 */
@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    @TestConfiguration
    static class TransactionServiceTestImplTestContextConfiguration {

        @Bean
        public TransactionService transactionService() {
            return new TransactionServiceImpl();
        }
    }

    @MockBean
    private TransactionDAO transactionDAO;

    @Autowired
    private TransactionService transactionService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void validTransactionID_shouldReturnEntity() {
        final Long id = 1L;


        given(transactionDAO.findById(id)).willReturn(Optional.of(TestDataGen.getATransaction()));
        final Transaction transactionRetrieved = transactionService.getTransaction(id);

        assertEquals(TestDataGen.getATransaction().getAccount_id(), transactionRetrieved.getAccount_id());
    }

    @Test
    public void validAccountID_shouldReturnListOfTransactions() {
        final Long id = 1L;

        given(transactionDAO.findAllTransactionByAccountAndOrderByTimestampDesc(id)).willReturn(TestDataGen.getListOfTransactions());
        final List<Transaction> transactionsRetrieved = transactionService.getTransactions(id);

        assertEquals(TestDataGen.getListOfTransactions().size(), transactionsRetrieved.size());
        assertEquals(TestDataGen.getListOfTransactions().get(0).getAccount_id(), transactionsRetrieved.get(0).getAccount_id());
    }

    @Test
    public void invalidTransactionID_shouldThrowException() {
        final Long id = 100L;

        given(transactionDAO.findById(id)).willReturn(Optional.empty());
        exception.expect(CustomEntityNotFoundException.class);

        transactionService.getTransaction(id);
    }
}
