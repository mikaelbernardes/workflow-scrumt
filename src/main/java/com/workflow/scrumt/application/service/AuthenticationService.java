package com.workflow.scrumt.application.service;

import com.workflow.scrumt.application.dto.AuthenticationDTO;
import com.workflow.scrumt.domain.entity.User;
import com.workflow.scrumt.domain.repository.UserRepository;
import com.workflow.scrumt.infra.security.TokenService;
import com.workflow.scrumt.presentation.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public AuthenticationResponse authenticateUser(AuthenticationDTO data) throws BadCredentialsException {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var user = (User) auth.getPrincipal();

        var token = tokenService.generateToken(user);

        return new AuthenticationResponse(token, user.getId());
    }


}
