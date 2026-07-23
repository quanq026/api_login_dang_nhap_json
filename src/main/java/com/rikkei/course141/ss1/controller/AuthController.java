package com.rikkei.course141.ss1.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rikkei.course141.ss1.dto.request.LoginRequest;
import com.rikkei.course141.ss1.dto.response.ApiResponse;
import com.rikkei.course141.ss1.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody LoginRequest dto, HttpSession session) {
        return ResponseEntity.ok(ApiResponse.success(authService.login(dto, session)));
    }
}
