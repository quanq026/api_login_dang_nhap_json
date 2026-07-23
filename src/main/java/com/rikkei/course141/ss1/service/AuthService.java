package com.rikkei.course141.ss1.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rikkei.course141.ss1.dto.request.LoginRequest;
import com.rikkei.course141.ss1.model.User;
import com.rikkei.course141.ss1.repository.UserRepository;
import com.rikkei.course141.ss1.security.UserPrincipal;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public User login(LoginRequest dto, HttpSession session) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        session.setAttribute("user", principal.getUser());
        return principal.getUser();
    }
}
