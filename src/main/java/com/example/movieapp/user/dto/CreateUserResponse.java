package com.example.movieapp.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CreateUserResponse {

    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime createdAt;
}
