package com.ferreteria.controller;

import com.ferreteria.controller.dto.AuthCreateUserRequest;
import com.ferreteria.controller.dto.AuthLoginRequest;
import com.ferreteria.controller.dto.AuthResponse;
import com.ferreteria.services.implementation.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest authLoginRequest) {

        return new ResponseEntity<>(this.userDetailsService.loginUser(authLoginRequest), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid AuthCreateUserRequest authCreateUserRequest) {

        return new ResponseEntity<>(this.userDetailsService.createUser(authCreateUserRequest), HttpStatus.CREATED);
    }

}
