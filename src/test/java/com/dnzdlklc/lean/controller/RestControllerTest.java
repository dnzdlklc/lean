package com.dnzdlklc.lean.controller;

import com.dnzdlklc.lean.LeanApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by denizdalkilic on 24/07/2020 @ 18:36.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LeanApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void invalidEndpoint_shouldFailWith404() {
        headers.set("lean-token", "45kh2345jh245hhk");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("to/be/or/not/to/be"), HttpMethod.GET, entity, String.class);

        assertEquals(404, response.getStatusCode().value());
    }

    /**
     * CUSTOMER endpoint tests
     */
    @Test
    public void validCustomerID_shouldReturnWith200() {
        headers.set("lean-token", "45kh2345jh245hhk");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("customers/1"), HttpMethod.GET, entity, String.class);

        String expected = "{\"customerID\":1,\"name\":\"John Doe\"," +
                "\"dateOfBirth\":\"1990-01-01\"," +
                "\"nationality\":\"SAUDI\"," +
                "\"emailAddress\":\"user1@leantech.me\"," +
                "\"address\":\"1 National Arabic Towers, Riyadh, Saudi Arabia\"" +
                "}";

        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void invalidCustomerID_shouldFailWith404() {
        headers.set("lean-token", "45kh2345jh245hhk");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("customers/10"), HttpMethod.GET, entity, String.class);

        String expected = "{\"status\":404,\"message\":\"No customer found with ID: 10\",\"success\":false}";

        assertEquals(expected, response.getBody());
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    public void invalidCustomerIDFormat_shouldFailWith500() {
        headers.set("lean-token", "45kh2345jh245hhk");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("customers/as"), HttpMethod.GET, entity, String.class);

        assertEquals(400, response.getStatusCode().value());
    }

    /**
     * ACCOUNT endpoint tests
      */
    @Test
    public void validAccountID_shouldReturnWith200() {
        headers.set("lean-token", "45kh2345jh245hhk");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("accounts/1"), HttpMethod.GET, entity, String.class);

        String expected = "{\"accountID\":1,\"customer_id\":3," +
                "\"accountType\":\"SAVINGS\"," +
                "\"accountNumber\":789654123051," +
                "\"iban\":\"SA000000789654123051\"," +
                "\"status\":\"ACTIVE\"," +
                "\"balance\":123.56," +
                "\"currencyCode\":\"SAR\"}";

        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void invalidAccountID_shouldFailWith404() {
        headers.set("lean-token", "45kh2345jh245hhk");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("accounts/10"), HttpMethod.GET, entity, String.class);

        String expected = "{\"status\":404,\"message\":\"No such account found with ID: 10\",\"success\":false}";

        assertEquals(expected, response.getBody());
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    public void invalidAccountIDFormat_shouldFailWith500() {
        headers.set("lean-token", "45kh2345jh245hhk");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("accounts/as"), HttpMethod.GET, entity, String.class);

        String expected = "{\"status\":500,\"message\":\"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; nested exception is java.lang.NumberFormatException: For input string: \\\"as\\\"\",\"success\":false}";

        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    public void validAccountID_shouldReturnTransactionsWith200() {
        headers.set("lean-token", "45kh2345jh245hhk");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("accounts/2/transactions/"), HttpMethod.GET, entity, String.class);

        String expected = "[{\"transactionID\":5," +
                "\"account_id\":2," +
                "\"type\":\"DIRECT_DEBIT\"," +
                "\"description\":\"Utility Payment\"," +
                "\"amount\":-180.0," +
                "\"currencyCode\":\"GBP\"," +
                "\"time_stamp\":\"2020-06-11T00:01:55+01:00\"}]";

        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
