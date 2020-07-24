package com.dnzdlklc.lean.utils;

import com.dnzdlklc.lean.model.AccountType;
import com.dnzdlklc.lean.model.TransactionType;
import com.dnzdlklc.lean.model.entity.Account;
import com.dnzdlklc.lean.model.entity.Customer;
import com.dnzdlklc.lean.model.entity.Transaction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by denizdalkilic on 24/07/2020 @ 21:24.
 */
public class TestDataGen {

    public static Account getAnAccount() {
        Account account = new Account();
        account.setAccountID(1L);
        account.setCustomer_id(3L);
        account.setAccountType(AccountType.SAVINGS);
        account.setIban("SA000000789654123051");
        account.setStatus("ACTIVE");
        account.setBalance(123.56);
        account.setCurrencyCode("SAR");

        return account;
    }

    public static Customer getACustomer() {
        return new Customer(
                1L, "John Doe",
                LocalDate.of(1990, 9, 15),
                "SAUDI", "user1@leantech.me",
                "1 National Arabic Towers, Riyadh, Saudi Arabia");
    }

    public static Transaction getATransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionID(1L);
        transaction.setAccount_id(1L);
        transaction.setType(TransactionType.TRANSFER);
        transaction.setDescription("Transfer from Savings Account");
        transaction.setAmount(33.256);
        transaction.setCurrencyCode("BHD");
        transaction.setTime_stamp(ZonedDateTime.of(2020,5,1,0,11,37,0, ZoneId.of("UTC")));

        return transaction;
    }
    public static List<Transaction> getListOfTransactions() {
        Transaction transactionOne = new Transaction();
        transactionOne.setTransactionID(1L);
        transactionOne.setAccount_id(1L);
        transactionOne.setType(TransactionType.TRANSFER);
        transactionOne.setDescription("Transfer from Savings Account");
        transactionOne.setAmount(33.256);
        transactionOne.setCurrencyCode("BHD");
        transactionOne.setTime_stamp(ZonedDateTime.of(2020,5,1,0,11,37,0, ZoneId.of("UTC")));

        Transaction transactionTwo = new Transaction();
        transactionTwo.setTransactionID(2L);
        transactionTwo.setAccount_id(1L);
        transactionTwo.setType(TransactionType.TRANSFER);
        transactionTwo.setDescription("Transfer from Savings Account");
        transactionTwo.setAmount(10.00);
        transactionTwo.setCurrencyCode("GBP");
        transactionTwo.setTime_stamp(ZonedDateTime.of(2020,1,1,0,11,37,0, ZoneId.of("UTC")));

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transactionOne);
        transactionList.add(transactionTwo);

        return transactionList;
    }
}
