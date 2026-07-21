package com.example.movieapp.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    private String username;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
}
