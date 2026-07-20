package com.example.movieapp.user.controller;

import com.example.movieapp.user.dto.CreateUserRequest;
import com.example.movieapp.user.dto.CreateUserResponse;
import com.example.movieapp.user.dto.GetUserResponse;
import com.example.movieapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> create(@PathVariable CreateUserRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @GetMapping("/users")
    public ResponseEntity<GetUserResponse> getAllUsers() {
        return ResponseEntity.of(userService.getAll());
    }
}
