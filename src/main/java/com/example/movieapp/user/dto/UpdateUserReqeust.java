package com.example.movieapp.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserReqeust {

    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
}
