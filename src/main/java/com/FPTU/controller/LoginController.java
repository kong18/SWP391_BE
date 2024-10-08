package com.FPTU.controller;

import com.FPTU.security.dto.LoginRequest;
import com.FPTU.security.dto.LoginResponse;
import com.FPTU.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

  private final JwtTokenService jwtTokenService;

  @PostMapping
  public ResponseEntity<LoginResponse> loginRequest(@Valid @RequestBody LoginRequest loginRequest) {
    final LoginResponse loginResponse = jwtTokenService.login(loginRequest);
    return ResponseEntity.ok(loginResponse);
  }

}
