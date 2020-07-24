package com.dnzdlklc.lean.security;

import com.dnzdlklc.lean.utils.Utils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static com.dnzdlklc.lean.security.SecurityConstants.HEADER_STRING;

/**
 * Created by denizdalkilic on 24/07/2020 @ 15:52.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        String leanToken = req.getHeader(HEADER_STRING);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(leanToken, leanToken);
        return authenticationManager.authenticate(authenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
        String leanToken = req.getHeader(HEADER_STRING);
        List<String> validTokens;
        try {
            validTokens = Utils.getValidTokens();
            for (String token : validTokens) {
                if (token.equals(leanToken)) {
                    leanToken = token;
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.getMessage();
        }
        res.addHeader(HEADER_STRING, leanToken);
    }
}
