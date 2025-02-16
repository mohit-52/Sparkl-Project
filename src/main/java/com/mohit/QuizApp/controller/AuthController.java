package com.mohit.QuizApp.controller;

import com.mohit.QuizApp.dto.AuthRequest;
import com.mohit.QuizApp.entity.User;
import com.mohit.QuizApp.repo.UserRepository;
import com.mohit.QuizApp.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepo) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(request.getUsername());

        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get first available role (if no role is assigned, default to "USER")
        String role = user.getRoles().stream().findFirst().orElse("USER");

        // Build response
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("role", role);

        return ResponseEntity.ok(response);
    }
}
