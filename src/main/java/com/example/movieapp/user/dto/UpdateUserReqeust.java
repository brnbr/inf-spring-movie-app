package com.example.movieapp.user.dto;

import lombok.Getter;

@Getter
public class UpdateUserReqeust {

    private String username;
    private String email;
    private Integer password;
}
