package com.dnzdlklc.lean.security;

import com.dnzdlklc.lean.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.dnzdlklc.lean.security.SecurityConstants.HEADER_STRING;

/**
 * Created by denizdalkilic on 21/07/2020 @ 12:28.
 */
public class JWTAuthorisationFilter extends BasicAuthenticationFilter {

    public JWTAuthorisationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String header = req.getHeader(HEADER_STRING);
        if (header == null) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        if (authentication == null) {
            throw new BadCredentialsException("Authentication failed. Check token.");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws IOException, URISyntaxException {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // "parse" the token.
            List<String> validTokens = Utils.getValidTokens();
            if (validTokens.contains(token)) {
                return new UsernamePasswordAuthenticationToken(token, token, new ArrayList<>());
            } else {
                throw new BadCredentialsException("Valid token not supplied.");
            }
        }
        return null;
    }
}
