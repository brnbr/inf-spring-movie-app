package com.example.movieapp.user.controller;

import com.example.movieapp.user.dto.*;
import com.example.movieapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //CREATE
    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> create(@PathVariable CreateUserRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }

    //READ All
    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getAllUsers() {
        return ResponseEntity.of(userService.getAll());
    }

    //READ One
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getOneUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getOne(userId));
    }

    //Update
    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> update(@PathVariable Long userId, @RequestBody UpdateUserReqeust reqeust) {
        return ResponseEntity.ok(userService.update(userId, reqeust));
    }

    //Delete
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
