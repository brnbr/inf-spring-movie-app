package com.example.movieapp.user.controller;

import com.example.movieapp.user.dto.*;
import com.example.movieapp.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> create(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getOneUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getOne(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> update
            (@PathVariable Long userId,
             @RequestBody UpdateUserReqeust reqeust
    ) {
        return ResponseEntity.ok(userService.update(userId, reqeust, reqeust.getPassword()));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @RequestBody PasswordRequest request) {
        userService.delete(userId, request.getPassword());
        return ResponseEntity.noContent().build();
    }
}
