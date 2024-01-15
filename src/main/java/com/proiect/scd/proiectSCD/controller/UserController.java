package com.proiect.scd.proiectSCD.controller;

import com.proiect.scd.proiectSCD.auth.AuthenticationRequest;
import com.proiect.scd.proiectSCD.auth.AuthenticationResponse;
import com.proiect.scd.proiectSCD.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService service;

    @SecurityRequirement(name = "Authorization")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
