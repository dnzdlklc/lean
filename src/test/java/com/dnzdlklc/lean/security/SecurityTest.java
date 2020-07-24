package com.dnzdlklc.lean.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by denizdalkilic on 24/07/2020 @ 21:33.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void validRequestOnCustomerService_shouldSucceedWith200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customers/1").header("lean-token", "adf098adsfa098ss"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void invalidRequestOnCustomerService_shouldFailAuthWith401() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void invalidRequestOnAccountService_shouldFailAuthWith401() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/accounts/1"))
                .andExpect(status().isUnauthorized());
    }

}
