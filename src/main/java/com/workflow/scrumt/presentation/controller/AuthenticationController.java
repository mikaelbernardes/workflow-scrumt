package com.workflow.scrumt.presentation.controller;

import com.workflow.scrumt.application.dto.AuthenticationDTO;
import com.workflow.scrumt.application.service.AuthenticationService;
import com.workflow.scrumt.presentation.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody AuthenticationDTO data) {
        try {
            AuthenticationResponse authResponse = authenticationService.authenticateUser(data);
            return ResponseEntity.ok(authResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error has occurred. Please try again later.");
        }
    }
}
