package com.example.movieapp.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateUserRequest {

    private String username;
    private String email;
    private Integer password;
}
